package br.com.gocharg.repository;

import br.com.gocharg.domain.Totem;
import br.com.gocharg.exceptions.NoContentException;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.mappers.TotemMapper;
import br.com.gocharg.model.TotemModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class TotemRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Totem> getAll() {
    List<TotemModel> totens =
        entityManager.createQuery("SELECT t FROM TotemModel t").getResultList();

    if (totens.size() > 0) {
      return TotemMapper.INSTANCE.toDomain(totens);
    } else {
      throw new NoContentException();
    }
  }

  public List<Totem> getByStatus(String status) {
    List<TotemModel> totens =
        entityManager
            .createQuery("SELECT t FROM TotemModel t WHERE status.id = :status")
            .setParameter("status", status)
            .getResultList();

    if (totens.size() > 0) {
      return TotemMapper.INSTANCE.toDomain(totens);
    } else {
      throw new NoContentException();
    }
  }

  public List<Totem> getByEstado(String idEstado) {
    List<TotemModel> totens =
        entityManager
            .createQuery("SELECT t FROM TotemModel t WHERE estado.id = :idEstado")
            .setParameter("idEstado", idEstado)
            .getResultList();

    if (totens.size() > 0) {
      return TotemMapper.INSTANCE.toDomain(totens);
    } else {
      throw new NoContentException();
    }
  }

  public List<Totem> getByCidade(Integer idCidade) {
    List<TotemModel> totens =
        entityManager
            .createQuery("SELECT t FROM TotemModel t WHERE cidade.id = :idCidade")
            .setParameter("idCidade", idCidade)
            .getResultList();

    if (totens.size() > 0) {
      return TotemMapper.INSTANCE.toDomain(totens);
    } else {
      throw new NoContentException();
    }
  }

  public List<Totem> getByZona(UUID idZona) {
    List<TotemModel> totens =
        entityManager
            .createQuery("SELECT t FROM TotemModel t WHERE zona.id = :idZona")
            .setParameter("idZona", idZona)
            .getResultList();

    if (totens.size() > 0) {
      return TotemMapper.INSTANCE.toDomain(totens);
    } else {
      throw new NoContentException();
    }
  }

  public List<Totem> getBySubZona(UUID idSubZona) {
    List<TotemModel> totens =
        entityManager
            .createQuery("SELECT t FROM TotemModel t WHERE subZona.id = :idSubZona")
            .setParameter("idSubZona", idSubZona)
            .getResultList();

    if (totens.size() > 0) {
      return TotemMapper.INSTANCE.toDomain(totens);
    } else {
      throw new NoContentException();
    }
  }

  public List<Totem> getByValor(UUID idValor) {
    List<TotemModel> totens =
        entityManager
            .createQuery("SELECT t FROM TotemModel t WHERE valor.id = :idValor")
            .setParameter("idValor", idValor)
            .getResultList();

    if (totens.size() > 0) {
      return TotemMapper.INSTANCE.toDomain(totens);
    } else {
      throw new NoContentException();
    }
  }

  public Totem getById(UUID id) {
    TotemModel totem = entityManager.find(TotemModel.class, id);

    if (Objects.nonNull(totem)) {
      return TotemMapper.INSTANCE.toDomain(totem);
    } else {
      throw new NotFoundException("Totem não encontrado");
    }
  }

  public Totem create(Totem totem) {
    TotemModel totemModel = TotemMapper.INSTANCE.toModel(totem);

    entityManager.persist(totemModel);

    return TotemMapper.INSTANCE.toDomain(totemModel);
  }

  public void update(Totem totem) {
    TotemModel totemModel = entityManager.find(TotemModel.class, totem.getId());

    if (Objects.nonNull(totemModel)) {
      TotemMapper.INSTANCE.updateFrom(totem, totemModel);

      entityManager.merge(totemModel);
    } else {
      throw new NotFoundException("Totem não encontrado");
    }
  }

  public void delete(UUID id) {
    TotemModel totemModel = entityManager.find(TotemModel.class, id);

    if (Objects.nonNull(totemModel)) {
      entityManager.remove(totemModel);
    } else {
      throw new NotFoundException("Totem não encontrado");
    }
  }
}
