package sample.batch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MyRetryService {
    
    public static final Logger LOG = LoggerFactory.getLogger(MyRetryService.class);
    
    @Retryable(maxAttempts = 5, include = RuntimeException.class, backoff = @Backoff(delay = 100, multiplier = 2))
    public boolean process() {
        
        double random = Math.random();
        
        LOG.info("Running process, random value {}", random);
        
        if (random > 0.2d) {
            throw new RuntimeException("Random fail time!");
        }
        
        return true;
    }

}
