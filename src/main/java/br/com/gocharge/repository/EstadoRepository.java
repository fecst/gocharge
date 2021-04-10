package br.com.gocharge.repository;

import br.com.gocharge.domain.Estado;
import br.com.gocharge.exceptions.NoContentException;
import br.com.gocharge.exceptions.NotFoundException;
import br.com.gocharge.mappers.EstadoMapper;
import br.com.gocharge.model.EstadoModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class EstadoRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Estado> getAll() {
    List<EstadoModel> estados =
        entityManager.createQuery("SELECT e FROM EstadoModel e").getResultList();

    if (estados.size() > 0) {
      return EstadoMapper.INSTANCE.toDomain(estados);
    } else {
      throw new NoContentException();
    }
  }

  public Estado getById(String id) {
    EstadoModel estado = entityManager.find(EstadoModel.class, id);

    if (Objects.nonNull(estado)) {
      return EstadoMapper.INSTANCE.toDomain(estado);
    } else {
      throw new NotFoundException("Estado não encontrado");
    }
  }

  public Estado create(Estado estado) {
    EstadoModel estadoModel = EstadoMapper.INSTANCE.toModel(estado);

    entityManager.persist(estadoModel);

    return EstadoMapper.INSTANCE.toDomain(estadoModel);
  }

  public Estado update(Estado estado) {
    EstadoModel estadoModel = entityManager.find(EstadoModel.class, estado.getId());

    if (Objects.nonNull(estado)) {
      estadoModel.setDescricao(estado.getDescricao());

      return EstadoMapper.INSTANCE.toDomain(
          entityManager.merge(estadoModel));
    } else {
      throw new NotFoundException("Estado não encontrado");
    }
  }

  public void delete(UUID idEstado) {
    EstadoModel estado = entityManager.find(EstadoModel.class, idEstado);

    if (Objects.nonNull(estado)) {
      entityManager.remove(estado);
    } else {
      throw new NotFoundException("Estado não encontrado");
    }
  }
}
