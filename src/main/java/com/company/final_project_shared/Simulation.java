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
    private static People P_library;
    private static Random rand = new Random();
    private static Mask M_lib = new Mask();
    private static Vac V_lib = new Vac();
    private static Statistics Stat_lib = new Statistics();
    private static int num_academic_interaction = 7;
    private static int num_dhall_interaction = 3;
    private static int num_extracurricular_interaction = 5;
    private static int num_boarding_interaction = 3;
    private static double base_infection_rate=0.8;
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
    private static double vac_percentage;
    private static int covid_testing_interval=5;
    private static int testing_status=0;






    public Simulation() throws Exception {
        //GraphRun();
        settingsRun();
        FileWriter= new WriteFile("information.txt");
        P_library = new People(number_of_people,number_of_boarders,number_of_students,setStat[4]/10000.0,setStat[5], base_infection_rate, recovered_base_rate,setStat[18]/100.0);
        //P_library = new People(number_of_people,number_of_boarders,number_of_students,.50,-1);

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

        if(P_library.get_doi(one)==Date||P_library.get_isolation(one)==0){
            return;
        }
        if(P_library.get_doi(two)==Date||P_library.get_isolation(two)==0){
            return;
        }

        if((P_library.get_state(one)!=P_library.get_state(two)&&P_library.get_state(one)==1)){
            double base_rate= P_library.get_base_rate(two);
            if(modified_transmission_rate!=-1){
                base_rate=modified_transmission_rate;
            }
            //System.out.println("si , inter, mask: "+P_library.get_mask(two));
            base_rate*=V_lib.vac_effectiveness(P_library.get_vaccination(two))*M_lib.mask_effectiveness(P_library.get_mask(two));

            //System.out.println(base_rate);
            Double rand_rate = rand.nextDouble();
            if (rand_rate<base_rate){
                P_library.set_state(two,1);
                P_library.set_doi(two,Date);
            }
        }
    }

    private static void check_doi(){
        int sus_num = 0;
        int inf_num = 0;
        for (int i = 0; i < number_of_people; i++) {
            //P_library.get_attributes(i).set(6,NormalDist(Date - (int)P_library.get_attributes(i).get(5)));
            //if(i==0) System.out.println("date: "+Date+" person 0 rate: "+P_library.get_attributes(i).get(6));
            if((P_library.get_state(i)==1&&(Date - P_library.get_doi(i))>5) ){
                //P_library.get_attributes(i).set(6,NormalDist(Date - (int)P_library.get_attributes(i).get(5)));
                P_library.set_state(i,0);
                P_library.set_isolation(i,1);
                P_library.set_base_rate(i,recovered_base_rate);
            }
            if(P_library.get_state(i) != 1){
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
                while(receiver_idx==i||P_library.get_isolation(receiver_idx)==0){
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
                while (receiver_idx == i||P_library.get_isolation(receiver_idx)==0) {
                    receiver_idx = rand.nextInt(number_of_people);
                }
                interact(i, receiver_idx,-1);
            }
        }
    }

    private static void sim_boarding(){
        for(int i=0; i<number_of_boarders; i++){
            for(int j=0; j<num_boarding_interaction;j++){
                int receiver_idx = rand.nextInt(number_of_boarders);
                while(receiver_idx==i||P_library.get_isolation(receiver_idx)==0){
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
                while(receiver_idx==i||P_library.get_isolation(receiver_idx)==0){
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
                while(receiver_idx==i||P_library.get_isolation(receiver_idx)==0){
                    receiver_idx = rand.nextInt(number_of_extracurricular);
                }
                interact(i, receiver_idx, extracurricular_base_rate);
            }
        }
    }

    private static void covid_testing(int interval){
        if(Date % interval == 0){
            for (int i = 0; i < number_of_people; i++) {
                if (P_library.get_isolation(i) == 1){
                    if (P_library.get_state(i) == 1){
                        if (coreectly_tested * Math.random()*10000<99)
                        P_library.set_isolation (i,0);
                        P_library.set_doi(i, Date);
                    }
                    //
                    //doi remove from quarantine
                }
            }
        }
    }

    private static void rapid_tests(){
        for(int i = 0; i<number_of_people; i++){
            if (P_library.get_state(i)==1){
                if(P_library.get_isolation(i)==1){
                    double rate = rand.nextDouble();
                   if (rate<rapidtest_base_rate ){
                       //System.out.println("double rate "+rapidtest_base_rate);
                       P_library.set_isolation(i,0);
                       P_library.set_doi(i,Date);
                   }
                }
            }
        }
    }

    private static void real_sim(){

        sim_academic();
        sim_extracurricular();
        sim_boarding();
        sim_dhall();
        if(testing_status==2||testing_status==3) covid_testing(covid_testing_interval);
        if(testing_status==1||testing_status==3) rapid_tests();



    }

    private static void day() throws Exception {
        //sim_stage();
        real_sim();

        check_doi();
        FileWriter.write_SI(Stat_lib.get_SI_day(Date)[0],Stat_lib.get_SI_day(Date)[1]);
        System.out.println("SI Info: "+Stat_lib.get_SI_day(Date)[0]+" "+Stat_lib.get_SI_day(Date)[1]);
        Date++;
        if(Date==Target_Date){
            //System.out.println("close file");
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
        number_of_extracurricular = setStat[6];
        num_dhall_interaction = setStat[7];
        num_extracurricular_interaction = setStat[8];
        num_boarding_interaction = setStat[9];
        num_academic_interaction = setStat[10];
        extracurricular_base_rate=setStat[11]/ 1000.0;
        recovered_base_rate=setStat[12]/ 1000.0;
        rapidtest_base_rate = setStat[13]/ 1000.0;
        coreectly_tested = setStat[14]/ 1000.0;
        base_infection_rate = setStat[15]/ 1000.0;
        covid_testing_interval=setStat[16];
        testing_status=setStat[17];

        System.out.println("Aaaa"+setStat[5]);

    }

}
