package com.company.final_project_shared;

public class Main {
    static Simulation Sim;

    static {
        try {
            Sim = new Simulation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

    }
}

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
