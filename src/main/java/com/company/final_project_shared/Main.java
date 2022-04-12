package com.company.final_project_shared;

public class Main {
    static int tar_date=100;
    static Simulation Sim = new Simulation(tar_date);
    public static void main(String[] args){
        for(int i=0; i<tar_date; i++){
            Sim.day();
        }
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
