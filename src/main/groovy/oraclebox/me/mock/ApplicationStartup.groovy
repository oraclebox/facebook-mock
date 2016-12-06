package oraclebox.me.mock

import oraclebox.me.mock.model.data.MeRepository
import oraclebox.me.mock.model.data.MockData
import oraclebox.me.mock.model.data.MockDataRepository
import oraclebox.me.mock.model.data.PictureRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    final Logger L = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    MeRepository meRepository;

    @Autowired
    MockDataRepository mockDataRepository;

    @Autowired
    PictureRepository pictureRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    void onApplicationEvent(final ApplicationReadyEvent event) {
        mockDataRepository.refresh(["japanese.json", "chinese.json", "western.json"])
        L.info("Loaded mock data to repository.")

        meRepository.load();
        L.info("Loaded " + meRepository.tokenMeMap.size() + " records to me repository. ");

        pictureRepository.loadSamples();
        L.info("Loaded samples");

    }

}