package br.com.gocharg.repository;

import br.com.gocharg.domain.Fabricante;
import br.com.gocharg.exceptions.NoContentException;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.mappers.FabricanteMapper;
import br.com.gocharg.model.FabricanteModel;
import br.com.gocharg.model.StatusCadastroModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class FabricanteRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Fabricante> getAll() {
    List<FabricanteModel> fabrincates =
        entityManager.createQuery("SELECT b FROM FabricanteModel b").getResultList();

    if (fabrincates.size() > 0) {
      return FabricanteMapper.INSTANCE.toDomain(fabrincates);
    } else {
      throw new NoContentException();
    }
  }

  public Fabricante getById(UUID id) {
    FabricanteModel fabrincate = entityManager.find(FabricanteModel.class, id);

    if (Objects.nonNull(fabrincate)) {
      return FabricanteMapper.INSTANCE.toDomain(fabrincate);
    } else {
      throw new NotFoundException("Fabricante não encontrada");
    }
  }

  public Fabricante create(Fabricante Fabricante) {
    FabricanteModel fabricanteModel = FabricanteMapper.INSTANCE.toModel(Fabricante);

    entityManager.persist(fabricanteModel);

    return FabricanteMapper.INSTANCE.toDomain(fabricanteModel);
  }

  public Fabricante update(Fabricante Fabricante) {
    FabricanteModel FabricanteModel = entityManager.find(FabricanteModel.class, Fabricante.getId());

    if (Objects.nonNull(FabricanteModel)) {
      StatusCadastroModel statusCadastroModel = new StatusCadastroModel();
      statusCadastroModel.setId(Fabricante.getStatus().getCodigo());

      FabricanteModel.setDescricao(Fabricante.getDescricao());
      FabricanteModel.setStatus(statusCadastroModel);

      return FabricanteMapper.INSTANCE.toDomain(entityManager.merge(FabricanteModel));
    } else {
      throw new NotFoundException("Fabricante não encontrada");
    }
  }

  public void delete(UUID idFabricante) {
    FabricanteModel fabrincate = entityManager.find(FabricanteModel.class, idFabricante);

    if (Objects.nonNull(fabrincate)) {
      entityManager.remove(fabrincate);
    } else {
      throw new NotFoundException("Fabricante não encontrada");
    }
  }
}
