/*
 * Copyright (c) 2017-2021 DarkCompet. All rights reserved.
 */
package tool.compet.imgproc

import android.graphics.Bitmap

internal object MyScaler {
	fun scaleWithRate(input: Bitmap, widthWeight: Int, heightWeight: Int): Bitmap {
		val width = input.width
		val height = input.height
		val a = width.toLong() * heightWeight
		val b = height.toLong() * widthWeight
		var desW = width
		var desH = height
		if (a > b) {
			desW = height * widthWeight / heightWeight
		}
		else if (a < b) {
			desH = width * heightWeight / widthWeight
		}
		if (width == desW && height == desH) {
			return input
		}
		val output = Bitmap.createBitmap(
			input,
			Math.abs(desW - width) / 2,
			Math.abs(desH - height) / 2, desW, desH
		)
		return Bitmap.createScaledBitmap(output, desW, desH, false)
	}

	fun scaleTo(input: Bitmap, dstWidth: Int, dstHeight: Int): Bitmap {
		val width = input.width
		val height = input.height
		return if (width == dstWidth && height == dstHeight) {
			input
		}
		else Bitmap.createScaledBitmap(input, dstWidth, dstHeight, false)
	}
}