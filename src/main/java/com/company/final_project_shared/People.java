package com.company.final_project_shared;

import java.util.ArrayList;
import java.util.Random;

public class People {
     private Random rand = new Random();


    ArrayList people = null;
    int Num_People;

    //boarding/day faculty/student type of mask vaccination state susceptible/infected/recovered

    /*

    537 students
        378 boarder
        159 day students
    219 faculties in total
        82 teaching faculties
     */

    People(int num_people){
        this.Num_People=num_people;
        people = new ArrayList();
        for(int i=0; i<num_people; i++){
            people.add(new ArrayList<>());
        }
        initialization(0.1);
    }

    void input_attributes(int index, int residency, int occupation, int mask, int vaccination, int state, int doi, double base_rate){
        ArrayList attributes = new ArrayList<>();

        attributes.add(residency);
        attributes.add(occupation);// 1-boarder(index 0-377), 2-day(index 378-536), 3-faculty(index 537-755)
        attributes.add(mask);//-1 is unmasked
        attributes.add(vaccination);
        attributes.add(state);//0-not infected, 1-infected
        attributes.add(doi);// after 5 days, people become susceptible again
        attributes.add(base_rate);
        people.set(index, attributes);

    }

    void set_person_attributes(int idx, ArrayList attributes){
        people.set(idx, attributes);
    }

    ArrayList get_attributes(int index){
        return (ArrayList) people.get(index);
    }

    //random initialization
    void initialization(double initial_infected_prob){
        for (int i = 0; i < Num_People; i++) {
            int index=i;
            int residency=0;
            int occupation;
            if(i<=377){
                occupation=1;
            }else if(i<=536){
                occupation=2;
            }else{
                occupation=3;
            }
            int mask=rand.nextInt(3)-1;
            int vaccination=rand.nextInt(3)-1;
            int state;
            if(rand.nextDouble()<=initial_infected_prob){
                state=1;
            }else{
                state=0;
            }
            int doi=rand.nextInt(5)-5;
            double base_rate=0.8;
            input_attributes(index, residency, occupation,  mask,  vaccination,  state,  doi,  base_rate );
        }
    }

}