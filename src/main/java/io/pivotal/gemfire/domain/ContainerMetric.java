package io.pivotal.gemfire.domain;

import com.gemstone.gemfire.pdx.PdxReader;
import com.gemstone.gemfire.pdx.PdxSerializable;
import com.gemstone.gemfire.pdx.PdxWriter;

import java.io.Serializable;

/**
 * Created by pivotal on 4/20/16.
 */
public class ContainerMetric implements PdxSerializable {
    private String applicationId;
    private int instanceIndex;
    private float cpuPercentage;
    private long memoryBytes;
    private long diskBytes;

    public ContainerMetric() {
    }

    public ContainerMetric(String applicationId, int instanceIndex, float cpuPercentage, long memoryBytes, long diskBytes) {
        this.applicationId = applicationId;
        this.instanceIndex = instanceIndex;
        this.cpuPercentage = cpuPercentage;
        this.memoryBytes = memoryBytes;
        this.diskBytes = diskBytes;
    }

    public void toData(PdxWriter writer) {
        writer.writeString("applicationId", applicationId)
                .writeInt("instanceIndex", instanceIndex)
                .writeFloat("cpuPercentage", cpuPercentage)
                .writeLong("memoryBytes", memoryBytes)
                .writeLong("diskBytes", diskBytes);
    }

    public void fromData(PdxReader reader) {
        applicationId = reader.readString("applicationId");
        instanceIndex = reader.readInt("instanceIndex");
        cpuPercentage = reader.readFloat("cpuPercentage");
        memoryBytes = reader.readLong("memoryBytes");
        diskBytes = reader.readLong("diskBytes");
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public int getInstanceIndex() {
        return instanceIndex;
    }

    public void setInstanceIndex(int instanceIndex) {
        this.instanceIndex = instanceIndex;
    }

    public float getCpuPercentage() {
        return cpuPercentage;
    }

    public void setCpuPercentage(float cpuPercentage) {
        this.cpuPercentage = cpuPercentage;
    }

    public long getMemoryBytes() {
        return memoryBytes;
    }

    public void setMemoryBytes(long memoryBytes) {
        this.memoryBytes = memoryBytes;
    }

    public long getDiskBytes() {
        return diskBytes;
    }

    public void setDiskBytes(long diskBytes) {
        this.diskBytes = diskBytes;
    }

    @Override
    public String toString() {
        return "ContainerMetric{" +
                "applicationId='" + applicationId + '\'' +
                ", instanceIndex=" + instanceIndex +
                ", cpuPercentage=" + cpuPercentage +
                ", memoryBytes=" + memoryBytes +
                ", diskBytes=" + diskBytes +
                '}';
    }
}
