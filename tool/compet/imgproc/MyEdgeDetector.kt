/*
 * Copyright (c) 2017-2020 DarkCompet. All rights reserved.
 */
package tool.compet.imgproc

import android.graphics.Bitmap
import android.graphics.Color
import kotlin.math.roundToInt
import kotlin.math.sqrt

internal object MyEdgeDetector {
	fun detect(input: Bitmap, _output: Bitmap?): Bitmap {
		var output = _output
		if (output == null || !output.isMutable) {
			output = input.copy(input.config, true)
		}

		val width = input.width
		val heigh = input.height
		if (width != output!!.width || heigh != output.height) {
			return output
		}

		val filter1 = arrayOf(intArrayOf(-1, 0, 1), intArrayOf(-2, 0, 2), intArrayOf(-1, 0, 1))
		val filter2 = arrayOf(intArrayOf(1, 2, 1), intArrayOf(0, 0, 0), intArrayOf(-1, -2, -1))

		// GetPrefixSum 3-by-3 array of colors in neighborhood
		val tmpGrayArea = Array(3) { IntArray(3) }

		for (y in 1..(heigh - 2)) {
			for (x in 1..(width - 2)) {
				for (i in 0..2) {
					for (j in 0..2) {
						val argb = input.getPixel(x - 1 + i, y - 1 + j)
						val r = argb shr 16 and 0xff
						val g = argb shr 8 and 0xff
						val b = argb and 0xff

						tmpGrayArea[i][j] = (0.299 * r + 0.587 * g + 0.114 * b).roundToInt()
					}
				}

				// Apply filter
				var gx = 0
				var gy = 0
				for (i in 0..2) {
					for (j in 0..2) {
						gx += tmpGrayArea[i][j] * filter1[i][j]
						gy += tmpGrayArea[i][j] * filter2[i][j]
					}
				}

				var magnitude = sqrt((gx * gx + gy * gy).toDouble()).toInt()
				magnitude = if (magnitude < 0) 0 else Math.min(magnitude, 255)
				magnitude = 255 - magnitude

				val color = Color.rgb(magnitude, magnitude, magnitude)
				output.setPixel(x, y, color)
			}
		}

		return output
	}
}