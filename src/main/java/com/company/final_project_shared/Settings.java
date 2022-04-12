package com.company.final_project_shared;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/*
simulation setting
        -number of days
        -population setting
        -number of faculty
        -number of day student
        -number of boarding student
        disease parameter setting
        -base transmission rate
        -transmission rate under certain type of masking
        -transmission rate under certain type of vaccination
        -daily contact
        human policy modification
        -vaccination percentage
        -mandatory masking type

 */
public class Settings extends Application {
    int[] values = new int[20];
    Stage[] windows = new Stage[10];
    public void begin(){
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        initialize();

        windows[0].show();
    }
    //make windows
    public void initialize(){
        mainScreen();
        setting();
        simulation();
    }

    public void mainScreen(){
        windows[0] = new Stage();
        windows[0].setTitle("Start");

        // create a button
        Button b = new Button("Start");
        Button b2 = new Button("Setting");

        // create a stack pane
        GridPane r = new GridPane();
        // add button
        r.add(b,0,0);
        r.add(b2,1,0);
        r.setAlignment(Pos.CENTER);

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               windows[0].hide();
               return;
            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Setting");
                windows[0].hide();
                windows[1].show();
            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[0].setScene(sc);


    }

    public void setting(){
        windows[1] = new Stage();
        windows[1].setTitle("Settings");

        Label instructions = new Label("Each button leads to a different group of settings back returns to previous page");
        // create a button
        Button simulation = new Button("simulation");
        Button population = new Button("population");
        Button disease = new Button("disease");
        Button human_policy = new Button("human policy");
        Button back = new Button("Back");

        // create a stack pane
        GridPane r = new GridPane();
        // add button
        //r.add(instructions,0,0,4,0);
        r.add(simulation,0,1);
        r.add(population,1,1);
        r.add(disease,2,1);
        r.add(human_policy,3,1);
        r.add(back,1,2);
        r.setAlignment(Pos.CENTER);

        simulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[1].hide();
                windows[2].show();

            }
        });
        population.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[1].hide();

            }
        });
        disease.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[1].hide();

            }
        });
        human_policy.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[1].hide();

            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[1].hide();
                windows[0].show();

            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[1].setScene(sc);


    }

    public void simulation(){
        windows[2] = new Stage();
        windows[2].setTitle("Settings");
        Label number = new Label("Number of Days in Simulation");
        Button back = new Button("Back");
        TextField days = new TextField();
        // create a stack pane
        GridPane r = new GridPane();

        r.add(back,0,1);
        r.add(days,1,0);
        r.add(number,0,0);
        r.setAlignment(Pos.CENTER);
        days.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[0] = Integer.parseInt( days.getText());
                System.out.println(values[0]);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[2].hide();
                windows[1].show();

            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[2].setScene(sc);


    }

}
