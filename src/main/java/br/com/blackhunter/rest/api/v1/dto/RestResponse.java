package br.com.blackhunter.rest.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {
    private int status;
    private boolean hasErrors;
    private Object data;
}
