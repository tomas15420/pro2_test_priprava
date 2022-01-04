package layout.saver;

import layout.measure.Record;
import layout.measure.RecordList;

import java.util.ArrayList;
import java.util.List;

public interface RecordsSaver {
    void saveRecords(String path, List<Record> records);
    List<Record> loadRecords(String path);
}
