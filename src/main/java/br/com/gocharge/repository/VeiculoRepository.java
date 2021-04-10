package br.com.gocharge.repository;

import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Veiculo;
import br.com.gocharge.exceptions.NoContentException;
import br.com.gocharge.exceptions.NotFoundException;
import br.com.gocharge.mappers.BandeiraMapper;
import br.com.gocharge.mappers.VeiculoMapper;
import br.com.gocharge.model.BandeiraModel;
import br.com.gocharge.model.StatusCadastroModel;
import br.com.gocharge.model.VeiculoModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class VeiculoRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Veiculo> getAll() {
    List<VeiculoModel> veiculos =
        entityManager.createQuery("SELECT v FROM VeiculoModel v").getResultList();

    if (veiculos.size() > 0) {
      return VeiculoMapper.INSTANCE.toDomain(veiculos);
    } else {
      throw new NoContentException();
    }
  }

  public Veiculo getById(UUID id) {
    VeiculoModel veiculo = entityManager.find(VeiculoModel.class, id);

    if (Objects.nonNull(veiculo)) {
      return VeiculoMapper.INSTANCE.toDomain(veiculo);
    } else {
      throw new NotFoundException("Veículo não encontrado");
    }
  }

  public Veiculo create(Veiculo veiculo) {
    VeiculoModel veiculoModel = VeiculoMapper.INSTANCE.toModel(veiculo);

    entityManager.persist(veiculoModel);

    return VeiculoMapper.INSTANCE.toDomain(veiculoModel);
  }

  public Veiculo update(Veiculo veiculo) {
    VeiculoModel veiculoModel = entityManager.find(VeiculoModel.class, veiculo.getId());

    if (Objects.nonNull(veiculoModel)) {
      VeiculoMapper.INSTANCE.updateFrom(veiculo, veiculoModel);

      return VeiculoMapper.INSTANCE.toDomain(entityManager.merge(veiculoModel));
    } else {
      throw new NotFoundException("Veículo não encontrado");
    }
  }

  public void delete(UUID id) {
    VeiculoModel veiculo = entityManager.find(VeiculoModel.class, id);

    if (Objects.nonNull(veiculo)) {
      entityManager.remove(veiculo);
    } else {
      throw new NotFoundException("Veículo não encontrado");
    }
  }
}
