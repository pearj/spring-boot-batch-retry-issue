package sample.batch;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import sample.batch.service.MyServiceNoRetry;

@ActiveProfiles("Test")
@ContextConfiguration(classes = {SampleBatchApplication.class, MockBatchTestWithNoRetryWorking.MockedRetryService.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
public class MockBatchTestWithNoRetryWorking {
    
    @Autowired
    MyServiceNoRetry service;
    
    @Test
    public void batchTest() {
        service.process();
        
        verify(service).process();
        validateMockitoUsage();
    }
    
    public static class MockedRetryService {
        @Bean
        @Primary
        public MyServiceNoRetry myService() {
            return mock(MyServiceNoRetry.class);
        }
    }
}
