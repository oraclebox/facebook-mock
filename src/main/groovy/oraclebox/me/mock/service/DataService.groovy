package oraclebox.me.mock.service

import com.fasterxml.jackson.databind.ObjectMapper
import oraclebox.me.mock.model.data.MockData
import org.springframework.stereotype.Service

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created by oraclebox on 4/12/2016.
 */
interface DataService {
    MockData loadMockData(String filename);

    int randomWithinRange(int min, int max)

    String isoDate(Date date);

}

@Service
class DataServiceImpl implements DataService {

    @Override
    MockData loadMockData(String filename) {
        MockData mockData = new ObjectMapper().readValue(new File(filename), MockData.class);
        return mockData;
    }

    @Override
    int randomWithinRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    @Override
    String isoDate(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ddZ"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}