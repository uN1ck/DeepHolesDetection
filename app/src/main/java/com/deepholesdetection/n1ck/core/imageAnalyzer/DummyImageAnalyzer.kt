package com.deepholesdetection.n1ck.core.imageAnalyzer

import android.graphics.Bitmap
import com.deepholesdetection.n1ck.models.BoundingBox
import com.deepholesdetection.n1ck.models.DataModel
import com.deepholesdetection.n1ck.models.Point
import mu.KotlinLogging
import java.util.*

class DummyImageAnalyzer : ImageAnalyzer {
    override fun analyze(image: Bitmap): DataModel {
        val logger = KotlinLogging.logger("DummyImageAnalyzer")
        logger.info { "Image analysing" }
        return DataModel(hashMapOf("foo" to 0.0, "bar" to 0.0), image, BoundingBox(Point(0.0, 0.0), Point(0.0, 0.0)))
    }

}