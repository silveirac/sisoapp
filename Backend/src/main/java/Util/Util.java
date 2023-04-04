package Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;

public class Util {
    public static void escreveLog(String msg){
        try{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/logs/Log_SisoApp_"+ LocalDate.now()+".txt",true));
        bufferedWriter.append(LocalDateTime.now()+": "+msg);
        bufferedWriter.newLine();
        bufferedWriter.close();}
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
