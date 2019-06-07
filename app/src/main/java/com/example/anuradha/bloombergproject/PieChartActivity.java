package com.example.anuradha.bloombergproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.example.anuradha.bloombergproject.MainActivity.admin;
import static com.example.anuradha.bloombergproject.MainActivity.apex;
import static com.example.anuradha.bloombergproject.MainActivity.bcloud;
import static com.example.anuradha.bloombergproject.MainActivity.corp;
import static com.example.anuradha.bloombergproject.MainActivity.dev;
import static com.example.anuradha.bloombergproject.MainActivity.feed;
import static com.example.anuradha.bloombergproject.MainActivity.inet;
import static com.example.anuradha.bloombergproject.MainActivity.prod;
import static com.example.anuradha.bloombergproject.MainActivity.storage;
import static com.example.anuradha.bloombergproject.MainActivity.tdmz;

public class PieChartActivity extends AppCompatActivity {

    Button backMain;
    float storagef, prodf, bcloudf, devf, adminf, corpf, inetf, feedf, apexf, tdmzf;
    float[] data;
    String[] areas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        backMain = findViewById(R.id.id_backMain);

        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PieChartActivity.this, MainActivity.class));
            }
        });

        storagef = (float)storage;
        prodf = (float)prod;
        bcloudf = (float)bcloud;
        devf = (float)dev;
        adminf = (float)admin;
        corpf = (float)corp;
        inetf = (float)inet;
        feedf = (float)feed;
        apexf = (float)apex;
        tdmzf = (float)tdmz;

        data = new float[]{storagef, prodf, bcloudf, devf, adminf, corpf, inetf, feedf, apexf, tdmzf};
        areas = new String[]{"storage", "prod", "bcloud", "dev", "admin", "corp", "inet", "feed", "apex", "tdmz"};

        setupPieChart();

    }

    private void setupPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();

        for(int i = 0; i < data.length; i++){
            pieEntries.add(new PieEntry(data[i], areas[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Percent per area");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setSliceSpace(1f);

        PieData pieData = new PieData(dataSet);

        PieChart chart = findViewById(R.id.chart);
        chart.setData(pieData);
        chart.animateY(1000);
        chart.setDrawHoleEnabled(false);
        chart.invalidate();

    }

}