package com.deepholesdetection.n1ck.core

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.util.LogWriter
import android.util.Log

class LocationHandler : LocationListener {
    private var _location: Location? = null
    val location: Location?
        get() = _location

    override fun onLocationChanged(location: Location) {
        _location = location
        Log.i("geo", "" + location.longitude + ":" + location.latitude);
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
}