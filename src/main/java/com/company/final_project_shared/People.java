package com.company.final_project_shared;

import java.util.ArrayList;
import java.util.Random;

public class People {
     private Random rand = new Random();


    ArrayList people = null;
    int Num_People;
    int board;
    int students;
    double vacPercent;
    int maskMandate;
    double base_rate = 0.8;

    //boarding/day faculty/student type of mask vaccination state susceptible/infected/recovered

    /*

    537 students
        378 boarder
        159 day students
    219 faculties in total
        82 teaching faculties
     */

    People(int num_people,int board, int students,double vacPercent, int maskMandate, double inp_base_rate){
        this.base_rate=inp_base_rate;
        this.Num_People=num_people;
        this.board = board;
        this.students = students;
        this.vacPercent = vacPercent;
        this.maskMandate = maskMandate;
        people = new ArrayList();
        for(int i=0; i<num_people; i++){
            people.add(new ArrayList<>());
        }
        initialization(0.1);
    }

    void input_attributes(int index, int residency, int occupation, int mask, int vaccination, int state, int doi, double base_rate, int isolation){
        ArrayList attributes = new ArrayList<>();

        attributes.add(residency);
        attributes.add(occupation);// 1-boarder(index 0-377), 2-day(index 378-536), 3-faculty(index 537-755)
        attributes.add(mask);//-1 is unmasked
        attributes.add(vaccination);
        attributes.add(state);//0-not infected, 1-infected
        attributes.add(doi);// after 5 days, people become susceptible again
        attributes.add(base_rate);
        attributes.add(isolation);//0 isolation 1 is not isolation
        people.set(index, attributes);

    }

    void set_person_attributes(int idx, ArrayList attributes){
        people.set(idx, attributes);
    }

    ArrayList get_attributes(int index){
        return (ArrayList) people.get(index);
    }

    private double calc_base_rate(double center, double radius){
        double cal =((double) rand.nextInt((int)(radius*100*2)))/100 + center;
        //System.out.println(cal);
        return cal;
    }

    public int get_residency(int index_person){
        return (int) get_attributes(index_person).get(0);
    }
    public void set_residency(int index_person, int set){
        get_attributes(index_person).set(0, set);
    }
    public int get_occupation(int index_person){
        return (int) get_attributes(index_person).get(1);
    }
    public void set_occupation(int index_person, int set){
        get_attributes(index_person).set(1, set);
    }
    public int get_mask(int index_person){
        return (int) get_attributes(index_person).get(2);
    }
    public void set_mask(int index_person, int set){
        get_attributes(index_person).set(2, set);
    }
    public int get_vaccination(int index_person){
        return (int) get_attributes(index_person).get(3);
    }
    public void set_vaccination(int index_person, int set){
        get_attributes(index_person).set(3, set);
    }
    public int get_state(int index_person){
        return (int) get_attributes(index_person).get(4);
    }
    public void set_state(int index_person, int set){
        get_attributes(index_person).set(4, set);
    }
    public int get_doi(int index_person){
        return (int) get_attributes(index_person).get(5);
    }
    public void set_doi(int index_person, int set){
        get_attributes(index_person).set(5, set);
    }
    public double get_base_rate(int index_person){
        return (double) get_attributes(index_person).get(6);
    }
    public void set_base_rate(int index_person, double set){
        get_attributes(index_person).set(6, set);
    }
    public int get_isolation(int index_person){
        return (int) get_attributes(index_person).get(7);
    }
    public void set_isolation(int index_person, int set){
        get_attributes(index_person).set(7, set);
    }


    //random initialization
    void initialization(double initial_infected_prob) {
        for (int i = 0; i < Num_People; i++) {
            int index = i;
            int residency = 0;
            int occupation;
            if (i <= board) {
                occupation = 1;
            } else if (i <= students) {
                occupation = 2;
            } else {
                occupation = 3;
            }
            int mask = rand.nextInt(1) - 1;
            if(maskMandate == 4){
                mask = rand.nextInt(4) - 1;
            }else if(maskMandate == 1){
                mask = 0;
            } else if(maskMandate == 0){
                mask = 2;
            }else {mask = 3;}

            int vaccination ;
            if(rand.nextDouble()<=vacPercent){
                vaccination = 1;
            }else {
                vaccination = 0;
            }
            int state;
            if (rand.nextDouble() <= initial_infected_prob) {
                state = 1;
            } else {
                state = 0;
            }
            int doi = rand.nextInt(5) - 5;

            if(state==1) base_rate=0.15;
                    //calc_base_rate(0.8, 0.1);
            int isolation = 1;
            //System.out.println("base rate: "+base_rate);
            input_attributes(index, residency, occupation, mask, vaccination, state, doi, base_rate,isolation);
            
        }
    }

}