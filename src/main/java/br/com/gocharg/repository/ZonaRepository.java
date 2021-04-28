package br.com.gocharg.repository;

import br.com.gocharg.domain.Zona;
import br.com.gocharg.exceptions.NoContentException;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.mappers.ZonaMapper;
import br.com.gocharg.model.ZonaModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class ZonaRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Zona> getAll() {
    List<ZonaModel> zonas = entityManager.createQuery("SELECT z FROM ZonaModel z").getResultList();

    if (zonas.size() > 0) {
      return ZonaMapper.INSTANCE.toDomain(zonas);
    } else {
      throw new NoContentException();
    }
  }

  public Zona getById(UUID id) {
    ZonaModel zona = entityManager.find(ZonaModel.class, id);

    if (Objects.nonNull(zona)) {
      return ZonaMapper.INSTANCE.toDomain(zona);
    } else {
      throw new NotFoundException("Zona não encontrada");
    }
  }

  public List<Zona> getByEstado(String idEstado) {
    List<ZonaModel> zonas =
            entityManager
                    .createQuery("SELECT z FROM ZonaModel z WHERE z.estado.id = :idEstado")
                    .setParameter("idEstado", idEstado)
                    .getResultList();

    if (zonas.size() > 0) {
      return ZonaMapper.INSTANCE.toDomain(zonas);
    } else {
      throw new NoContentException();
    }
  }

  public List<Zona> getByCidade(Integer idCidade) {
    List<ZonaModel> zonas =
            entityManager
                    .createQuery("SELECT z FROM ZonaModel z WHERE z.cidade.id = :idCidade")
                    .setParameter("idCidade", idCidade)
                    .getResultList();

    if (zonas.size() > 0) {
      return ZonaMapper.INSTANCE.toDomain(zonas);
    } else {
      throw new NoContentException();
    }
  }

  public Zona create(Zona zona) {
    ZonaModel zonaModel = ZonaMapper.INSTANCE.toModel(zona);

    entityManager.persist(zonaModel);

    return ZonaMapper.INSTANCE.toDomain(zonaModel);
  }

  public Zona update(Zona zona) {
    ZonaModel zonaModel = entityManager.find(ZonaModel.class, zona.getId());

    if (Objects.nonNull(zonaModel)) {
      ZonaMapper.INSTANCE.updateFrom(zona, zonaModel);

      entityManager.merge(zonaModel);

      return ZonaMapper.INSTANCE.toDomain(entityManager.merge(zonaModel));
    } else {
      throw new NotFoundException("Zona não encontrada");
    }
  }

  public void delete(UUID id) {
    ZonaModel zona = entityManager.find(ZonaModel.class, id);

    if (Objects.nonNull(zona)) {
      entityManager.remove(zona);
    } else {
      throw new NotFoundException("Zona não encontrada");
    }
  }
}
