package layout.saver;

import layout.measure.Record;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class RecordSaverCsv implements RecordsSaver{
    @Override
    public void saveRecords(String path, List<Record> records) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for(Record record : records){
                bufferedWriter.write(record.getLocalTime()+";"+record.getTime()+"\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("writed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Record> loadRecords(String path) {
        ArrayList<Record> list = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.lines().iterator().forEachRemaining((line)->{
                String[] data = line.split(";");
                try{
                    Record record = new Record(Integer.parseInt(data[1]),LocalTime.parse(data[0]));
                    list.add(record);
                }catch (NumberFormatException | DateTimeParseException e){
                    e.printStackTrace();
                }
            });
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
