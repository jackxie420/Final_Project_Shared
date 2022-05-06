package com.company.final_project_shared;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileSettings {
    Scanner read;
    ArrayList<Integer> stats= new ArrayList<>(0);

    ReadFileSettings(String loc){
        try{
            read = new Scanner(new FileInputStream(loc));
        }catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    public void retrieve(){
        while (read.hasNextLine()){
            String[] temp = read.nextLine().split("|");
            for (int i = 0; i < 11; i++) {
                stats.add(Integer.parseInt(temp[i]));
            }
        }
    }

    public ArrayList<Integer> get(){return  stats;}

    public void close(){read.close();}
}
