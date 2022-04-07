package com.company.final_project_shared;

public class Vac {
    Double vac_effectiveness(int type){
        double effectiveness = 1-0;
        if (type==0){
            effectiveness = 1-0.85;
        }
        else if(type==1){
            effectiveness = 1-0.85;
        }
        return effectiveness;
    }
}
