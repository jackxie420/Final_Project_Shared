package com.company.final_project_shared;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.application.Application.launch;

public class SIRgraph  extends Application {

    private ArrayList<int[]> stat_lib;
    private int x;



    public void Begin() throws Exception {
        launch();
        /*
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                launch();
            }
        });


         */


    }

    public void update(ArrayList<int[]> stat){
        stat_lib= (ArrayList<int[]>) stat.clone();

    }

    @Override
    public void start(Stage stage) throws Exception {
        createGraph();

    }

    public void createGraph() throws Exception {
        stat_lib = Simulation.getStat();
        XYChart.Series infected = new XYChart.Series();
        XYChart.Series susceptible = new XYChart.Series();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = new LineChart(xAxis,yAxis);
        Stage stage= new Stage();
        lineChart.setTitle("SE");
        xAxis.setLabel("Number of Days");
        yAxis.setLabel("people");
        stage.setTitle("Line Chart Sample");
        lineChart.setCreateSymbols(false);
        infected.setName("infected");
        susceptible.setName("susceptible");
        System.out.println(stat_lib.size());
        for (int i = 0; i < stat_lib.size(); i++) {
            ;
            infected.getData().add(new XYChart.Data(i+1,stat_lib.get(i)[1]));
            susceptible.getData().add(new XYChart.Data(i+1,stat_lib.get(i)[0]));
            //System.out.println(i+":"+stat_lib.get(i)[1]);
            //System.out.println(i+":"+stat_lib.get(i)[0]);
        }
        //populating the series with data

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(infected);
        lineChart.getData().add(susceptible);
        stage.setScene(scene);
        stage.show();
    }



}