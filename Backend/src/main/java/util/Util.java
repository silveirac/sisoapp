package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;

public class Util {
    public static Timestamp dateToTimestamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;

    }
//    public static void escreveLog(String msg) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Log_SisoApp_"+ LocalDate.now()+".txt",true));
//        bufferedWriter.append(LocalDateTime.now()+": "+msg);
//        bufferedWriter.newLine();
//        bufferedWriter.close();
//    }
}
