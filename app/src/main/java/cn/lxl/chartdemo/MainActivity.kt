package cn.lxl.chartdemo

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.markerview.view.*

class MainActivity : AppCompatActivity() {

    val entries1 = ArrayList<Entry>()
    val entries2 = ArrayList<Entry>()
    val entries3 = ArrayList<Entry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        chart.isEnabled = true
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)
        chart.axisRight.isEnabled = false
        chart.isDragEnabled = false
        chart.isScaleXEnabled = false
        chart.isScaleYEnabled = false
        chart.setPinchZoom(false)


        chart.clear()




        val xAxis = chart.xAxis
        xAxis.setLabelCount(7, true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.gridColor = Color.parseColor("#00000000")
        xAxis.axisLineColor = Color.parseColor("#D9DCE0")
        xAxis.textSize = 11f
        xAxis.textColor = Color.parseColor("#999999")
        xAxis.valueFormatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return when(value.toInt()){
                    0 -> "一"
                    1 -> "二"
                    2 -> "三"
                    3 -> "四"
                    4 -> "五"
                    5 -> "六"
                    6 -> "七"
                    else -> ""
                }
            }
        }

        val leftAxis = chart.axisLeft
        var max = 30f
        entries1.add(Entry(0f, 15f))
        entries1.add(Entry(1f, 16f))
        entries1.add(Entry(2f, 17f))
        entries1.add(Entry(3f, 10f))
        entries1.add(Entry(4f, 13f))
        entries1.add(Entry(5f, 9f))
        entries1.add(Entry(6f, 5f))
        entries2.add(Entry(0f, 20f))
        entries2.add(Entry(1f, 24f))
        entries2.add(Entry(2f, 22f))
        entries2.add(Entry(3f, 26f))
        entries2.add(Entry(4f, 27f))
        entries2.add(Entry(5f, 22f))
        entries2.add(Entry(6f, 20f))
        entries3.add(Entry(0f, 3f))
        entries3.add(Entry(1f, 10f))
        entries3.add(Entry(2f, 11f))
        entries3.add(Entry(3f, 14f))
        entries3.add(Entry(4f, 10f))
        entries3.add(Entry(5f, 13f))
        entries3.add(Entry(6f, 12f))

        leftAxis.mAxisMaximum = (max*1.5f).toInt().toFloat()
        leftAxis.axisMinimum = 0f
        leftAxis.gridColor = Color.parseColor("#00000000")
        leftAxis.axisLineColor = Color.parseColor("#00000000")
        leftAxis.valueFormatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString()
            }
        }
        leftAxis.textSize = 11f
        leftAxis.textColor = Color.parseColor("#999999")

        val set1 = LineDataSet(entries1, null)
        set1.fillDrawable = ContextCompat.getDrawable(this, R.drawable.fade_linechart)
        set1.setDrawFilled(true)
        //set1.fillAlpha = 1800
        set1.lineWidth = 2f
        set1.color = Color.parseColor("#F82828")
        set1.circleRadius = 4f
        set1.setCircleColor(Color.parseColor("#00000000"))
        set1.circleHoleColor = Color.parseColor("#00000000")
        set1.circleHoleRadius = 2f
        set1.setDrawValues(true)
        set1.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        set1.highLightColor = Color.parseColor("#F82828")
        set1.enableDashedHighlightLine(10f,10f,10f)
        set1.highlightLineWidth = 1f

        val set2 = LineDataSet(entries2, null)
        set2.fillDrawable = ContextCompat.getDrawable(this, R.drawable.fade_linechart2)
        set2.setDrawFilled(true)
        //set1.fillAlpha = 1800
        set2.lineWidth = 2f
        set2.color = Color.parseColor("#1F97FC")
        set2.circleRadius = 4f
        set2.setCircleColor(Color.parseColor("#00000000"))
        set2.circleHoleColor = Color.parseColor("#00000000")
        set2.circleHoleRadius = 2f
        set2.setDrawValues(false)
        set2.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        set2.highLightColor = Color.parseColor("#F82828")
        set2.enableDashedHighlightLine(10f,10f,10f)
        set2.highlightLineWidth = 1f


        val set3 = LineDataSet(entries3, null)
        set3.fillDrawable = ContextCompat.getDrawable(this, R.drawable.fade_linechart3)
        set3.setDrawFilled(true)
        //set1.fillAlpha = 1800
        set3.lineWidth = 2f
        set3.color = Color.parseColor("#64CBD8")
        set3.circleRadius = 4f
        set3.setCircleColor(Color.parseColor("#00000000"))
        set3.circleHoleColor = Color.parseColor("#00000000")
        set3.circleHoleRadius = 2f
        set3.setDrawValues(false)
        set3.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        set3.highLightColor = Color.parseColor("#F82828")
        set3.enableDashedHighlightLine(10f,10f,10f)
        set3.highlightLineWidth = 1f

        val sets = ArrayList<ILineDataSet>()
        sets.add(set1)
        sets.add(set2)
        sets.add(set3)

        val data = LineData(sets)
        data.setValueTextSize(0f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.TRANSPARENT)


        chart.animateXY(1000, 1000, Easing.Linear)
        chart.data = data
        chart.highlightValue(null)

        chart.legend.formSize = 0f

        var mv = XYMarkerView(this,chart,entries1,entries2,entries3)

        chart.marker = mv
        chart.invalidate()

    }

    inner class MyMakerView(context: Context?) : MarkerView(context, R.layout.markerview) {
        override fun refreshContent(e: Entry?, highlight: Highlight?) {
            tv.text = "离床:" + entries1[e!!.x.toInt()].y
            tv2.text = "在床:" + entries2[e!!.x.toInt()].y
            tv3.text = "离线:" + entries3[e!!.x.toInt()].y
            super.refreshContent(e, highlight)
        }



    }

}