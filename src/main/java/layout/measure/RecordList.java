package layout.measure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "measures")
public class RecordList {
    @XmlElement(name = "measure")
    List<Record> recordList = new ArrayList<>();

    public void addRecord(Record record){
        recordList.add(record);
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public void clear(){
        recordList.clear();
    }
}
