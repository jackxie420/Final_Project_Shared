package com.company.final_project_shared;

public class Mask {

    Double mask_effectiveness(int type){
        double effectiveness = 1-0;
        if (type==0){//cloth
            effectiveness = 1-0.56;
        }
        else if (type==1){//surgical
            effectiveness = 1-0.66;
        }
        else if(type==2){//KN95
            effectiveness = 1-0.83;
        }
        return effectiveness;
    }
}