package com.company.final_project_shared;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFileSettings {


    private static FileWriter myWriter = null;

    WriteFileSettings(String loc){
        try{
            myWriter= new FileWriter(loc);
        }catch(IOException e){
            System.out.println("Output Error constructor");
            System.exit(0);
        }
    }

    public static void write_SI(ArrayList<Integer> stat){
        try{
            for (int i : stat) {
                myWriter.write(i+"|");
            }

        }catch(IOException e){
            System.out.println("Output Error write_SI");
            System.exit(0);
        }

    }

    public static void close_writer(){
        try{
            myWriter.close();
        }catch(IOException e){
            System.out.println("Output Error close File");
            System.exit(0);
        }
    }
}
