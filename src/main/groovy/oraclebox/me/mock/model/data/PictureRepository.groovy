package oraclebox.me.mock.model.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.MapType
import com.fasterxml.jackson.databind.type.TypeFactory
import groovy.transform.Synchronized
import oraclebox.me.mock.model.facebook.Me
import org.apache.catalina.User
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PictureRepository {

    @Autowired
    ObjectMapper objectMapper;

    Map<String, Image> maleAsian = [:];
    Map<String, Image> femaleAsian = [:];
    Map<String, Image> maleWestern = [:];
    Map<String, Image> femaleWestern = [:];

    Map<String, List<Image>> userImagesMap = [:];

//    Map<String, Me> tokenMeMap = [:];
//
//    void save(String token, Me me) {
//        tokenMeMap.put(token, me);
//        // Write to file also
//        objectMapper.writeValue(new File("created_me.json"), tokenMeMap);
//    }
//
//    void load() {
//        TypeFactory typeFactory = objectMapper.getTypeFactory();
//        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Me.class);
//        tokenMeMap = objectMapper.readValue(new File("created_me.json"), mapType);
//        print(tokenMeMap);
//    }

    @Synchronized
    void save(){
        objectMapper.writeValue(new File("user_images.json"), userImagesMap);
    }

    List<Image> getImages(Me me, int maxNumberOfImages){
        if(!userImagesMap.containsKey(me.id)){
            // TODO random generate images 1..max
            // TODO Store to map
            save();
        }else{
            return userImagesMap.get(me.id);
        }
    }

    void loadSamples(){
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
