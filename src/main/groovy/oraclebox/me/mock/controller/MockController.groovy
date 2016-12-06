package oraclebox.me.mock.controller

import oraclebox.me.mock.model.facebook.Me
import oraclebox.me.mock.service.FacebookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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
}
