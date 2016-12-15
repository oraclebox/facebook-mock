package oraclebox.me.mock.controller

import oraclebox.me.mock.model.data.Image
import oraclebox.me.mock.model.facebook.Me
import oraclebox.me.mock.service.FacebookService
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = "/mock")
class MockController {

    @Autowired
    FacebookService facebookService;

    @RequestMapping(value = "/graph/me")
    Me me(@RequestParam(value = "access_token") String accessToken){
        return facebookService.loadOrCreateFacebookUser(accessToken);
    }


    @RequestMapping(value = "/graph/me/picture")
    ResponseEntity<byte[]> picture(@RequestParam(value = "access_token") String accessToken) {
        Me me = facebookService.loadOrCreateFacebookUser(accessToken);
        HttpHeaders headers = new HttpHeaders();
        Image image = facebookService.findUserImage(me.id);
        InputStream is = new FileInputStream(image.file);
        byte[] media = IOUtils.toByteArray(is);
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(media.size());
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }


}
