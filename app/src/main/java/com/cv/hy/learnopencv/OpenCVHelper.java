package com.cv.hy.learnopencv;

/**
 * Created by GZS10692 on 2017/7/24.
 */

public class OpenCVHelper {
    static {
        System.loadLibrary("OpenCV");
    }

    public static native int[] gray(int[] buf, int w, int h);
}
