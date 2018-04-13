package com.deepholesdetection.n1ck.models

import java.util.*


class DataModel(val meta: Dictionary<String, Double>,
        //todo:add bitmap
                val boundingBox: BoundingBox
)

class BoundingBox(val leftLow: Point, val rightTop: Point)

class Point(val x: Double, val y: Double)