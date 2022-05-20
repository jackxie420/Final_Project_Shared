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


        number of people in extracurricular
        interaction academic
        interaction dhall
        boarding
        testing

        number_of_extracurricular

        num_academic_interaction = 7;
        num_dhall_interaction = 3;
        num_extracurricular_interaction = 5;
        num_boarding_interaction = 3;
        extracurricular_base_rate=0.1;
        recovered_base_rate=0.15;
        rapidtest_base_rate = 0.222;
        coreectly_tested = 0.99;

 */
/*
values key
0 number of days in simulation
1 number of boarding students
2 number of day students
3 number of faculty
4 percentage vaccinated  divide by 100 to get percentage
5 type mandated 0 surgical 1 cloth 2 N95 -1 none
6 participants in co curricular
7 number of dinning hall interactions
8 number of extra cocurricular interactions
9 number of boarding interactions
10 number of academic interactions
11 extracurricular_base_rate / 100 for true value
12 recovered_base_rate / 100 for true value
13 rapidtest_base_rate / 100 for true value
14 coreectly_tested / 100 for true value

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
        key.put("ExtracP",6);
        key.put("dhallI",7);
        key.put("exI",8);
        key.put("boardI",9);
        key.put("acaI",10);
        key.put("exBR",11);
        key.put("recovBR",12);
        key.put("rapidBR",13);
        key.put("correctT",14);


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
        TextField days = new TextField("100");
        // create a stack pane
        GridPane r = new GridPane();
        //default value for variables add so nothing breaks if it isn't changed
        values[key.get("Nday")] = 100;

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

        Label exL = new Label("Number of extracurricular participants");
        Button exB = new Button("Apply");
        TextField ex = new TextField("619");
        // create a stack pane
        GridPane r = new GridPane();
        //default value for variables add so nothing breaks if it isn't changed
        values[key.get("fac")]  = 219;
        values[key.get("board")]  = 378;
        values[key.get("day")]  = 159;
        values[key.get("ExtracP")]  = 159;

        r.add(back,0,4);
        r.add(fac,1,0);
        r.add(facB,2,0);
        r.add(facL,0,0);

        r.add(day,1,1);
        r.add(dayB,2,1);
        r.add(dayL,0,1);

        r.add(board,1,2);
        r.add(boardB,2,2);
        r.add(boardL,0,2);

        r.add(ex,1,3);
        r.add(exB,2,3);
        r.add(exL,0,3);
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

        exB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("ExtracP")] = Integer.parseInt( ex.getText());


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
        Slider vacE = new Slider(0,100,50);
        Button back = new Button("Back");
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
        Label facL = new Label("Number of dinning hall interactions");
        Button facB = new Button("Apply");
        TextField fac = new TextField("3");

        Label dayL = new Label("Number of extracurricular interactions");
        Button dayB = new Button("Apply");
        TextField day = new TextField("5");

        Label boardL = new Label("Number of boarding interactions");
        Button boardB = new Button("Apply");
        TextField board = new TextField("3");

        Label exL = new Label("Number of academic interactions");
        Button exB = new Button("Apply");
        TextField ex = new TextField("7");

        //sliders

        Label number = new Label("0.100");
        Label vac = new Label("extracurricular base rate");
        Slider vacE = new Slider(0,1,.1);

        Label n2 = new Label("0.150");
        Label nn2 = new Label("recovered base rate");
        Slider s2 = new Slider(0,1,.15);

        Label n3 = new Label("0.222");
        Label nn3 = new Label("rapid test base rate");
        Slider s3 = new Slider(0,1,.222);

        Label n4 = new Label("0.990");
        Label nn4 = new Label("PCR accuracy");
        Slider s4 = new Slider(0,1,.99);


        // create a stack pane
        GridPane r = new GridPane();
        //default value for variables add so nothing breaks if it isn't changed
        values[key.get("dhallI")]  = 3;
        values[key.get("exI")]  = 5;
        values[key.get("boardI")]  = 3;
        values[key.get("acaI")]  = 7;
        values[key.get("exBR")] = 100;
        values[key.get("recovBR")] = 150;
        values[key.get("rapidBR")] = 222;
        values[key.get("correctT")] = 990;

        r.add(back,0,4);
        r.add(fac,1,0);
        r.add(facB,2,0);
        r.add(facL,0,0);

        r.add(day,1,1);
        r.add(dayB,2,1);
        r.add(dayL,0,1);

        r.add(board,1,2);
        r.add(boardB,2,2);
        r.add(boardL,0,2);

        r.add(ex,1,3);
        r.add(exB,2,3);
        r.add(exL,0,3);

        r.add(vacE,1,5);
        r.add(vac,0,5);
        r.add(number,2,5);

        r.add(s2,1,6);
        r.add(nn2,0,6);
        r.add(n2,2,6);

        r.add(s3,1,7);
        r.add(nn3,0,7);
        r.add(n3,2,7);

        r.add(s4,1,8);
        r.add(nn4,0,8);
        r.add(n4,2,8);

        r.setAlignment(Pos.CENTER);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                windows[5].hide();
                windows[1].show();

            }
        });

        facB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("dhallI")] = Integer.parseInt( fac.getText());

            }
        });
        dayB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("exI")] = Integer.parseInt( day.getText());

            }
        });
        boardB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("boardI")] = Integer.parseInt( board.getText());


            }
        });

        exB.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                values[key.get("acaI")] = Integer.parseInt( ex.getText());


            }
        });

        vacE.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                number.setText(String.format("%.3f", new_val));
                values[key.get("exBR")] = (int)(new_val.doubleValue()*100);
            }
        });

        s2.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                n2.setText(String.format("%.3f", new_val));
                values[key.get("recovBR")] = (int)(new_val.doubleValue()*100);
            }
        });

        s3.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                n3.setText(String.format("%.3f", new_val));
                values[key.get("rapidBR")] = (int)(new_val.doubleValue()*100);
            }
        });

        s4.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                n4.setText(String.format("%.3f", new_val));
                values[key.get("correctT")] = (int)(new_val.doubleValue()*100);
            }
        });

        // create a scene
        Scene sc = new Scene(r, 800, 600);

        // set the scene
        windows[5].setScene(sc);


    }

}
