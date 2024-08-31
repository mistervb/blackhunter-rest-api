package br.com.blackhunter.rest.api.v1.handler;

import br.com.blackhunter.rest.api.v1.dto.FieldError;
import br.com.blackhunter.rest.api.v1.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fields = new ArrayList<>();
        e.getFieldErrors().forEach(er -> {
            fields.add(new FieldError(er.getField(), er.getDefaultMessage()));
        });
        return responseError(fields);
    }

    private ResponseEntity<RestResponse> responseError(Object value) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestResponse(
                HttpStatus.BAD_REQUEST.value(),
                true,
                value
        ));
    }
}
