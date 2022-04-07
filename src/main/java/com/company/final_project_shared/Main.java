package com.company.final_project_shared;

public class Main {
    static Simulation Sim = new Simulation();
    public static void main(String[] args){
        for(int i=0; i<10; i++){
            Sim.day();
        }
    }
}
