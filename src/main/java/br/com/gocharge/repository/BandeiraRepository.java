package br.com.gocharge.repository;

import br.com.gocharge.model.BandeiraModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class BandeiraRepository {

  @PersistenceContext EntityManager entityManager;

  public List<BandeiraModel> getAll() {
    return entityManager.createQuery("SELECT BandeiraModel FROM BandeiraModel").getResultList();
  }

  public BandeiraModel getById(UUID id) {
    return entityManager.find(BandeiraModel.class, id);
  }

  public void create(BandeiraModel bandeira) {
    entityManager.persist(bandeira);
  }

  public void update(BandeiraModel bandeira) {
    entityManager.merge(bandeira);
  }

  public void delete(BandeiraModel bandeira) {
    entityManager.remove(bandeira);
  }
}
