package com.deepholesdetection.n1ck.core.imageProcessor

import android.graphics.Bitmap
import mu.KotlinLogging

class DummyImageProcessor : ImageProcessor {
    override fun process(image: Bitmap): Bitmap {
        val logger = KotlinLogging.logger("DummyImageProcessor")
        logger.info { "Image processing" }
        return image
    }

}