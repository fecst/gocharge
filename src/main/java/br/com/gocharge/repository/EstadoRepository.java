package br.com.gocharge.repository;

import br.com.gocharge.exceptions.NoContentException;
import br.com.gocharge.exceptions.NotFoundException;
import br.com.gocharge.model.EstadoModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EstadoRepository {

  @PersistenceContext EntityManager entityManager;

  public List<EstadoModel> getAll() {
    return Optional.ofNullable(
            entityManager.createQuery("SELECT EstadoModel FROM EstadoModel").getResultList())
        .orElseThrow(NoContentException::new);
  }

  public EstadoModel getById(UUID id) {
    return Optional.ofNullable(entityManager.find(EstadoModel.class, id))
        .orElseThrow(NotFoundException::new);
  }

  public void create(EstadoModel estado) {
    entityManager.persist(estado);
  }

  public void update(EstadoModel estado) {
    entityManager.merge(estado);
  }

  public void delete(EstadoModel estado) {
    entityManager.remove(estado);
  }
}
