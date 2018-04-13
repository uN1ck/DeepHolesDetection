package com.deepholesdetection.n1ck.core.dataSender

import com.deepholesdetection.n1ck.core.LocationHandler
import com.deepholesdetection.n1ck.models.DataModel
import mu.KotlinLogging

class DummyDataSender(val locationHandler: LocationHandler) : DataSender {
    override fun send(data: DataModel) {
        //fixme filter without geolocation
        val logger = KotlinLogging.logger("DummyDataSender")
        logger.info { "Data sending ${locationHandler.location}" }
    }

}