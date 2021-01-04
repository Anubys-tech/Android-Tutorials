package com.anubys.example.realtimeaccelerometermpandroiddemo

/** @Author Created by Anubys on the 03.01.2021 */

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.anubys.example.realtimeaccelerometermpandroiddemo.databinding.ActivityMainBinding

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class MainActivity : AppCompatActivity(), SensorEventListener {
    private val tag = MainActivity::class.java.simpleName

    private lateinit var sensorManager: SensorManager
    private lateinit var binding: ActivityMainBinding

    private var chart: LineChart? = null
    private var thread: Thread? = null
    private var plotData = true


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "TAG - MainActivity - onCreate()")

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chart = binding.chart

        initSensors()
        initChart()
        feedMultiple()
    }

    override fun onPause() {
        Log.d(tag, "TAG - MainActivity - onResume()")
        super.onPause()

        thread?.interrupt()
        sensorManager.unregisterListener(this)
    }

    override fun onDestroy() {
        Log.d(tag, "TAG - MainActivity - onDestroy()")

        sensorManager.unregisterListener(this@MainActivity)
        thread?.interrupt()
        super.onDestroy()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun initSensors() {
        Log.d(tag, "TAG - MainActivity - initSensor()")

        initSystemService()
        registerListener()
    }

    private fun initSystemService() {
        Log.d(tag, "TAG - MainActivity - initSystemService()")

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    private fun registerListener() {
        Log.d(tag, "TAG - MainActivity - registerListener()")

        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
        )
    }

    private fun initChart() {
        Log.d(tag, "TAG - MainActivity - initChart()")

        // Beschreibungstext aktivieren
        chart?.description?.isEnabled = true

        // Touch-Gesten aktivieren
        chart?.setTouchEnabled(true)

        // Skalieren und Ziehen aktivieren
        chart?.isDragEnabled = true
        chart?.setScaleEnabled(true)
        chart?.setDrawGridBackground(false)

        // Wenn deaktiviert, kann die Skalierung auf der x- und y-Achse getrennt vorgenommen werden
        chart?.setPinchZoom(true)

        // Eine alternative Hintergrundfarbe einstellen
        chart?.setBackgroundColor(Color.WHITE)

        val data = LineData()
        data.setValueTextColor(Color.WHITE)

        // Leere Daten hinzufügen
        chart?.data = data

        // Die Legende holen (nur möglich nach dem Setzen von Daten)
        val legend = chart?.legend

        // Die Legende anpassen ...
        legend?.form = Legend.LegendForm.LINE
        legend?.textColor = Color.WHITE

        val xAxis = chart?.xAxis
        xAxis?.textColor = Color.WHITE
        xAxis?.setDrawGridLines(true)
        xAxis?.setAvoidFirstLastClipping(true)
        xAxis?.isEnabled = true

        val leftAxis = chart?.axisLeft
        leftAxis?.textColor = Color.WHITE
        leftAxis?.setDrawGridLines(false)
        leftAxis?.axisMaximum = 10f
        leftAxis?.axisMinimum = 0f
        leftAxis?.setDrawGridLines(true)

        val rightAxis = chart?.axisRight
        rightAxis?.isEnabled = false

        chart?.axisLeft?.setDrawGridLines(false)
        chart?.xAxis?.setDrawGridLines(false)
        chart?.setDrawBorders(false)
    }

    private fun feedMultiple() {
        Log.d(tag, "TAG - MainActivity - feedMultiple()")

        thread?.interrupt()

        thread = Thread {
            while (true) {
                plotData = true

                try {
                    Thread.sleep(1000) // 1. Sekunde
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        thread?.start()
    }

    private fun addEntry(event: SensorEvent) {
        Log.d(tag, "TAG - MainActivity - addEntry()")

        val data = chart?.data

        if (data != null) {
            var set = data.getDataSetByIndex(0)

            if (set == null) {
                set = createSet()
                data.addDataSet(set)
            }

            data.addEntry(Entry(set.entryCount.toFloat(), event.values[0] + 5), 0)
            data.notifyDataChanged()

            // Dem Diagramm mitteilen, dass sich seine Daten geändert haben
            chart?.notifyDataSetChanged()

            // Die Anzahl der sichtbaren Einträge begrenzen
            chart?.setVisibleXRangeMaximum(150f)

            // Zum letzten Eintrag springen
            chart?.moveViewToX(data.entryCount.toFloat())
        }
    }

    private fun createSet(): LineDataSet {
        Log.d(tag, "TAG - MainActivity - createSet()")

        val lineDataSet = LineDataSet(null, "Dynamic Data")
        lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
        lineDataSet.lineWidth = 3f
        lineDataSet.color = Color.MAGENTA
        lineDataSet.isHighlightEnabled = false
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircles(false)
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.cubicIntensity = 0.2f

        return (lineDataSet)
    }


    //* ************************************************ *
    //*                   S E N S O R                    *
    //* ************************************************ *
    override fun onSensorChanged(event: SensorEvent) {
        //Log.d(tag, "TAG - MainActivity - onSensorChanged()")

        if (plotData) {
            addEntry(event)
            plotData = false
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(tag, "TAG - MainActivity - onAccuracyChanged()")
    }
}
