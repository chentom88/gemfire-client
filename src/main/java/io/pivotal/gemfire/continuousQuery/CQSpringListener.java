package io.pivotal.gemfire.continuousQuery;

import com.gemstone.gemfire.cache.query.CqEvent;

import com.gemstone.gemfire.pdx.internal.PdxInstanceImpl;
import io.pivotal.gemfire.monitor.CQRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.pivotal.gemfire.domain.Envelope;

import java.util.Calendar;

/**
 * Created by pivotal on 4/20/16.
 */
public class CQSpringListener {
    private static Log log = LogFactory.getLog(CQSpringListener.class);

    @Autowired
    CQRepository CQRepository;

    public void handleEvent(CqEvent event) {
        PdxInstanceImpl pdxInstance = (PdxInstanceImpl) event.getNewValue();
        Envelope envelope = (io.pivotal.gemfire.domain.Envelope)pdxInstance.getObject();
        envelope.setContinousQueryReceivedTimestamp(Calendar.getInstance().getTime());
        log.info("Received a CQ event " + envelope.getKey()+ " "+ envelope.getOrigin());
        CQRepository.addCQResult(envelope);
    }

}
