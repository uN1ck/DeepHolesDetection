package com.deepholesdetection.n1ck.core.dataSender

import com.deepholesdetection.n1ck.models.DataModel

interface DataSender {
    fun send(data: DataModel)
}