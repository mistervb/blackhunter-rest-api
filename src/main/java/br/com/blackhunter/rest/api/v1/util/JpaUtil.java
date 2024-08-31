package br.com.blackhunter.rest.api.v1.util;

import br.com.blackhunter.rest.api.v1.entity.EntityModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

// Generics para suportar qualquer entidade que implemente EntityModel e seu repositório correspondente
@Service
public class JpaUtil<E extends EntityModel<P>, P, R extends JpaRepository<E, UUID>> {

    @Autowired
    private R repository;

    // Método para buscar uma entidade pelo ID
    public E findById(UUID id) {
        Optional<E> op = this.repository.findById(id);
        if (op.isEmpty()) {
            throw new EntityNotFoundException("[404] Entidade com o id \"" + id + "\" não foi encontrada.");
        }
        return op.get();
    }

    // Método para buscar uma entidade pelo ID e aplicar uma atualização com payload específico
    public E findByIdAndUpdate(UUID id, P payload) {
        E entity = findById(id);
        entity.updateData(payload);
        return repository.saveAndFlush(entity);
    }

    // Método para buscar uma entidade pelo ID e aplicar uma atualização com payload específico
    public void findByIdAndDelete(UUID id) {
        E entity = findById(id);
        repository.delete(entity);
    }
}
