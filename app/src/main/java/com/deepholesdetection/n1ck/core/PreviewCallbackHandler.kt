package com.deepholesdetection.n1ck.core

import android.content.Context.LOCATION_SERVICE
import android.graphics.*
import android.hardware.Camera
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.deepholesdetection.n1ck.core.dataSender.DataSender
import com.deepholesdetection.n1ck.core.imageAnalyzer.ImageAnalyzer
import com.deepholesdetection.n1ck.core.imageProcessor.ImageProcessor
import java.io.ByteArrayOutputStream

class PreviewCallbackHandler(val imageProcessor: ImageProcessor,
                             val dataSender: DataSender,
                             val imageAnalyzer: ImageAnalyzer)
    : Camera.PreviewCallback {

    override fun onPreviewFrame(data: ByteArray?, camera: Camera) {

        val raw = _basicImagePreprocessing(camera.parameters.previewSize.width, camera.parameters.previewSize.height, data!!)
        val processed = imageProcessor.process(raw)
        val analyze = imageAnalyzer.analyze(processed)
        dataSender.send(analyze)

    }

    private fun _basicImagePreprocessing(width: Int, height: Int, bytesOfImage: ByteArray): Bitmap {
        val imageOutputStream = ByteArrayOutputStream()
        val rect = Rect(0, 0, width, height)
        val yuvimage = YuvImage(bytesOfImage, ImageFormat.NV21, width, height, null)
        yuvimage.compressToJpeg(rect, 100, imageOutputStream)
        val bmp = BitmapFactory.decodeByteArray(imageOutputStream.toByteArray(), 0, imageOutputStream.size())
        return bmp
        //        val rotationMatrix = Matrix()
        //        rotationMatrix.postRotate(90F)
        //        return Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, rotationMatrix, true)
    }


}