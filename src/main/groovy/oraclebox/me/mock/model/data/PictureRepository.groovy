package oraclebox.me.mock.model.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.MapType
import com.fasterxml.jackson.databind.type.TypeFactory
import groovy.transform.Synchronized
import oraclebox.me.mock.model.facebook.Me
import oraclebox.me.mock.service.DataService
import org.apache.catalina.User
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PictureRepository {

    @Autowired
    DataService dataService;

    @Autowired
    ObjectMapper objectMapper;

    Map<String, Image> maleAsian = [:];
    Map<String, Image> femaleAsian = [:];
    Map<String, Image> maleWestern = [:];
    Map<String, Image> femaleWestern = [:];

    Map<String, Image> userImagesMap = [:];

    void load() {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Image.class);
        userImagesMap = objectMapper.readValue(new File("user_images.json"), mapType);
    }

    @Synchronized
    void save() {
        objectMapper.writeValue(new File("user_images.json"), userImagesMap);
    }

    List<Image> getImages(Me me, int maxNumberOfImages) {
        if (!userImagesMap.containsKey(me.id)) {
            Image image = null;
            if (me.region == 'asian') {
                if (me.gender == 'male')
                    image = maleAsian.values()[dataService.randomWithinRange(1, maleAsian.size())];
                else
                    image = femaleAsian.values()[dataService.randomWithinRange(1, femaleAsian.size())];
            } else {
                if (me.gender == 'male')
                    image = maleWestern.values()[dataService.randomWithinRange(1, maleWestern.size())];
                else
                    image = femaleWestern.values()[dataService.randomWithinRange(1, femaleWestern.size())];
            }
            userImagesMap.put(me.id, image);
            save();
        } else {
            return userImagesMap.get(me.id);
        }
    }

    void loadSamples() {
        maleAsian = listFileImages("sample/male/asian");
        femaleAsian = listFileImages("sample/female/asian");
        maleWestern = listFileImages("sample/male/western");
        femaleWestern = listFileImages("sample/female/western");

    }


    static Map<String, Image> listFileImages(String path) {
        List<File> files = FileUtils.listFiles(new File(path), ['png'] as String[], true);
        Map<String, Image> list = [:];
        files.each {
            Image image = new Image(file: it, id: StringUtils.substringBefore(it.getName(), ".png"));
            list.put(image.id, image);
        }
        return list;
    }
}
