package br.com.gocharge.repository;

import br.com.gocharge.model.CidadeModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class CidadeRepository {

  @PersistenceContext EntityManager entityManager;

  public List<CidadeModel> getAll() {
    return entityManager.createQuery("SELECT CidadeModel FROM CidadeModel").getResultList();
  }

  public CidadeModel getById(UUID id) {
    return entityManager.find(CidadeModel.class, id);
  }

  public void create(CidadeModel cidade) {
    entityManager.persist(cidade);
  }

  public void update(CidadeModel cidade) {
    entityManager.merge(cidade);
  }

  public void delete(CidadeModel cidade) {
    entityManager.remove(cidade);
  }
}
