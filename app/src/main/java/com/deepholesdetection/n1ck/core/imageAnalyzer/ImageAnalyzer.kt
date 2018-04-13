package com.deepholesdetection.n1ck.core.imageAnalyzer

import android.graphics.Bitmap
import com.deepholesdetection.n1ck.models.DataModel

interface ImageAnalyzer {
    fun analyze(image: Bitmap): DataModel
}