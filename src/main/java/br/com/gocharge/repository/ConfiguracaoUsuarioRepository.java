package br.com.gocharge.repository;

import br.com.gocharge.model.ConfiguracaoUsuarioModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class ConfiguracaoUsuarioRepository {

  @PersistenceContext EntityManager entityManager;

  public List<ConfiguracaoUsuarioModel> getByUsuario(UUID idUsuario) {
    return entityManager
        .createQuery(
            "SELECT ConfiguracaoUsuarioModel "
                + "FROM ConfiguracaoUsuarioModel "
                + "WHERE CidadeModel.usuarioModel.id = :idUsuario")
        .setParameter("idUsuario", idUsuario)
        .getResultList();
  }

  public ConfiguracaoUsuarioModel getById(UUID id) {
    return entityManager.find(ConfiguracaoUsuarioModel.class, id);
  }

  public void create(ConfiguracaoUsuarioModel configuracaoUsuario) {
    entityManager.persist(configuracaoUsuario);
  }

  public void update(ConfiguracaoUsuarioModel configuracaoUsuario) {
    entityManager.merge(configuracaoUsuario);
  }

  public void delete(ConfiguracaoUsuarioModel configuracaoUsuario) {
    entityManager.remove(configuracaoUsuario);
  }
}
