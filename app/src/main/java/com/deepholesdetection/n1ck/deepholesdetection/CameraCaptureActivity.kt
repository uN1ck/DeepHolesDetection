package com.deepholesdetection.n1ck.deepholesdetection


import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.util.DisplayMetrics

import android.content.pm.ActivityInfo
import android.graphics.*

import android.hardware.Camera

import android.view.*

import java.io.IOException
import java.io.ByteArrayOutputStream


class CameraCaptureActivity : AppCompatActivity(), SurfaceHolder.Callback, View.OnClickListener, Camera.PictureCallback, Camera.PreviewCallback, Camera.AutoFocusCallback {
    override fun onClick(v: View?) {

    }

    private var camera: Camera? = null



    private var surfaceHolder: SurfaceHolder? = null
    private var preview: SurfaceView? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_capture)

        preview = findViewById<SurfaceView>(R.id.surfaceView)
        surfaceHolder = preview!!.holder
        surfaceHolder!!.addCallback(this)

    }


    override fun onResume() {
        super.onResume()
        camera = Camera.open()
    }

    override fun onPause() {
        super.onPause()

        if (camera != null) {
            camera!!.setPreviewCallback(null)
            camera!!.stopPreview()
            camera!!.release()
            camera = null
        }
    }


    override fun onAutoFocus(b: Boolean, camera: Camera) {

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
//            camera!!.setPreviewDisplay(holder)
            camera!!.setPreviewCallback(this)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val layoutParams = preview!!.layoutParams
        val displayMetrix = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrix)

        camera!!.setDisplayOrientation(90)
        layoutParams.width = displayMetrix.widthPixels
        layoutParams.height = displayMetrix.heightPixels
        preview!!.layoutParams = layoutParams

        val paramsForAutoFocus = camera!!.getParameters()
        paramsForAutoFocus.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
        paramsForAutoFocus.setPictureSize(preview!!.height, preview!!.width)
        paramsForAutoFocus.setPreviewSize(preview!!.height, preview!!.width)
        camera!!.setParameters(paramsForAutoFocus)
        camera!!.startPreview()

    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {

    }

    override fun onPictureTaken(bytes: ByteArray, camera: Camera) {

    }


    //counter for image's name. need for debugging and for watch through results.
    private var frameCounter: Int = 0

    override fun onPreviewFrame(bytes: ByteArray, camera: Camera) {
        val sizes = camera.parameters.previewSize
        val w = basicImagePreprocessing(camera.parameters.pictureSize.width, camera.parameters.pictureSize.height, bytes)
    }

    fun basicImagePreprocessing(width: Int, height: Int, bytesOfImage: ByteArray): Bitmap {
        val imageOutputStream = ByteArrayOutputStream()
        val rect = Rect(0, 0, width, height)

        val yuvimage = YuvImage(bytesOfImage, ImageFormat.NV21, width, height, null)
        yuvimage.compressToJpeg(rect, 100, imageOutputStream)

        val bmp = BitmapFactory.decodeByteArray(imageOutputStream.toByteArray(), 0, imageOutputStream.size())

        //Image rotating
        val rotationMatrix = Matrix()
        rotationMatrix.postRotate(90F)
        return Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, rotationMatrix, true)
    }

}
