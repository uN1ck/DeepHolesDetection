package com.deepholesdetection.n1ck.deepholesdetection


import android.support.v7.app.AppCompatActivity
import android.os.Bundle


import android.hardware.Camera
import android.location.LocationManager
import com.deepholesdetection.n1ck.core.LocationHandler


import com.deepholesdetection.n1ck.core.PreviewCallbackHandler
import com.deepholesdetection.n1ck.core.dataSender.DummyDataSender
import com.deepholesdetection.n1ck.core.imageAnalyzer.DummyImageAnalyzer
import com.deepholesdetection.n1ck.core.imageProcessor.DummyImageProcessor


class CameraCaptureActivity : AppCompatActivity() {

    private lateinit var camera: Camera
    private lateinit var previewCallbackHandler: PreviewCallbackHandler
    private var locationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_capture)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val locationListener = LocationHandler()
        //fixme here!
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        previewCallbackHandler = PreviewCallbackHandler(DummyImageProcessor(), DummyDataSender(locationListener), DummyImageAnalyzer())
    }


    override fun onResume() {
        super.onResume()
        camera = Camera.open()
        camera.setPreviewCallback(previewCallbackHandler)
        camera.startPreview()
    }

    override fun onPause() {
        super.onPause()
        camera.setPreviewCallback(null)
        camera.stopPreview()
        camera.release()
    }


}
