package com.politecnicomalaga.NasdaqOilPrices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.politecnicomalaga.NasdaqOilPrices.Control.ControlGrafico;
import com.politecnicomalaga.NasdaqOilPrices.Model.Price;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class mainGraphview extends AppCompatActivity {
    private LinkedList<Price> gList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_graphview);
        GraphView gvSensor = findViewById(R.id.gvGrafico);
        ArrayList<DataPoint> miArray = new ArrayList<>();
        ControlGrafico CG = new ControlGrafico();
        List<Double> Precio = CG.getPrecioDouble();

        for (int i = 0; i < Precio.size(); i++) {

            DataPoint newDP = new DataPoint(i, Precio.get(i));
            miArray.add(newDP);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(miArray.toArray(new DataPoint[0]));

        series.setThickness(5);
        series.setTitle("Oil Price");
        series.setDrawBackground(true);
        series.setColor(Color.argb(255, 255, 60, 60));
        series.setBackgroundColor(Color.argb(100, 204, 119, 119));
        series.setDrawDataPoints(true);

        // legend
        gvSensor.getLegendRenderer().setVisible(true);
        gvSensor.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        gvSensor.getLegendRenderer().setBackgroundColor(Color.rgb(220,220,220));

        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        gvSensor.setTitle("Precio de petroleo: ");

        // on below line we are setting
        // text color to our graph view.
        gvSensor.setTitleColor(Color.BLACK);

        // on below line we are setting
        // our title text size.
        gvSensor.setTitleTextSize(40);

        // enable scaling and scrolling
        //miGrafico.getViewport().setScalable(true);
        //miGrafico.getViewport().setScalableY(true);

        // set manual X bounds
        gvSensor.getViewport().setYAxisBoundsManual(true);
        gvSensor.getViewport().setMinY(0);
        gvSensor.getViewport().setMaxY(200);

        gvSensor.getViewport().setXAxisBoundsManual(true);
        gvSensor.getViewport().setMinX(0);
        gvSensor.getViewport().setMaxX(24);

        gvSensor.getViewport().setBorderColor(Color.BLUE);

        // on below line we are adding
        // data series to our graph view.
        gvSensor.removeAllSeries();
        gvSensor.addSeries(series);
         
    }
}