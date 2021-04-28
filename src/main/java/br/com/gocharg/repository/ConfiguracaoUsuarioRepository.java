package br.com.gocharg.repository;

import br.com.gocharg.domain.ConfiguracaoUsuario;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.mappers.ConfiguracaoUsuarioMapper;
import br.com.gocharg.model.ConfiguracaoUsuarioModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.UUID;

@Repository
public class ConfiguracaoUsuarioRepository {

  @PersistenceContext EntityManager entityManager;

  public ConfiguracaoUsuario getByUsuario(UUID idUsuario) {
    ConfiguracaoUsuarioModel configuracao =
        (ConfiguracaoUsuarioModel)
            entityManager
                .createQuery(
                    "SELECT ConfiguracaoUsuarioModel c"
                        + "FROM ConfiguracaoUsuarioModel c"
                        + "WHERE CidadeModel.usuarioModel.id = :idUsuario")
                .setParameter("idUsuario", idUsuario)
                .getSingleResult();

    if (Objects.nonNull(configuracao)) {
      return ConfiguracaoUsuarioMapper.INSTANCE.toDomain(configuracao);
    } else {
      throw new NotFoundException("Configuração não encontrada");
    }
  }

  public ConfiguracaoUsuario getById(UUID id) {
    ConfiguracaoUsuarioModel configuracao = entityManager.find(ConfiguracaoUsuarioModel.class, id);

    if (Objects.nonNull(configuracao)) {
      return ConfiguracaoUsuarioMapper.INSTANCE.toDomain(configuracao);
    } else {
      throw new NotFoundException("Configuração não encontrada");
    }
  }

  public ConfiguracaoUsuario create(ConfiguracaoUsuario configuracaoUsuario) {
    ConfiguracaoUsuarioModel configuracao =
        ConfiguracaoUsuarioMapper.INSTANCE.toModel(configuracaoUsuario);

    entityManager.persist(configuracaoUsuario);

    return ConfiguracaoUsuarioMapper.INSTANCE.toDomain(configuracao);
  }

  public ConfiguracaoUsuario update(ConfiguracaoUsuario configuracaoUsuario) {
    ConfiguracaoUsuarioModel configuracao =
        entityManager.find(ConfiguracaoUsuarioModel.class, configuracaoUsuario.getId());

    if (Objects.nonNull(configuracao)) {
      configuracao.setDuplaValidacao(configuracaoUsuario.getDuplaValidacao());

      return entityManager.merge(configuracaoUsuario);
    } else {
      throw new NotFoundException("Configuração não encontrada");
    }
  }

  public void delete(ConfiguracaoUsuario configuracaoUsuario) {
    ConfiguracaoUsuarioModel configuracao =
        entityManager.find(ConfiguracaoUsuarioModel.class, configuracaoUsuario.getId());

    if (Objects.nonNull(configuracao)) {
      entityManager.remove(configuracao);
    } else {
      throw new NotFoundException("Configuração não encontrada");
    }
  }
}
