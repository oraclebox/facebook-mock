package oraclebox.me.mock.model.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.MapType
import com.fasterxml.jackson.databind.type.TypeFactory
import oraclebox.me.mock.model.facebook.Me
import oraclebox.me.mock.service.DataService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MeRepository {

    @Autowired
    ObjectMapper objectMapper;

    Map<String, Me> tokenMeMap = [:];

    void save(String token, Me me) {
        tokenMeMap.put(token, me);
        // Write to file also
        objectMapper.writeValue(new File("created_me.json"), tokenMeMap);
    }

    void load() {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Me.class);
        tokenMeMap = objectMapper.readValue(new File("created_me.json"), mapType);
        print(tokenMeMap);
    }

}
