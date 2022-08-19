package cn.lxl.chartdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.List;

public class XYMarkerView extends MarkerView {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private List<Entry> entries1;
    private List<Entry> entries2;
    private List<Entry> entries3;
    private LineChart chart;
    private Context context;

    public XYMarkerView(Context context, LineChart chart, List<Entry> entries1, List<Entry> entries2, List<Entry> entries3) {
        super(context, R.layout.markerview);
        this.context = context;
        tv1 = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        this.entries1 = entries1;
        this.entries2 = entries2;
        this.entries3 = entries3;
        this.chart = chart;
    }

    //回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {


        int x = (int) e.getX();

        tv1.setText("离床:" + entries1.get(x).getY());
        tv2.setText("在床:" + entries2.get(x).getY());
        tv3.setText("离线:" + entries3.get(x).getY());
        super.refreshContent(e, highlight);
    }

    //设置显示偏移位置
    @Override
    public MPPointF getOffset() {
        return new MPPointF((getWidth() / 8), -getHeight());
    }

    //这里修改markview偏移
    public void draw(Canvas canvas, float posX, float posY) {
        MPPointF mpPointF = new MPPointF();
        //根据项目需求通过width、height、chartWidth、chartHeight来设置point偏移位置
        float width = getWidth();//markview宽度
        float height = getHeight();//markview的高度
        float chartWidth = chart.getWidth();//chart宽度
        float chartHeight = chart.getHeight();//chart的高度
        //右边超出的情况，显示一个，隐藏一个，设置对应的偏移量
        if (chartWidth < posX + width) {
            mpPointF.x =  - (getWidth());
        } else {//右边没有超出的情况，同上
            mpPointF.x = 0;
        }
        //绘制方法是直接复制过来的，没动
        int saveId = canvas.save();
        // translate to the correct position and draw
        canvas.translate(posX + mpPointF.x, posY + mpPointF.y);
        draw(canvas);
        canvas.restoreToCount(saveId);
    }

    public int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

}