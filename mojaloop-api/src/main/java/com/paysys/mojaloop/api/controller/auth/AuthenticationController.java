package com.paysys.mojaloop.api.controller.auth;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paysys.mojaloop.api.controller.APIController;
import com.paysyslabs.mojaloop.commons.enums.Channel;
import com.paysyslabs.mojaloop.commons.enums.DeviceType;

import io.swagger.annotations.Api;

@Api(tags = "Authentication Controller")
@RestController
public class AuthenticationController extends APIController{

	@RequestMapping(value = AUTHENTICATE_ENDPOINT, method = RequestMethod.POST)
    public String authenticate(
                                    @RequestHeader("X-Auth-Username") String user,
                                    @RequestHeader("X-Auth-Password") String password,
                                    @RequestHeader("X-Device-Type") DeviceType deviceType,
                                    @RequestHeader("X-Device-ID") String deviceID,
                                    @RequestHeader("X-App-Version") String appVersion,
                                    @RequestHeader("X-Channel") Channel channel,
                                    @RequestHeader(value="X-Latitude", required = false) String latitude,
                                    @RequestHeader(value="X-Longitude", required = false) String longitude) {
        return "This is just for in-code-documentation purposes and Rest API reference documentation."
                + "Servlet will never get to this point as Http requests are processed by AuthenticationFilter."
                + "Nonetheless to authenticate Domain User POST request with X-Auth-Username and X-Auth-Password headers "
                + "is mandatory to this URL. If username and password are correct valid token will be returned (just json string in response) "
                + "This token must be present in X-Auth-Token header in all requests for all other URLs, including logout."
                + "Authentication can be issued multiple times and each call results in new ticket.";
    }
}
