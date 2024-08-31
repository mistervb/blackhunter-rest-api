package br.com.blackhunter.rest.api.v1.service;

import java.util.List;
import java.util.UUID;

public interface CrudService<Return, Payload> {
    Return doCreate(Payload payload) throws Exception;
    Return doUpdateById(UUID ID, Payload payload) throws Exception;
    List<Return> getAll();
    Return detailsById(UUID id);
    void doDeleteById(UUID id);

    default List<Return> getAllByValue(Object value){ return null; };
}
