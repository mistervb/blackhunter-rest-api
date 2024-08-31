package br.com.blackhunter.rest.api.v1.controller;

import br.com.blackhunter.rest.api.v1.dto.RestResponse;
import br.com.blackhunter.rest.api.v1.payload.UserAccountPayload;
import br.com.blackhunter.rest.api.v1.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
public class UserAccountRestController extends RestControllerModel {
    @Autowired
    private UserAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<RestResponse> doCreate(@RequestBody @Validated UserAccountPayload payload) {
        setCreatedResponse(() -> accountService.doCreate(payload));
        return response();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<RestResponse> doUpdateById(@PathVariable UUID id,
                                                     @RequestBody @Validated UserAccountPayload payload) {
        setOkResponse(() -> accountService.doUpdateById(id,payload));
        return response();
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<RestResponse> detailsById(@PathVariable UUID id) {
        setOkResponse(() -> accountService.detailsById(id));
        return response();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> doDeleteById(@PathVariable UUID id) {
        accountService.detailsById(id);
        return responseNoContent();
    }
}
