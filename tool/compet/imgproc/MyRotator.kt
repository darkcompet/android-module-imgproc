/*
 * Copyright (c) 2017-2021 DarkCompet. All rights reserved.
 */
package tool.compet.imgproc

import android.graphics.Bitmap
import android.graphics.Matrix
import tool.compet.core.DkLogcats
import tool.compet.graphics.DkBitmaps

internal object MyRotator {
	fun rotate(input: Bitmap, degrees: Float, pivotX: Int, pivotY: Int): Bitmap {
		try {
			val matrix = Matrix()
			matrix.postRotate(degrees, pivotX.toFloat(), pivotY.toFloat())

			return Bitmap.createBitmap(input, 0, 0, input.width, input.height, matrix, true)
		}
		catch (e: Exception) {
			DkLogcats.error(DkBitmaps::class.java, e)
		}

		return input
	}
}