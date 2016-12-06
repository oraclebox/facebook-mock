package oraclebox.me.mock.service;

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MockImageServiceTest {

    @Autowired
    MockImageService mockImageService;

    @Test
    void loadImages() throws Exception {
        mockImageService.loadImages();
    }

}