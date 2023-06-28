package co.com.telefonica.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ApplicationTest {

    @InjectMocks
    private Application application;

    @Test
    public void testMain() throws Exception {
        application.main(new String[]{"args"});
    }
}
