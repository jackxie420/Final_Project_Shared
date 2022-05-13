package com.company.final_project_shared;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;


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
/*
values key
0 number of days in simulation
1 number of boarding students
2 number of day students
3 number of faculty
4 percentage vaccinated  divide by 100 to get percentage
5 type mandated 0 surgical 1 cloth 2 N95 4 none
6 virus type 0 is omicron 1 is delta
 */
//use dictionary with name of var and its place in value arraylist for easy change
public class Settings extends Application {
    int[] values = new int[20];
    Stage[] windows = new Stage[10];
    HashMap<String,Integer> key = new HashMap<String,Integer>();
    public void begin(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        addKey();
        initialize();
        Platform.setImplicitExit(false);
        windows[0].show();
    }
    //make windows
    private void initialize(){
        mainScreen();
        setting();
        simulation();
        population();
        policy();
        virus();

    }

    private void addKey(){
        key.put("Nday",0);
        key.put("board",1);
        key.put("day",2);
        key.put("fac",3);
        key.put("vacP",4);
        key.put("maskT",5);
        key.put("virusT",6);

    }

    private void mainScreen(){
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
               Simulation.setStats(values);
                System.out.println("In");
                Platform.exit();

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

    private void setting(){
        windows[1] = new Stage();
        windows[1].setTitle("Settings");

        Label instructions = new Label("Each button leads to a different group of settings back returns to previous page");
        // create a button
        Button simulation = new Button("simulation");
        Button population = new Button("population");
        Button virus = new Button("virus type");
        Button human_policy = new Button("human policy");
        Button back = new Button("Back");

        // create a stack pane
        GridPane r = new GridPane();
        // add button
        //r.add(instructions,0,0,4,0);
        r.add(simulation,0,1);
        r.add(population,1,1);
        r.add(human_policy,2,1);
        r.add(virus,3,1);
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
                windows[3].show();

            }
        });

        human_policy.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[1].hide();
                windows[4].show();

            }
        });
        virus.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[1].hide();
                windows[5].show();

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

    private void simulation(){
        windows[2] = new Stage();
        windows[2].setTitle("Settings");
        Label number = new Label("Number of Days in Simulation");
        Button back = new Button("Back");
        Button apply = new Button("Apply");
        TextField days = new TextField("10");
        // create a stack pane
        GridPane r = new GridPane();
        //default value for variables add so nothing breaks if it isn't changed
        values[key.get("Nday")] = 10;

        r.add(back,0,1);
        r.add(days,1,0);
        r.add(apply,2,0);
        r.add(number,0,0);
        r.setAlignment(Pos.CENTER);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[2].hide();
                windows[1].show();

            }
        });
        apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("Nday")] = Integer.parseInt( days.getText());

            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[2].setScene(sc);


    }

    private void population(){
        windows[3] = new Stage();
        windows[3].setTitle("Population");
        Button back = new Button("Back");

        Label facL = new Label("Number of faculty");
        Button facB = new Button("Apply");
        TextField fac = new TextField("219");

        Label dayL = new Label("Number of day students");
        Button dayB = new Button("Apply");
        TextField day = new TextField("159");

        Label boardL = new Label("Number of boarding students");
        Button boardB = new Button("Apply");
        TextField board = new TextField("378");
        // create a stack pane
        GridPane r = new GridPane();
        //default value for variables add so nothing breaks if it isn't changed
        values[key.get("fac")]  = 219;
        values[key.get("board")]  = 378;
        values[key.get("day")]  = 159;

        r.add(back,0,3);
        r.add(fac,1,0);
        r.add(facB,2,0);
        r.add(facL,0,0);

        r.add(day,1,1);
        r.add(dayB,2,1);
        r.add(dayL,0,1);

        r.add(board,1,2);
        r.add(boardB,2,2);
        r.add(boardL,0,2);
        r.setAlignment(Pos.CENTER);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[3].hide();
                windows[1].show();

            }
        });

        facB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("fac")] = Integer.parseInt( fac.getText());

            }
        });
        dayB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("day")] = Integer.parseInt( day.getText());

            }
        });
        boardB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("board")] = Integer.parseInt( board.getText());


            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[3].setScene(sc);
    }

    private void policy(){
        windows[4] = new Stage();
        windows[4].setTitle("policy");
        Label number = new Label("50.00%");
        Label vac = new Label("Vaccination Percentage");
        Button back = new Button("Back");
        Slider vacE = new Slider(0,100,50);
        final ComboBox masks = new ComboBox();
        // create a stack pane
        GridPane r = new GridPane();
        //default value for variables add so nothing breaks if it isn't changed
        values[key.get("vacP")] = 5000;
        values[key.get("maskT")] = 0;

        masks.getItems().addAll(
                "Surgical",
                "Cloth",
                "N95",
                "none"
        );
        masks.setValue(masks.getItems().get(0));


        r.add(back,0,2);
        r.add(vacE,1,0);
        r.add(vac,0,0);
        r.add(number,2,0);
        r.add(masks,0,1);
        r.setAlignment(Pos.CENTER);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[4].hide();
                windows[1].show();

            }
        });
        vacE.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                number.setText(String.format("%.2f%s", new_val,"%"));
                values[key.get("vacP")] = (int)(new_val.doubleValue()*100);
            }
        });
        masks.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if ('S' ==masks.getValue().toString().charAt(0)){
                    values[key.get("maskT")] = 0;
                }else if ('C' ==masks.getValue().toString().charAt(0)){
                    values[key.get("maskT")] = 1;
                }else if ('N' ==masks.getValue().toString().charAt(0)){
                    values[key.get("maskT")] = 2;
                }else if ('n' ==masks.getValue().toString().charAt(0)){
                    values[key.get("maskT")] = 3;
                }


            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[4].setScene(sc);


    }

    private void virus(){
        windows[5] = new Stage();
        windows[5].setTitle("virus");
        Button back = new Button("Back");
        final ComboBox vType = new ComboBox();
        // create a stack pane
        GridPane r = new GridPane();
        //default value for variables add so nothing breaks if it isn't changed
        values[key.get("virusT")] = 0;

        vType.getItems().addAll(
                "Omicron",
                "Delta"

        );
        vType.setValue(vType.getItems().get(0));


        r.add(back,0,2);
        r.add(vType,0,1);
        r.setAlignment(Pos.CENTER);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[5].hide();
                windows[1].show();

            }
        });

        vType.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if ('O' ==vType.getValue().toString().charAt(0)){
                    values[key.get("virusT")] = 0;
                }else if ('D' ==vType.getValue().toString().charAt(0)){
                    values[key.get("virusT")] = 1;
                }

            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[5].setScene(sc);


    }

}
