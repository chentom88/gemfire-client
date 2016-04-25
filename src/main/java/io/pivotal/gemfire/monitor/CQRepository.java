package io.pivotal.gemfire.monitor;


import org.springframework.stereotype.Component;
import io.pivotal.gemfire.domain.Envelope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pivotal on 4/22/16.
 */

@Component
public class CQRepository {

private Map<String, Envelope> continuousQueryResults = new ConcurrentHashMap<String, Envelope>();

    public void addCQResult(Envelope envelope){
        continuousQueryResults.put(envelope.getKey(), envelope);
    }

    public Envelope getCQResult(String key){
        return continuousQueryResults.get(key);
    }

}
