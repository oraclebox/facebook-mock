package oraclebox.me.mock.model.data

import oraclebox.me.mock.service.DataService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MockDataRepository {

    Map<String, MockData> mockDataLinkedHashMap = [:];

    @Autowired
    DataService dataService;

    void refresh(List<String> files) {
        Map<String, MockData> mockDataMap = [:];
        files.each {
            mockDataMap.put(StringUtils.substringBefore(it, "."), dataService.loadMockData(it));
        }
        mockDataLinkedHashMap = mockDataMap;
    }

    MockData getMockData(String name){
        return mockDataLinkedHashMap[name];
    }
}
