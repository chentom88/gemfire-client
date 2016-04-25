package io.pivotal.gemfire.continuousQuery;

import com.gemstone.gemfire.cache.CacheWriter;
import com.gemstone.gemfire.cache.CacheWriterException;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.RegionEvent;
import io.pivotal.gemfire.domain.Envelope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by pivotal on 4/21/16.
 */
public class ProductDBWriter implements CacheWriter<Long, Envelope> {

    private static Log log = LogFactory.getLog(ProductDBWriter.class);
    /* (non-Javadoc)
     * @see com.gemstone.gemfire.cache.CacheCallback#close()
     */
    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.gemstone.gemfire.cache.CacheWriter#beforeCreate(com.gemstone.gemfire.cache.EntryEvent)
     */
    @Override
    @Async
    public void beforeCreate(EntryEvent<Long, Envelope> entryEvent) throws CacheWriterException {
        log.info("Calling before create");
    }

    /* (non-Javadoc)
     * @see com.gemstone.gemfire.cache.CacheWriter#beforeDestroy(com.gemstone.gemfire.cache.EntryEvent)
     */
    @Override
    @Async
    public void beforeDestroy(EntryEvent<Long, Envelope> entryEvent) throws CacheWriterException {
       log.info("Calling before Destroy");
    }

    /* (non-Javadoc)
     * @see com.gemstone.gemfire.cache.CacheWriter#beforeRegionClear(com.gemstone.gemfire.cache.RegionEvent)
     */
    @Override
    public void beforeRegionClear(RegionEvent<Long, Envelope> entryEvent) throws CacheWriterException {
        log.info("Calling before region clear");
    }

    /* (non-Javadoc)
     * @see com.gemstone.gemfire.cache.CacheWriter#beforeRegionDestroy(com.gemstone.gemfire.cache.RegionEvent)
     */
    @Override
    public void beforeRegionDestroy(RegionEvent<Long, Envelope> regionEvent)
            throws CacheWriterException {
        log.info("Calling before region destroy");
    }

    /* (non-Javadoc)
     * @see com.gemstone.gemfire.cache.CacheWriter#beforeUpdate(com.gemstone.gemfire.cache.EntryEvent)
     */
    @Override
    @Async
    public void beforeUpdate(EntryEvent<Long, Envelope> entryEvent) throws CacheWriterException {
        log.info("Calling before update");
    }

}