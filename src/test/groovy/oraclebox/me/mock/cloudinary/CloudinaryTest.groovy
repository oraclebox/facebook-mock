package oraclebox.me.mock.cloudinary
import com.cloudinary.*
import com.cloudinary.utils.ObjectUtils
import oraclebox.me.mock.model.data.Image
import org.apache.commons.io.FileUtils;
import org.junit.Test

class CloudinaryTest {

    @Test
    void cloudinary(){
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dguvbakpe",
                "api_key", "666265871522726",
                "api_secret", "krLdsD0bcG27hsLq6cBZmazpSQw"));

        listFileImages("images/female/western/").each {
            image->
                convertAndDownload(cloudinary, image.file, "sample/female/western/");
        }
        listFileImages("images/female/asian/").each {
            image->
                convertAndDownload(cloudinary, image.file, "sample/female/asian/");
        }
        listFileImages("images/male/western/").each {
            image->
                convertAndDownload(cloudinary, image.file, "sample/male/western/");
        }

        //File toUpload = new File("images/female/asian/0.jpeg");
        //convertAndDownload(cloudinary, toUpload, "download/female/asian/")

    }

    private void convertAndDownload(Cloudinary cloudinary, File toUpload, String folder) {
        Transformation transformation =
                new Transformation().width(1024).height(1024).crop("thumb").gravity("face").fetchFormat("png");
        Map options = ObjectUtils.asMap(
                "transformation", transformation
        );
        Map uploadResult = cloudinary.uploader().upload(toUpload, options);
        download(folder, uploadResult.get("secure_url"));
        FileUtils.deleteQuietly(toUpload);
    }

    void download(def folder, def address) {
        new File("${folder}/${address.tokenize('/')[-1]}").withOutputStream { out ->
            out << new URL(address).openStream()
        }
    }

    ArrayList<Image> listFileImages(String path) {
        List<File> files = FileUtils.listFiles(new File(path), ['jpg'] as String[], true);
        List<Image> list = [];
        files.each {
            Image image = new Image(file: it, id: UUID.randomUUID());
            list.add(image);
        }
        return list;
    }
}
