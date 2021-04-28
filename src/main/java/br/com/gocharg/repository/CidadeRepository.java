package br.com.gocharg.repository;

import br.com.gocharg.domain.Cidade;
import br.com.gocharg.exceptions.NoContentException;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.mappers.CidadeMapper;
import br.com.gocharg.mappers.EstadoMapper;
import br.com.gocharg.model.CidadeModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CidadeRepository {

  @PersistenceContext EntityManager entityManager;

  public List<Cidade> getAll() {
    List<CidadeModel> cidades =
        entityManager.createQuery("SELECT c FROM CidadeModel c").getResultList();

    if (cidades.size() > 0) {
      return CidadeMapper.INSTANCE.toDomain(cidades);
    } else {
      throw new NoContentException();
    }
  }

  public List<Cidade> getByEstado(String idEstado) {
    List<CidadeModel> cidades =
        entityManager
            .createQuery("SELECT c FROM CidadeModel c WHERE c.estado.id = :idEstado")
            .setParameter("idEstado", idEstado)
            .getResultList();

    if (cidades.size() > 0) {
      return CidadeMapper.INSTANCE.toDomain(cidades);
    } else {
      throw new NoContentException();
    }
  }

  public Cidade getById(Integer id) {
    CidadeModel cidade = entityManager.find(CidadeModel.class, id);

    if (Objects.nonNull(cidade)) {
      return CidadeMapper.INSTANCE.toDomain(cidade);
    } else {
      throw new NotFoundException("Cidade não encontrada");
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
      cidadeModel.setEstado(EstadoMapper.INSTANCE.toModel(cidade.getEstado()));

      return CidadeMapper.INSTANCE.toDomain(entityManager.merge(cidadeModel));
    } else {
      throw new NotFoundException("Cidade não encontrada");
    }
  }

  public void delete(Integer id) {
    CidadeModel cidadeModel = entityManager.find(CidadeModel.class, id);

    if (Objects.nonNull(cidadeModel)) {
      entityManager.remove(cidadeModel);
    } else {
      throw new NotFoundException("Cidade não encontrada");
    }
  }
}
