package br.com.gocharge.repository;

import br.com.gocharge.domain.Cidade;
import br.com.gocharge.exceptions.NotFoundException;
import br.com.gocharge.mappers.CidadeMapper;
import br.com.gocharge.mappers.EstadoMapper;
import br.com.gocharge.model.CidadeModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class CidadeRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Cidade> getAll() {
    List<CidadeModel> cidades =
        entityManager.createQuery("SELECT c FROM CidadeModel c").getResultList();

    if (cidades.size() > 0) {
      return CidadeMapper.INSTANCE.toDomain(cidades);
    } else {
      throw new NotFoundException();
    }
  }

  public Cidade getById(UUID id) {
    CidadeModel cidade = entityManager.find(CidadeModel.class, id);

    if (Objects.nonNull(cidade)) {
      return CidadeMapper.INSTANCE.toDomain(cidade);
    } else {
      throw new NotFoundException();
    }
  }

  public Cidade create(Cidade cidade) {
    CidadeModel cidadeModel = CidadeMapper.INSTANCE.toModel(cidade);

    entityManager.persist(cidadeModel);

    return CidadeMapper.INSTANCE.toDomain(cidadeModel);
  }

  public Cidade update(Cidade cidade) {
    CidadeModel cidadeModel = entityManager.find(CidadeModel.class, cidade.getId());

    if (Objects.nonNull(cidade)) {
      cidadeModel.setDescricao(cidade.getDescricao());
      cidadeModel.setEstado(EstadoMapper.INSTANCE.toModel(cidade.getEstado()
      ));

      return CidadeMapper.INSTANCE.toDomain(entityManager.merge(cidadeModel));
    } else {
      throw new NotFoundException();
    }
  }

  public void delete(UUID id) {
    CidadeModel cidadeModel = entityManager.find(CidadeModel.class, id);

    if (Objects.nonNull(cidadeModel)) {
      entityManager.remove(cidadeModel);
    } else {
      throw new NotFoundException();
    }
  }
}
