package com.pg.salud.ui.registro

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYSeries
import java.util.*

class RegistroImcViewModel : ViewModel() {
    val domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10,11,12,13)
    val series1Number = arrayOf<Number>(1,8,4,16,22,30)

    val series1: XYSeries = SimpleXYSeries(Arrays.asList(* series1Number), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "series 1")

    val series1Format = LineAndPointFormatter(Color.BLUE, Color.BLACK, null, null)

    plot    val domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10,11,12,13)
    val series1Number = arrayOf<Number>(1,8,4,16,22,30)

    val series1: XYSeries = SimpleXYSeries(Arrays.asList(* series1Number), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "series 1")

    val series1Format = LineAndPointFormatter(Color.BLUE, Color.BLACK, null, null)
