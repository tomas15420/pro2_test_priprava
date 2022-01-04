package layout.saver;

import layout.measure.Record;
import layout.measure.RecordList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class RecordSaverXML implements RecordsSaver{
    @Override
    public void saveRecords(String path, List<Record> records){
        try{
            JAXBContext context = JAXBContext.newInstance(RecordList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            RecordList recordList = new RecordList();
            recordList.setRecordList(records);
            marshaller.marshal(recordList,new File(path+".xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> loadRecords(String path) {
        try{
            JAXBContext context = JAXBContext.newInstance(RecordList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RecordList recordList = (RecordList)unmarshaller.unmarshal(new File(path));
            return recordList.getRecordList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
