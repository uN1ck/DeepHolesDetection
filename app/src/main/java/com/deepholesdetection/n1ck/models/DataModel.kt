package com.deepholesdetection.n1ck.models

import android.graphics.Bitmap
import java.util.*


class DataModel(val meta: Map<String, Double>,
                val image: Bitmap,
                val boundingBox: BoundingBox)

class BoundingBox(val leftLow: Point, val rightTop: Point)

class Point(val x: Double, val y: Double)