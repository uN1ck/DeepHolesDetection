package com.deepholesdetection.n1ck.core.imageProcessor

import android.graphics.Bitmap

interface ImageProcessor {
    fun process(image: Bitmap): Bitmap
}