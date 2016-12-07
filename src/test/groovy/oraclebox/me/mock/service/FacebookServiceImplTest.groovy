package oraclebox.me.mock.service

import oraclebox.me.mock.model.data.MockData
import oraclebox.me.mock.model.facebook.Me
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacebookServiceImplTest {

    @Autowired
    FacebookService facebookService;

    @Autowired
    DataService dataService;

    @Test
    void createMockFacebookUserJapanese() {
        // Japanese
        MockData mockData = dataService.loadMockData("japanese.json");
        Me me = facebookService.createMockFacebookUser(mockData);
        print(me);
    }

    @Test
    void createMockFacebookUserChinese() {
        // Chinese
        for (int i = 0; i < 1; i++) {
            MockData mockData = dataService.loadMockData("chinese.json");
            Me me = facebookService.createMockFacebookUser(mockData);
        }
    }

    @Test
    void createMockFacebookUserWestern() {
        // Western
        for (int i = 0; i < 1; i++) {
            MockData mockData = dataService.loadMockData("western.json");
            Me me = facebookService.createMockFacebookUser(mockData);
        }
    }

}