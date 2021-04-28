package br.com.gocharg.repository;

import br.com.gocharg.domain.Usuario;
import br.com.gocharg.exceptions.NoContentException;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.mappers.UsuarioMapper;
import br.com.gocharg.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class UsuarioRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Usuario> getAll() {
    List<UsuarioModel> usuarios =
        entityManager.createQuery("SELECT u FROM UsuarioModel u").getResultList();

    if (usuarios.size() > 0) {
      return UsuarioMapper.INSTANCE.toDomain(usuarios);
    } else {
      throw new NoContentException();
    }
  }

  public Usuario getById(UUID id) {
    UsuarioModel usuario = entityManager.find(UsuarioModel.class, id);

    if (Objects.nonNull(usuario)) {
      return UsuarioMapper.INSTANCE.toDomain(usuario);
    } else {
      throw new NotFoundException("Usuário não encontrado");
    }
  }

  public Usuario create(Usuario usuario) {
    UsuarioModel usuarioModel = UsuarioMapper.INSTANCE.toModel(usuario);

    entityManager.persist(usuarioModel);

    return UsuarioMapper.INSTANCE.toDomain(usuarioModel);
  }

  public Usuario update(Usuario usuario) {
    UsuarioModel usuarioModel = entityManager.find(UsuarioModel.class, usuario.getId());

    if (Objects.nonNull(usuarioModel)) {
      UsuarioMapper.INSTANCE.updateFrom(usuario, usuarioModel);

      entityManager.merge(usuarioModel);

      return UsuarioMapper.INSTANCE.toDomain(entityManager.merge(usuarioModel));
    } else {
      throw new NotFoundException("Usuário não encontrado");
    }
  }

  public void delete(UUID idUsuario) {
    UsuarioModel usuario = entityManager.find(UsuarioModel.class, idUsuario);

    if (Objects.nonNull(usuario)) {
      entityManager.remove(usuario);
    } else {
      throw new NotFoundException("Usuário não encontrado");
    }
  }
}
