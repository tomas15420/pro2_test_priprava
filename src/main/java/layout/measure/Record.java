package layout.measure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "measure")

public class Record {
    @XmlElement(name = "time")
    private int time;
    @XmlElement(name = "localtime")
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime localTime;
    private final static int DISTANCE = 1600;

    public Record(){

    }

    public Record(int time, LocalTime localTime){
        this.time = time;
        this.localTime = localTime;
    }

    public int getTime() {
        return time;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public double getSpeedKMH(){
        return DISTANCE/time*3.6;
    }

    public double getSpeedMS(){
        return DISTANCE/(double)time;
    }
}
