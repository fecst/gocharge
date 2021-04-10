package br.com.gocharge.repository;

import br.com.gocharge.domain.Valor;
import br.com.gocharge.exceptions.NoContentException;
import br.com.gocharge.exceptions.NotFoundException;
import br.com.gocharge.mappers.ValorMapper;
import br.com.gocharge.model.ValorModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class ValorRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Valor> getAll() {
    List<ValorModel> valores =
        entityManager.createQuery("SELECT b FROM ValorModel b").getResultList();

    if (valores.size() > 0) {
      return ValorMapper.INSTANCE.toDomain(valores);
    } else {
      throw new NoContentException();
    }
  }

  public List<Valor> getByBandeira(UUID idBandeira) {
    List<ValorModel> valores =
        entityManager
            .createQuery("SELECT b FROM ValorModel b WHERE b.bandeira.id = :idBandeira")
            .setParameter("idBandeira", idBandeira)
            .getResultList();

    if (valores.size() > 0) {
      return ValorMapper.INSTANCE.toDomain(valores);
    } else {
      throw new NoContentException();
    }
  }

  public Valor getById(UUID id) {
    ValorModel valor = entityManager.find(ValorModel.class, id);

    if (Objects.nonNull(valor)) {
      return ValorMapper.INSTANCE.toDomain(valor);
    } else {
      throw new NotFoundException("Valor não encontrado");
    }
  }

  public Valor create(Valor valor) {
    ValorModel valorModel = ValorMapper.INSTANCE.toModel(valor);

    entityManager.persist(valorModel);

    return ValorMapper.INSTANCE.toDomain(valorModel);
  }

  public Valor update(Valor valor) {
    ValorModel valorModel = entityManager.find(ValorModel.class, valor.getId());

    if (Objects.nonNull(valorModel)) {
      ValorMapper.INSTANCE.updateFrom(valor, valorModel);

      return ValorMapper.INSTANCE.toDomain(entityManager.merge(valorModel));
    } else {
      throw new NotFoundException("Valor não encontrado");
    }
  }

  public void delete(UUID idValor) {
    ValorModel valor = entityManager.find(ValorModel.class, idValor);

    if (Objects.nonNull(valor)) {
      entityManager.remove(valor);
    } else {
      throw new NotFoundException("Valor não encontrado");
    }
  }
}
