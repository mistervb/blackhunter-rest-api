package br.com.blackhunter.rest.api.v1.controller;

import br.com.blackhunter.rest.api.v1.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public abstract class RestControllerModel {
    protected RestResponse restResponse;

    protected static final int CREATED     = HttpStatus.CREATED.value(),
                               BAD_REQUEST = HttpStatus.BAD_REQUEST.value(),
                               OK          = HttpStatus.OK.value(),
                               NO_CONTENT  = HttpStatus.NO_CONTENT.value();

    protected ResponseEntity<RestResponse> response() {
        return ResponseEntity.status(restResponse.getStatus()).body(restResponse);
    }

    protected ResponseEntity<Void> responseNoContent() {
        return ResponseEntity.status(NO_CONTENT).build();
    }

    protected void setResponse(Callable<Object> valueCallable, int status) {
        try {
            Object value = valueCallable.call();
            this.restResponse = new RestResponse(status, false, value);
        } catch (SQLException e) {
            System.out.println("Teste de erro de SQL ai ó");
        } catch (Exception e) {
            this.restResponse = new RestResponse(BAD_REQUEST, true, e.getMessage());
        }
    }

    protected void setResponse(Callable<Object> valueCallable, int status, int errorStatus) {
        try {
            Object value = valueCallable.call();
            this.restResponse = new RestResponse(status, false, value);
        } catch(Exception e) {
            this.restResponse = new RestResponse(errorStatus, true, e.getMessage());
        }
    }

    protected void setCreatedResponse(Callable<Object> value) {
        setResponse(value, CREATED);
    }

    protected void setOkResponse(Callable<Object> value) {
        setResponse(value, OK);
    }
}
