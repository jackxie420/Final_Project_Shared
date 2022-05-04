package com.company.final_project_shared;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    Scanner read;
    ArrayList<int[]> stats= new ArrayList<>(0);

    ReadFile(String loc){
        try{
            read = new Scanner(new FileInputStream(loc));
        }catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    public void retrieve(){
        while (read.hasNextLine()){
            String[] temp = read.nextLine().split(" ");
            stats.add(new int[] {Integer.parseInt(temp[0]), Integer.parseInt(temp[1])} );
        }
    }

    public ArrayList<int[]> get(){return  stats;}

    public void close(){read.close();}
}
