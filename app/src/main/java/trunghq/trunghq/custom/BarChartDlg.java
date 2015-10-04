package trunghq.trunghq.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import trunghq.trunghq.R;

/**
 * Created by vantuegia on 10/3/2015.
 */
public class BarChartDlg extends DialogFragment {

    private int[] mYVals;
    private String mChartName;

    public static BarChartDlg newInstance(int[] yVals, String chartName) {
        Log.d("NOTICEE", "newInstance");
        BarChartDlg fm = new BarChartDlg();
        fm.mYVals = yVals;
        fm.mChartName = chartName;
        return fm;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("NOTICEE", "onCreate");
    }

    public BarChartDlg() {
        super();
        Log.d("NOTICEE", "BarChartDlg");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("NOTICEE", "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("NOTICEE", "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("NOTICEE", "onActivityCreated");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("NOTICEE", "onViewCreated");

    }

    private void setData(BarChart barChart, int[] arr) {

        ArrayList<String> xVals = new ArrayList<String>();
        int count = arr.length;
        for (int i = 0; i < count; i++) {
            xVals.add("X" + i);
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
//            float mult = (range + 1);
//            float val = (float) (Math.random() * mult);
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

        barChart.setData(data);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("NOTICEE", "onCreateDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.bar_chart_dlg, null);
        // Class name
        TextView tvChartName = (TextView) view.findViewById(R.id.tvChartName);
        if (null == mChartName) mChartName = "";
        tvChartName.setText(mChartName);
        // Bar chart
        BarChart barChart = (BarChart) view.findViewById(R.id.barChart);
        barChart.getLegend().setEnabled(false);
        // set max value 100, for display percent
        barChart.getAxisLeft().setAxisMaxValue(100f);
        barChart.getAxisRight().setAxisMaxValue(100f);
//        barChart.setOnChartValueSelectedListener(this);
        barChart.animateXY(2000, 2000);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // barChart.setDrawBarShadow(true);

        // barChart.setDrawXLabels(false);

        barChart.setDrawGridBackground(false);

        setData(barChart, mYVals);

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
