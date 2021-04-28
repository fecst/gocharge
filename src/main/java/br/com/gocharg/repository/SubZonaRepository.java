package br.com.gocharg.repository;

import br.com.gocharg.domain.SubZona;
import br.com.gocharg.exceptions.NoContentException;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.mappers.SubZonaMapper;
import br.com.gocharg.model.SubZonaModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class SubZonaRepository {

  @PersistenceContext EntityManager entityManager;

  public List<SubZona> getAll() {
    List<SubZonaModel> zonas =
        entityManager.createQuery("SELECT z FROM SubZonaModel z").getResultList();

    if (zonas.size() > 0) {
      return SubZonaMapper.INSTANCE.toDomain(zonas);
    } else {
      throw new NoContentException();
    }
  }

  public SubZona getById(UUID id) {
    SubZonaModel zona = entityManager.find(SubZonaModel.class, id);

    if (Objects.nonNull(zona)) {
      return SubZonaMapper.INSTANCE.toDomain(zona);
    } else {
      throw new NotFoundException("SubZona não encontrada");
    }
  }

  public List<SubZona> getByEstado(String idEstado) {
    List<SubZonaModel> zonas =
        entityManager
            .createQuery("SELECT z FROM SubZonaModel z WHERE z.estado.id = :idEstado")
            .setParameter("idEstado", idEstado)
            .getResultList();

    if (zonas.size() > 0) {
      return SubZonaMapper.INSTANCE.toDomain(zonas);
    } else {
      throw new NoContentException();
    }
  }

  public List<SubZona> getByCidade(Integer idCidade) {
    List<SubZonaModel> zonas =
        entityManager
            .createQuery("SELECT z FROM SubZonaModel z WHERE z.cidade.id = :idCidade")
            .setParameter("idCidade", idCidade)
            .getResultList();

    if (zonas.size() > 0) {
      return SubZonaMapper.INSTANCE.toDomain(zonas);
    } else {
      throw new NoContentException();
    }
  }

  public List<SubZona> getByZona(UUID idZona) {
    List<SubZonaModel> zonas =
        entityManager
            .createQuery("SELECT s FROM SubZonaModel s WHERE s.zona.id = :idZona")
            .setParameter("idZona", idZona)
            .getResultList();

    if (zonas.size() > 0) {
      return SubZonaMapper.INSTANCE.toDomain(zonas);
    } else {
      throw new NoContentException();
    }
  }

  public SubZona create(SubZona zona) {
    SubZonaModel SubZonaModel = SubZonaMapper.INSTANCE.toModel(zona);

    entityManager.persist(SubZonaModel);

    return SubZonaMapper.INSTANCE.toDomain(SubZonaModel);
  }

  public SubZona update(SubZona subZona) {
    SubZonaModel SubZonaModel = entityManager.find(SubZonaModel.class, subZona.getId());

    if (Objects.nonNull(SubZonaModel)) {
      SubZonaMapper.INSTANCE.updateFrom(subZona, SubZonaModel);

      entityManager.merge(SubZonaModel);

      return SubZonaMapper.INSTANCE.toDomain(entityManager.merge(SubZonaModel));
    } else {
      throw new NotFoundException("SubZona não encontrada");
    }
  }

  public void delete(UUID id) {
    SubZonaModel zona = entityManager.find(SubZonaModel.class, id);

    if (Objects.nonNull(zona)) {
      entityManager.remove(zona);
    } else {
      throw new NotFoundException("SubZona não encontrada");
    }
  }
}
