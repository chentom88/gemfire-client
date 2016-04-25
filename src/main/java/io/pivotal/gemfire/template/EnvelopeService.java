package io.pivotal.gemfire.template;

import com.gemstone.gemfire.cache.query.SelectResults;
import io.pivotal.gemfire.domain.Envelope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.GemfireOperations;
import org.springframework.stereotype.Component;

/**
 * Created by pivotal on 4/20/16.
 */
@Component
public class EnvelopeService {
    @Autowired
    GemfireOperations envelopeTemplate;

    public void insert(Envelope envelope){
        envelopeTemplate.put(envelope.getKey(), envelope);

    }

    public SelectResults<Object> query(String query){
        return envelopeTemplate.find(query);
    }

    public int queryForInt(String query) {
        SelectResults<Object> count =  envelopeTemplate.query(query);
        return (Integer) count.asList().get(0);
    }
}