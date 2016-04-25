package io.pivotal.gemfire.service;

import io.pivotal.gemfire.domain.ContainerMetric;
import io.pivotal.gemfire.domain.Envelope;
import io.pivotal.gemfire.monitor.CQRepository;
import io.pivotal.gemfire.template.EnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by pivotal on 4/20/16.
 */

@Component
public class EnvelopeProducer {
    private final int STATISTICS_CHUNK = 1000;


    private String randomIdentifier ="";

    @PostConstruct
    public void randomizeIdentifier(){
        char[] symbols;
        StringBuilder tmp = new StringBuilder();
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        symbols = tmp.toString().toCharArray();

        SecureRandom random = new SecureRandom();
        int randomNumber = new BigInteger(15, random).intValue();
        while (randomNumber > 1){
            randomIdentifier += symbols[randomNumber % 10];
            randomNumber = randomNumber / 10;
        }
    }

    @Autowired
    CQRepository CQRepository;

    @Autowired
    EnvelopeService envelopeService;

    public List<String> createEnvelopes(long count){
        long envelopeCounter = 0;
        List<String> rates = new ArrayList<String>();
        System.out.println("Running Producer with random Identifier: "+randomIdentifier);
        long startTime = System.nanoTime();

        while(true && envelopeCounter < count){
            envelopeCounter++;
            Envelope envelope = generateRandomEnvelope(envelopeCounter);
            envelopeService.insert(envelope);
            if(envelopeCounter % STATISTICS_CHUNK == 0) {
                long estimatedTime = System.nanoTime() - startTime;
                if(TimeUnit.NANOSECONDS.toMillis(estimatedTime) == 0){
                    rates.add("Inserted "+STATISTICS_CHUNK+" envelopes at "+STATISTICS_CHUNK+" envelopes per millisecond. <br/>");
                }else {
                    rates.add("Inserted "+STATISTICS_CHUNK+" envelopes at " + (new Float(STATISTICS_CHUNK)/ TimeUnit.NANOSECONDS.toMillis(estimatedTime)) + " envelopes per millisecond.  <br/>");
                }
                startTime = System.nanoTime();
            }
        }
        return rates;
    }

    public Envelope sendCQEnvelope() {
        long startTime = System.nanoTime();
        Envelope envelope = generateRandomEnvelope(0, "james");
        envelopeService.insert(envelope);

        boolean notFound = true;
        long elapsedTime = 0L;
        Envelope cqEnvelope = null;
        while(notFound && elapsedTime < 60L){
            cqEnvelope = CQRepository.getCQResult(envelope.getKey());
            if(cqEnvelope != null){
                notFound = false;
            }

            elapsedTime = TimeUnit.NANOSECONDS.toSeconds((System.nanoTime() - startTime));
        }

        return cqEnvelope;
    }

    private Envelope generateRandomEnvelope(long envelopeCounter) {
        return generateRandomEnvelope(envelopeCounter, "bob");
    }

    private Envelope generateRandomEnvelope(long envelopeCounter, String origin) {
        String key  = randomIdentifier + "-" + UUID.randomUUID().toString();

        String eventType = "myEventType";
        Date timestamp = Calendar.getInstance().getTime();
        String applicationId = "myAppID";
        int instanceIndex = 0;
        float cpuPercentage = 64.3f;
        long memoryBytes = 10000024L;
        long diskBytes = 100000000000024L;
        ContainerMetric containerMetric = new ContainerMetric( applicationId,  instanceIndex,  cpuPercentage,  memoryBytes,  diskBytes);
        return new Envelope(key,  origin,  eventType,  timestamp,  containerMetric);

    }
}
