package br.com.gocharge.repository;

import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.exceptions.NoContentException;
import br.com.gocharge.exceptions.NotFoundException;
import br.com.gocharge.mappers.BandeiraMapper;
import br.com.gocharge.model.BandeiraModel;
import br.com.gocharge.model.StatusCadastroModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class BandeiraRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Bandeira> getAll() {
    List<BandeiraModel> bandeiras =
        entityManager.createQuery("SELECT b FROM BandeiraModel b").getResultList();

    if (bandeiras.size() > 0) {
      return BandeiraMapper.INSTANCE.toDomain(bandeiras);
    } else {
      throw new NoContentException();
    }
  }

  public Bandeira getById(UUID id) {
    BandeiraModel bandeira = entityManager.find(BandeiraModel.class, id);

    if (Objects.nonNull(bandeira)) {
      return BandeiraMapper.INSTANCE.toDomain(bandeira);
    } else {
      throw new NotFoundException("Bandeira não encontrada");
    }
  }

  public Bandeira create(Bandeira bandeira) {
    BandeiraModel bandeiraModel = BandeiraMapper.INSTANCE.toModel(bandeira);

    entityManager.persist(bandeiraModel);

    return BandeiraMapper.INSTANCE.toDomain(bandeiraModel);
  }

  public Bandeira update(Bandeira bandeira) {
    BandeiraModel bandeiraModel = entityManager.find(BandeiraModel.class, bandeira.getId());

    if (Objects.nonNull(bandeiraModel)) {
      StatusCadastroModel statusCadastroModel = new StatusCadastroModel();
      statusCadastroModel.setId(bandeira.getStatus().getCodigo());

      bandeiraModel.setDescricao(bandeira.getDescricao());
      bandeiraModel.setStatus(statusCadastroModel);

      return BandeiraMapper.INSTANCE.toDomain(entityManager.merge(bandeiraModel));
    } else {
      throw new NotFoundException("Bandeira não encontrada");
    }
  }

  public void delete(UUID idBandeira) {
    BandeiraModel bandeira = entityManager.find(BandeiraModel.class, idBandeira);

    if (Objects.nonNull(bandeira)) {
      entityManager.remove(bandeira);
    } else {
      throw new NotFoundException("Bandeira não encontrada");
    }
  }
}
