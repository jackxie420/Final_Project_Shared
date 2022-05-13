package com.company.final_project_shared;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {


    private static FileWriter myWriter = null;

    WriteFile(String loc){
        try{
            myWriter= new FileWriter(loc);
        }catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    public static void write_SI(int S, int I){
        try{
            System.out.println("writting");
            myWriter.write(S+" "+I+"\n");
        }catch(IOException e){
            System.out.println("Output Error write_SI");
            System.exit(0);
        }

    }

    public static void close_writer(){
        try{
            myWriter.flush();
            myWriter.close();
        }catch(IOException e){
            System.out.println("Output Error close File");
            System.exit(0);
        }
    }
}
