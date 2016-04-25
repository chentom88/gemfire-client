package io.pivotal.gemfire.monitor;

import io.pivotal.gemfire.template.EnvelopeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by pivotal on 4/22/16.
 */
@Component
public class GemfireInsertRate {
    private static Log log = LogFactory.getLog(GemfireInsertRate.class);

    @Autowired
    EnvelopeService envelopeService;

    public String monitor(){
        try {
            int startCount = envelopeService.queryForInt("select count(*) from /envelope");
            TimeUnit.SECONDS.sleep(5);
            int endCount = envelopeService.queryForInt("select count(*) from /envelope");

            return "Gemfire throughput: " + ((endCount - startCount) / 5) + " records per second.";
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
