/*
 * Copyright (c) 2017-2021 DarkCompet. All rights reserved.
 */

package tool.compet.imgproc;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import tool.compet.graphics.DkBitmaps;
import tool.compet.core.DkLogcats;

class MyRotator {
	static Bitmap rotate(Bitmap input, float degrees, int pivotX, int pivotY) {
		try {
			Matrix matrix = new Matrix();
			matrix.postRotate(degrees, pivotX, pivotY);

			return Bitmap.createBitmap(input, 0, 0, input.getWidth(), input.getHeight(), matrix, true);
		}
		catch (Exception e) {
			DkLogcats.error(DkBitmaps.class, e);
		}
		return input;
	}
}
