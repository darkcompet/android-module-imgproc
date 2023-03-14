/*
 * Copyright (c) 2017-2020 DarkCompet. All rights reserved.
 */
package tool.compet.imgproc

import android.graphics.Bitmap

/**
 * Image Processing for Android bitmap.
 */
object DkImageProcs {
	// Make image more human readable by apply removing noise, gray scale...
	@JvmOverloads
	fun makeGray(input: Bitmap, output: Bitmap? = null): Bitmap {
		return MyTransformer.makeGray(input, output)
	}

	fun rotate(input: Bitmap, degrees: Int): Bitmap {
		return rotate(input, degrees.toFloat(), input.width shr 1, input.height shr 1)
	}

	// Rotate image with given angle in degrees
	fun rotate(input: Bitmap, degrees: Float, pivotX: Int, pivotY: Int): Bitmap {
		return MyRotator.rotate(input, degrees, pivotX, pivotY)
	}

	// Scale to given dimension
	fun scale(input: Bitmap, dstWidth: Int, dstHeight: Int): Bitmap {
		return MyScaler.scaleTo(input, dstWidth, dstHeight)
	}

	// Scale given bitmap to fit with given ratio (with : height)
	fun scaleWithRate(input: Bitmap, widthWeight: Int, heightWeight: Int): Bitmap {
		return MyScaler.scaleWithRate(input, widthWeight, heightWeight)
	}

	// Simple detect edge of given image
	fun detectEdge(input: Bitmap, output: Bitmap? = null): Bitmap {
		return MyEdgeDetector.detect(input, output)
	}
}