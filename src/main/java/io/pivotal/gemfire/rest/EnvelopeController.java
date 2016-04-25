package io.pivotal.gemfire.rest;

import io.pivotal.gemfire.monitor.*;
import io.pivotal.gemfire.monitor.CQRepository;
import io.pivotal.gemfire.service.EnvelopeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.pivotal.gemfire.domain.Envelope;

@RestController
public class EnvelopeController {

    @Autowired
    EnvelopeProducer producer;

    @Autowired
    InsertRateMonitor gemfireInsertRate;

    @Autowired
    CQRepository CQRepository;

    @RequestMapping("/createEnvelopes")
    String create(@RequestParam(required = true) long count) {
        try {
            return "Your rates were: " + producer.createEnvelopes(count);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "An error occurred";
    }

    @RequestMapping("/createCQEnvelope")
    String createCQ() {
        try {
            Envelope envelope = producer.sendCQEnvelope();

            if(envelope != null) {
                return "Event delay was " + (envelope.getContinousQueryReceivedTimestamp().getTime() - envelope.getTimestamp().getTime()) + " ms";
            }else{
                return "Gave up waiting for the continuous query to pop.";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "An error occurred";
        }
    }

    @RequestMapping("/insertRate")
    String getInsertRate(){
        return gemfireInsertRate.monitor();
    }


}