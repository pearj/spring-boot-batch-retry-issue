package sample.batch;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import sample.batch.service.MyRetryService;

@ActiveProfiles("Test")
@ContextConfiguration(classes = { SampleBatchApplication.class,
        MockBatchTestWithRetryVerificationFailuresWorkaround.MockedRetryService.class },
                      loader = AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
public class MockBatchTestWithRetryVerificationFailuresWorkaround {

    @Autowired
    MyRetryService service;

    @Test
    public void batchTest() throws Exception {
        service.process();
        
        if (service instanceof Advised) {
            service = (MyRetryService) ((Advised) service).getTargetSource().getTarget();
        }

        verify(service).process();
        validateMockitoUsage();
    }

    public static class MockedRetryService {
        @Bean
        @Primary
        public MyRetryService myRetryService() {
            return mock(MyRetryService.class);
        }
    }
}
