package com.company.final_project_shared;

import java.util.ArrayList;
import java.util.Random;

public class People {
     private Random rand = new Random();


    ArrayList people = null;
    int Num_People;

    //boarding/day faculty/student type of mask vaccination state susceptible/infected/recovered

    People(int num_people){
        this.Num_People=num_people;
        people = new ArrayList();
        for(int i=0; i<num_people; i++){
            people.add(new ArrayList<Integer>());
        }
        initialization();
    }

    void input_attributes(int index, int residency, int occupation, int mask, int vaccination, int state, int doi){
        ArrayList<Integer> attributes = new ArrayList<Integer>();

        attributes.add(residency);
        attributes.add(occupation);
        attributes.add(mask);//-1 is unmasked
        attributes.add(vaccination);
        attributes.add(state);//0-not infected, 1-infected
        attributes.add(doi);// after 5 days, people become susceptible again
        people.set(index, attributes);

    }

    ArrayList get_attributes(int index){
        return (ArrayList) people.get(index);
    }

    void initialization(){
        for (int i = 0; i < Num_People; i++) {
            input_attributes(i,rand.nextInt(1),rand.nextInt(1),rand.nextInt(3)-1,rand.nextInt(3)-1,rand.nextInt(2),rand.nextInt(5)-5 );
        }
    }
}