package com.company.final_project_shared;

import com.company.final_project_shared.Simulation;
import com.company.final_project_shared.Statistics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.application.Application.launch;

public class SIRgraph  {

    private ArrayList<int[]> stat_lib = new ArrayList<>(0);

    //defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    XYChart.Series infected = new XYChart.Series();
    XYChart.Series susceptible = new XYChart.Series();

    //creating the chart
    final LineChart<Number,Number> lineChart = new LineChart(xAxis,yAxis);

//quick chart and swing raptor

    public void first() {launch();}


    public void update(ArrayList<int[]> stat){
        stat_lib=stat;
    }

    //@Override
    public void start(Stage stage) throws Exception {
        lineChart.setTitle("SE");
        xAxis.setLabel("Number of Days");
        yAxis.setLabel("people");
        stage.setTitle("Line Chart Sample");

        infected.setName("infected");
        susceptible.setName("susceptible");
        for (int i = 0; i < stat_lib.size(); i++) {
            infected.getData().add(new XYChart.Data(i+1,stat_lib.get(i)[1]));
            susceptible.getData().add(new XYChart.Data(i+1,stat_lib.get(i)[0]));
        }
        //populating the series with data

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(infected);
        lineChart.getData().add(susceptible);
        stage.setScene(scene);
        stage.show();
    }
}