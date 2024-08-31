package br.com.blackhunter.rest.api.v1.controller;

import br.com.blackhunter.rest.api.v1.dto.RestResponse;
import br.com.blackhunter.rest.api.v1.payload.LoginPayload;
import br.com.blackhunter.rest.api.v1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController extends RestControllerModel {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<RestResponse> doLogin(@RequestBody @Validated LoginPayload payload) {
        setOkResponse(() -> authService.doLogin(payload));
        return response();
    }
}
