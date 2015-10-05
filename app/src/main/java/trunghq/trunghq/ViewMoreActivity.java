package trunghq.trunghq;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ViewMoreActivity extends AppCompatActivity {

    private HorizontalBarChart mChart;
    private int[] mYVals = {10, 20, 30, 49, 59, 67, 78, 88, 99, 0, 18, 25, 36, 49, 50, 69};
    private String mChartName = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        mChart = (HorizontalBarChart) findViewById(R.id.horBarChart);
        //lấy intent gọi Activity này
        Intent callerIntent = getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller = callerIntent.getBundleExtra("ClassPackage");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        mChartName = packageFromCaller.getString("ClassName");
        setupChart(mChart);
    }

    private void setupChart(HorizontalBarChart horBarChart) {
        horBarChart.getLegend().setEnabled(false);
        // set max value 100, for display percent
        horBarChart.getAxisLeft().setAxisMaxValue(108f);
        horBarChart.getAxisRight().setAxisMaxValue(108f);
//        horBarChart.setOnChartValueSelectedListener(this);
        horBarChart.animateY(2000);

        XAxis xl = horBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);

        horBarChart.setDrawBarShadow(false);
        horBarChart.setDrawValueAboveBar(true);

        horBarChart.setDescription(mChartName);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        horBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        horBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // horBarChart.setDrawBarShadow(true);

        // horBarChart.setDrawXLabels(false);

        horBarChart.setDrawGridBackground(false);

        setData(horBarChart, mYVals);
    }

    private void setData(BarChart barChart, int[] arr) {

        ArrayList<String> xVals = new ArrayList<String>();
        int count = arr.length;
        // draw backward
        for (int i = 0; i < count; i++) {
            xVals.add(i + "");
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        // draw backward
        for (int i = 0; i < count; i++) {
//            yVals1.add(new BarEntry(arr[count - 1 - i], i));
            yVals1.add(new BarEntry(arr[i], i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setColors(new int[]{Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, 208, 140),
                Color.rgb(140, 234, 255), Color.rgb(255, 140, 157)});
        set1.setBarSpacePercent(35f);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
//        data.setValueTypeface(mTf);

        // set chart.height
        barChart.getLayoutParams().height = 100 * count;

        barChart.setData(data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
