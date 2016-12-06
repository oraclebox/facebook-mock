package oraclebox.me.mock.model.data

import oraclebox.me.mock.model.facebook.Me
import oraclebox.me.mock.service.DataService
import oraclebox.me.mock.service.FacebookService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeRepositoryTest {

    @Autowired
    FacebookService facebookService;

    @Autowired
    DataService dataService;

    @Autowired
    MeRepository meRepository;
    @Autowired
    MockDataRepository mockDataRepository;

    @Test
    void save() throws Exception {
        mockDataRepository.refresh(["japanese.json", "chinese.json", "western.json"]);
        for (int i = 0; i < 500; i++) {
            Me me = facebookService.createMockFacebookUser(mockDataRepository.mockDataLinkedHashMap.values()[dataService.randomWithinRange(0, 2)]);
            meRepository.save(me.id, me);
        }

        meRepository.load();
    }

}