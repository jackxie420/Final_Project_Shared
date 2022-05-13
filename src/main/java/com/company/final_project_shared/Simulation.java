package com.company.final_project_shared;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Simulation {

    private static int number_of_people = 756;
    private static int number_of_students = 537;
    private static int number_of_boarders = 377;
    private static int number_of_faculties = 219;
    private static int number_of_extracurricular = 619;
    private static People P_library ;
    private static Random rand = new Random();
    private static Mask M_lib = new Mask();
    private static Vac V_lib = new Vac();
    private static Statistics Stat_lib = new Statistics();
    private static int num_interaction = 7;
    private static int num_academic_interaction = 7;
    private static int num_academic_s_interaction = 10;
    private static int num_academic_t_interaction = 5;
    private static int num_academic_st_interaction = 5;
    private static int num_dhall_interaction = 3;
    private static int num_extracurricular_interaction = 5;
    private static int num_boarding_interaction = 3;
    private static double extracurricular_base_rate=0.1;
    private static double recovered_base_rate=0.15;
    private static double rapidtest_base_rate = 0.222;
    private static double coreectly_tested = 0.99;
    private static int Date=0;//current date
    //"C:\\Users\\henry\\Desktop\\Compression\\problem2.txt"
    private static WriteFile FileWriter;
    private static ReadFile FileReader= new ReadFile("information.txt");
    private static int Target_Date=100;
    private static Settings setts = new Settings();
    private static int[] setStat;






    public Simulation() throws Exception {
        GraphRun();
        settingsRun();
        FileWriter= new WriteFile("information.txt");
        //System.out.println("a");
        initialize_stat();
        for(int i=0; i<Target_Date; i++){
            day();
        }
    }
    private void GraphRun() throws Exception {
        FileReader.retrieve();
        Stat_lib.total_SI = FileReader.get();
        SIRgraph graph = new SIRgraph();
        graph.first();
        System.exit(0);
    }

    private void settingsRun() {
        setts.begin();
        settingsChoice();
        P_library = new People(number_of_people,number_of_boarders,number_of_students,setStat[4]/10000.0,setStat[5]);
    }

    private static double NormalDist(int x){
        double sd=3.45;
        double mean=4;
        double peak_trans_rate=0.8;
        double x_0_value=1/(sd*Math.sqrt(2*Math.PI))*Math.pow(Math.E, -0.5* ((mean-mean)/sd) * ((x-mean)/sd)) ;

        double res=1/(sd*Math.sqrt(2*Math.PI))*Math.pow(Math.E, -0.5* ((x-mean)/sd) * ((x-mean)/sd)) ;
        return peak_trans_rate/x_0_value*res;
    }

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

    private static void interact(int one, int two, double modified_transmission_rate){

        //P_library.input_attributes(0,1,1,1,1,1);
        //P_library.input_attributes(1,1,1,1,1,0);

        ArrayList person_one=P_library.get_attributes(one);
        ArrayList person_two=P_library.get_attributes(two);

        if((int)person_one.get(5)==Date||(int)person_one.get(7)==0){
            return;
        }
        if((int)person_two.get(5)==Date||(int)person_two.get(7)==0){
            return;
        }

        if((person_one.get(4)!=person_two.get(4))&&(int)person_one.get(4)==1){
            double base_rate=(double)person_two.get(6);
            if(modified_transmission_rate!=-1){
                base_rate=modified_transmission_rate;
            }
            base_rate*=V_lib.vac_effectiveness((int)person_two.get(3))*M_lib.mask_effectiveness((int)person_two.get(2));

            //System.out.println(base_rate);
            Double rand_rate = rand.nextDouble();
            if (rand_rate<base_rate){
                person_two.set(4,1);
                person_two.set(5,Date);
                P_library.set_person_attributes(two, person_two);
            }
        }
    }

    private static void check_doi(){
        int sus_num = 0;
        int inf_num = 0;
        for (int i = 0; i < number_of_people; i++) {
            //P_library.get_attributes(i).set(6,NormalDist(Date - (int)P_library.get_attributes(i).get(5)));
            //if(i==0) System.out.println("date: "+Date+" person 0 rate: "+P_library.get_attributes(i).get(6));
            if((Date - (int)P_library.get_attributes(i).get(5))>5) {
                //P_library.get_attributes(i).set(6,NormalDist(Date - (int)P_library.get_attributes(i).get(5)));
                P_library.get_attributes(i).set(4,0);
                P_library.get_attributes(i).set(6,recovered_base_rate);
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
            for(int j=0; j<6; j++){
                int receiver_idx= rand.nextInt(number_of_people);
                while(receiver_idx==i){
                    receiver_idx= rand.nextInt(number_of_people);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

    private static void sim_stage_lambda(){

        //IntStream.range(0, number_of_people).range(0, num_interaction).map(f->{int idx = rand.nextInt(number_of_people);interact(idx,idx)});

        for(int i=0; i<number_of_people; i++){
            for(int j=0; j<num_interaction; j++){
                int receiver_idx= rand.nextInt(number_of_people);
                while(receiver_idx==i){
                    receiver_idx= rand.nextInt(number_of_people);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

    private static void sim_academic() {
        for (int i = 0; i < number_of_people; i++) {
            for (int j = 0; j < num_academic_interaction; j++) {
                int receiver_idx = rand.nextInt(number_of_people);
                while (receiver_idx == i) {
                    receiver_idx = rand.nextInt(number_of_people);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

/*
    //academic  student

    private static void sim_academic_s() {
        for (int i = 0; i < number_of_students; i++) {
            for (int j = 0; j < num_academic_s_interaction; j++) {
                int receiver_idx = rand.nextInt(number_of_students);
                while (receiver_idx == i) {
                    receiver_idx = rand.nextInt(number_of_students);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

 */
/*

    private static void sim_academic_t() {
        for (int i = number_of_students; i < number_of_students+number_of_faculties; i++) {
            for (int j = 0; j < num_academic_t_interaction; j++) {
                int receiver_idx = rand.nextInt(number_of_faculties)+number_of_students;
                while (receiver_idx == i) {
                    receiver_idx = rand.nextInt(number_of_faculties)+number_of_students;
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

 */
/*
    private static void sim_academic_st() {
        for (int i = 0; i < number_of_people; i++) {
            for (int j = 0; j < num_academic_st_interaction; j++) {
                int receiver_idx = rand.nextInt(number_of_people);
                while (receiver_idx == i) {
                    receiver_idx = rand.nextInt(number_of_people);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }
*/

    //boarding 377
    private static void sim_boarding(){
        for(int i=0; i<number_of_boarders; i++){
            for(int j=0; j<num_boarding_interaction;j++){
                int receiver_idx = rand.nextInt(number_of_boarders);
                while(receiver_idx==i){
                    receiver_idx = rand.nextInt(number_of_boarders);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

    private static void sim_dhall(){
        for(int i=0; i<number_of_people; i++){
            for(int j=0; j<num_dhall_interaction;j++){
                int receiver_idx = rand.nextInt(number_of_people);
                while(receiver_idx==i){
                    receiver_idx = rand.nextInt(number_of_people);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

    private static void sim_extracurricular(){

        for(int i=0; i<number_of_extracurricular; i++){
            for(int j=0; j<num_extracurricular_interaction;j++){
                int receiver_idx = rand.nextInt(number_of_extracurricular);
                while(receiver_idx==i){
                    receiver_idx = rand.nextInt(number_of_extracurricular);
                }
                interact(i, receiver_idx, extracurricular_base_rate);
            }
        }
    }

    private static void covid_testing(int interval){
        while(Date % interval == 0){
            for (int i = 0; i < number_of_people; i++) {
                if ((int)P_library.get_attributes(i).get(7) == 1){
                    if ((int)P_library.get_attributes(i).get(4) == 1){
                        if (coreectly_tested * Math.random()*10000<99)
                        P_library.get_attributes(i).set(7,0);
                    }
                    //
                    //doi remove from quarantine
                }
            }
        }
    }

    private static void rapid_tests(int number_of_symptomatic_people){
        for(int i = 0; i<number_of_people; i++){
            if ((int)P_library.get_attributes(i).get(4)==1){
                if((int)P_library.get_attributes(i).get(7)==1){
                   // if (rapidtest_base_rate * rand.nextInt() )

//rapidtest_base_rate
// .6*.37
                    //set date to current date if test positive
                    //quarantine
                    //another if. if already in quarantine nothing applies
                }
            }
        }
    }

    private static void real_sim(){

        sim_academic();
        /*

        sim_extracurricular();
        sim_boarding();
        sim_dhall();


         */
    }

    private static void day() throws Exception {
        sim_stage();
        //real_sim();

        check_doi();
        FileWriter.write_SI(Stat_lib.get_SI_day(Date)[0],Stat_lib.get_SI_day(Date)[1]);
        //System.out.println("SI Info: "+Stat_lib.get_SI_day(Date)[0]+" "+Stat_lib.get_SI_day(Date)[1]);
        Date++;
        if(Date==Target_Date){
            FileWriter.close_writer();
            //Grapher.update(Stat_lib.get_SI_stat());
            //Grapher.first();
            //SIRgraph graph = new SIRgraph();
            //System.out.println("A");
            //graph.first();
            //Grapher.update(Stat_lib.get_SI_stat());

        }
    }

    public static ArrayList<int[]> getStat(){
        return Stat_lib.get_SI_stat();
    }

    public static void setStats(int[] statts){
        setStat = statts;
    }

    private static void settingsChoice(){
        Target_Date= setStat[0];

        number_of_students = setStat[1]+setStat[2];
        number_of_boarders = setStat[1];
        number_of_faculties = setStat[3];
        number_of_people = setStat[1]+setStat[2]+setStat[3];

    }

}
