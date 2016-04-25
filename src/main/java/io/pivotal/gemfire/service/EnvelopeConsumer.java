package io.pivotal.gemfire.service;

import io.pivotal.gemfire.template.EnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by pivotal on 4/20/16.
 */

@Component
public class EnvelopeConsumer implements Runnable{

    private final int MAX_SEARCHES = 1;
    private final int STATISTICS_CHUNK = 1;

    @Autowired
    EnvelopeService envelopeService;

    public void run(){
        System.out.println("Running consumer");
        int counter = 0;
        long startTime = System.nanoTime();


        while(true && counter < MAX_SEARCHES){
            envelopeService.query("select * from /envelope ");
            counter++;

            if(counter % STATISTICS_CHUNK == 0) {
                long estimatedTime = System.nanoTime() - startTime;
                if(TimeUnit.NANOSECONDS.toSeconds(estimatedTime) == 0){
                    System.out.println("Queried "+STATISTICS_CHUNK+" times at "+STATISTICS_CHUNK+" queries per second.");
                }else {
                    System.out.println("Queried "+STATISTICS_CHUNK+" times at " + (new Float(STATISTICS_CHUNK)/ TimeUnit.NANOSECONDS.toSeconds(estimatedTime)) + " queries per second.");
                }
                System.out.println("Queries duration: "+TimeUnit.NANOSECONDS.toSeconds(estimatedTime)+" seconds");
                startTime = System.nanoTime();
            }
        }
    }

}
