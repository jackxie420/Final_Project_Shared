package com.company.final_project_shared;
import java.util.Random;
import java.util.ArrayList;
public class Simulation {

    private static int number_of_people = 500;
    private static Random rand = new Random();
    private static People P_library = new People(number_of_people);
    private static Mask M_lib = new Mask();
    private static Vac V_lib = new Vac();
    private static Statistics Stat_lib = new Statistics();
    private static int num_interaction = 7;
    private static int Date=0;
    //private static SIRgraph graph = new SIRgraph();

    void initialize_stat(){
        int tot_S=0;
        int tot_I=0;
        for(int i=0; i<number_of_people; i++){
            if((int)P_library.get_attributes(i).get(4)==0){
                tot_S++;
            }else{
                tot_I++;
            }
        }
        Stat_lib.SI_change(tot_S, tot_I);
        //graph.update(Stat_lib.get_SI_stat());
    }

    private static void interact(int one, int two){

        //P_library.input_attributes(0,1,1,1,1,1);
        //P_library.input_attributes(1,1,1,1,1,0);

        ArrayList person_one=P_library.get_attributes(one);
        ArrayList person_two=P_library.get_attributes(two);

        if((person_one.get(4)!=person_two.get(4))&&(int)person_one.get(4)==1){
            double base_rate=0.8;
            base_rate*=V_lib.vac_effectiveness((int)person_two.get(3))*M_lib.mask_effectiveness((int)person_two.get(2));
            //System.out.println(base_rate);
            Double rand_rate = rand.nextDouble();
            if (rand_rate<base_rate){
                person_two.set(4,1);
                person_two.set(5,Date);
            }
        }
    }

    private static void check_doi(){
        int sus_num = 0;
        int inf_num = 0;
        for (int i = 0; i < number_of_people; i++) {
            if((Date - (int)P_library.get_attributes(i).get(5))>5) {
                P_library.get_attributes(i).set(4,0);
            }
            if((int)P_library.get_attributes(i).get(4) != 1){
                sus_num++;
            }else {
                inf_num++;
            }
        }
        Stat_lib.SI_change(sus_num, inf_num);
        //graph.update(Stat_lib.get_SI_stat());
    }

    private static void sim_stage(){
        for(int i=0; i<number_of_people; i++){
            for(int j=0; j<num_interaction; j++){
                int receiver_idx= rand.nextInt(number_of_people);
                while(receiver_idx==j){
                    receiver_idx= rand.nextInt(number_of_people);
                }
                interact(j, receiver_idx);
            }
        }
    }

    public static void day(){
        sim_stage();
        check_doi();
        System.out.println(Stat_lib.get_SI_day(Date)[0]+" "+Stat_lib.get_SI_day(Date)[1]);
        //graph.first();
        Date++;
    }
}
