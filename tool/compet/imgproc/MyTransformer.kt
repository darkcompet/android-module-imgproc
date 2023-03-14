/*
 * Copyright (c) 2017-2021 DarkCompet. All rights reserved.
 */
package tool.compet.imgproc

import android.graphics.Bitmap
import android.graphics.Color
import kotlin.math.roundToInt

internal object MyTransformer {
	fun makeGray(input: Bitmap, _output: Bitmap?): Bitmap {
		var output = _output
		val width = input.width
		val height = input.height

		if (output == null) {
			output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)!!
		}
		if (!output.isMutable) {
			output = input.copy(input.config, true)
		}
		if (width != output!!.width || height != output.height) {
			return output
		}

		for (y in 0 until height) {
			for (x in 0 until width) {
				val argb = input.getPixel(x, y)
				val a = argb shr 24 and 0xff
				val r = argb shr 16 and 0xff
				val g = argb shr 8 and 0xff
				val b = argb and 0xff

				val gray = (0.299 * r + 0.587 * g + 0.114 * b).roundToInt()

				output.setPixel(x, y, Color.argb(a, gray, gray, gray))
			}
		}

		return output
	}
}