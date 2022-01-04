package layout.saver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import layout.measure.Record;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordSaverJson implements RecordsSaver{
    GsonBuilder builder;
    public RecordSaverJson(){
        builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalTime.class, new TypeAdapter<LocalTime>() {
            @Override
            public void write(JsonWriter out, LocalTime value) throws IOException {
//                out.beginObject();
//                out.name("time");
//                out.value(value.toString());
//                out.endObject();

                out.value(value.toString());
            }

            @Override
            public LocalTime read(JsonReader in) throws IOException {
//                in.beginObject();
//                in.nextName();
//                LocalTime lt = LocalTime.parse(in.nextString());
//                in.endObject();

                return LocalTime.parse(in.nextString());
            }
        });
    }

    @Override
    public void saveRecords(String path, List<Record> records) {
        String content = builder.create().toJson(records);
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+".json"));
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> loadRecords(String path) {
        Gson gson = builder.create();
        try{
            JsonReader jsonReader = gson.newJsonReader(new FileReader(path));
            ArrayList<Record> records;
            records = gson.fromJson(jsonReader,new TypeToken<ArrayList<Record>>(){}.getType());
            return records;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
