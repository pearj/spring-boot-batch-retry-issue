package sample.batch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyServiceNoRetry {
    
    public static final Logger LOG = LoggerFactory.getLogger(MyServiceNoRetry.class);
    
    public boolean process() {
        
        LOG.info("Running process that doesn't do retry");
        
        return true;
    }

}
