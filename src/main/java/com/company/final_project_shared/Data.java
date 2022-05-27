package com.company.final_project_shared;

import java.util.ArrayList;


public class Data {
    ArrayList total_SI = new ArrayList();
    
    public void SI_change(int S_c, int I_c){
        //System.out.println(S_c+" "+I_c);
        int[] daily_SI = {S_c, I_c};
        total_SI.add(daily_SI);
    }

    public ArrayList get_SI_stat(){

        return total_SI;
    }
    public int[] get_SI_day(int day){

        return (int[]) total_SI.get(day);
    }

}

