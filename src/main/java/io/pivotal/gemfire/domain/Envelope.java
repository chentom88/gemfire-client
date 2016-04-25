package io.pivotal.gemfire.domain;

import com.gemstone.gemfire.pdx.PdxReader;
import com.gemstone.gemfire.pdx.PdxSerializable;
import com.gemstone.gemfire.pdx.PdxWriter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pivotal on 4/20/16.
 */
public class Envelope implements PdxSerializable{


    private String key;
    private String origin;
    private String eventType;
    private Date timestamp;
    private Date continousQueryReceivedTimestamp;

    public Date getContinousQueryReceivedTimestamp() {
        return continousQueryReceivedTimestamp;
    }

    public void setContinousQueryReceivedTimestamp(Date continousQueryReceivedTimestamp) {
        this.continousQueryReceivedTimestamp = continousQueryReceivedTimestamp;
    }

    private ContainerMetric containerMetric;

    public Envelope() {

    }

    public Envelope(String key, String origin, String eventType, Date timestamp, ContainerMetric containerMetric) {
        this.key = key;
        this.origin = origin;
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.containerMetric = containerMetric;
    }

    public void toData(PdxWriter writer) {
        writer.writeString("key", key)
                .writeString("origin", origin)
                .writeString("eventType", eventType)
                .writeDate("timestamp", timestamp)
                .writeObject("containerMetric", containerMetric);
    }

    public void fromData(PdxReader reader) {
        key = reader.readString("key");
        origin = reader.readString("origin");
        eventType = reader.readString("eventType");
        timestamp = reader.readDate("timestamp");
        containerMetric = (ContainerMetric) reader.readObject("containerMetric");
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ContainerMetric getContainerMetric() {
        return containerMetric;
    }

    public void setContainerMetric(ContainerMetric containerMetric) {
        this.containerMetric = containerMetric;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "key='" + key + '\'' +
                ", origin='" + origin + '\'' +
                ", eventType='" + eventType + '\'' +
                ", timestamp=" + timestamp +
                ", continousQueryReceivedTimestamp=" + continousQueryReceivedTimestamp +
                ", containerMetric=" + containerMetric +
                '}';
    }
}
