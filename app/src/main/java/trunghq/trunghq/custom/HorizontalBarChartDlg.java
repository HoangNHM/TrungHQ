package trunghq.trunghq.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import trunghq.trunghq.R;

/**
 * Created by vantuegia on 10/4/2015.
 */
public class HorizontalBarChartDlg extends DialogFragment{

    private HorizontalBarChart mChart;
    private int[] mYVals;
    private String mChartName;

    public static HorizontalBarChartDlg newInstance(int[] yVals, String chartName) {
        Log.d("NOTICEE", "newInstance");
        HorizontalBarChartDlg fm = new HorizontalBarChartDlg();
        fm.mYVals = yVals;
        fm.mChartName = chartName;
        return fm;
    }
    private void setData(BarChart barChart, int[] arr) {

        ArrayList<String> xVals = new ArrayList<String>();
        int count = arr.length;
        // draw backward
        for (int i = count; i > 0; i--) {
            xVals.add(i + "");
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        // draw backward
        for (int i = 0; i < count; i++) {
            yVals1.add(new BarEntry(arr[count - 1 - i], i));
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
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("NOTICEE", "onCreateDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.hor_bar_chart_dlg, null);
        // Class name
//        TextView tvChartName = (TextView) view.findViewById(R.id.tvChartName);
//        if (null == mChartName) mChartName = "";
//        tvChartName.setText(mChartName);
        // Bar chart
        HorizontalBarChart horBarChart = (HorizontalBarChart) view.findViewById(R.id.horBarChart);
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

        horBarChart.setDescription("");

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

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        // Add action buttons
//                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // ...
//                    }
//                })
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // ...
//                    }
//                });
        return builder.create();

    }
}
