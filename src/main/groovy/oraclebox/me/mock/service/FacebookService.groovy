package oraclebox.me.mock.service

import oraclebox.me.mock.model.data.MeRepository
import oraclebox.me.mock.model.data.MockData
import oraclebox.me.mock.model.data.MockDataRepository
import oraclebox.me.mock.model.facebook.AgeRange
import oraclebox.me.mock.model.facebook.Employer
import oraclebox.me.mock.model.facebook.Language
import oraclebox.me.mock.model.facebook.Location
import oraclebox.me.mock.model.facebook.Me
import oraclebox.me.mock.model.facebook.Work
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

interface FacebookService {

    Me createMockFacebookUser(MockData mockData);

    String createBirthday(int age);

    long createId();

    Me loadOrCreateFacebookUser(String accessToken);
}

@Service
class FacebookServiceImpl implements FacebookService {

    @Autowired
    MockDataRepository mockDataRepository;

    @Autowired
    MeRepository meRepository;

    @Autowired
    DataService dataService;

    @Override
    Me createMockFacebookUser(MockData mockData) {
        Me me = new Me();
        me.id = createId();
        int sex = dataService.randomWithinRange(0, 2);
        String country = mockData.countries[dataService.randomWithinRange(0, mockData.countries.size())];


        if (sex == 0) { // male
            me.name = mockData.males[dataService.randomWithinRange(0, mockData.males.size() - 1)];
            me.gender = "male";
        } else {
            me.name = mockData.females[dataService.randomWithinRange(0, mockData.males.size() - 1)];
            me.gender = "female";
        }
        if (country == "japan") {
            me.lastName = StringUtils.substring(me.name, 0, 2);
            me.firstName = StringUtils.substring(me.name, 2);
        } else if (country == "china") {
            me.lastName = StringUtils.substring(me.name, 0, 1);
            me.firstName = StringUtils.substring(me.name, 1);
        } else {
            me.firstName = StringUtils.split(me.name, " ")?.first();
            me.lastName = StringUtils.split(me.name, " ")?.last();
        }
        me.ageRange = new AgeRange(min: dataService.randomWithinRange(12, 40));
        me.birthday = createBirthday(me.ageRange.min);
        me.email = UUID.randomUUID().toString() + "@" + mockData.domains[dataService.randomWithinRange(0, mockData.domains.size())];
        me.locale = mockData.locale;
        me.location = new Location(id: createId(), name: mockData.locations[dataService.randomWithinRange(0, mockData.locations.size())]);
        me.languages = [];
        mockData.languages.each {
            me.languages.add(new Language(id: createId(), name: it));
        }
        me.timezone = mockData.timezone;
        me.updatedTime = dataService.isoDate(new Date());
        // Job history
        int totalJobs = dataService.randomWithinRange(0, 4);
        if (totalJobs > 0) me.work = [];
        for (int i = 0; i < totalJobs; i++) {
            int year = dataService.randomWithinRange(1990 + i * 2, 2013);
            int month = dataService.randomWithinRange(0, 12);
            me.work.add(new Work(id: createId(),
                    startDate: year + "-" + StringUtils.leftPad(month + "", 2, "0"),
                    employer: new Employer(name: mockData.companies[dataService.randomWithinRange(0, mockData.companies.size())])
            ));
        }
        return me;
    }

    @Override
    String createBirthday(int age) {
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        int year = now.get(Calendar.YEAR) - age;
        String month = StringUtils.leftPad("" + dataService.randomWithinRange(1, 12), 2, "0");
        String day = StringUtils.leftPad("" + dataService.randomWithinRange(1, 30), 2, "0");
        return month + "/" + day + "/" + year;
    }

    @Override
    long createId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS")
        return Long.parseLong(sdf.format(new Date()));
    }

    @Override
    Me loadOrCreateFacebookUser(String accessToken) {
        Me me = meRepository.tokenMeMap.get(accessToken);
        if (me == null) {
            int randomData = dataService.randomWithinRange(0, mockDataRepository.getMockDataLinkedHashMap().size());
            me = createMockFacebookUser(mockDataRepository.getMockDataLinkedHashMap().values()[randomData]);
            // Save to me repository
            meRepository.save(accessToken, me);
        }
        return me;
    }
}

