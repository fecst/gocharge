package br.com.gocharge.processor.usuario;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.dto.UsuarioDTO;
import br.com.gocharge.exceptions.UnprocessableEntityException;
import br.com.gocharge.mappers.UsuarioMapper;
import br.com.gocharge.processor.cidade.BuscaCidadePorIdProcessor;
import br.com.gocharge.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AlteraUsuarioProcessor implements CommandProcessor<Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;

    @Autowired
    private BuscaCidadePorIdProcessor buscaCidadePorIdProcessor;

    @Override
    public Usuario process(CommandContext context) {
        UsuarioDTO usuario = context.getProperty("usuarioDTO", UsuarioDTO.class);
        Estado estado = null;
        Cidade cidade = null;

        if (Objects.nonNull(usuario.getEstado())) {
            context.put("idEstado", usuario.getEstado());

            estado = buscaEstadoPorIdProcessor.process(context);
        }

        if (Objects.nonNull(usuario.getCidade())) {
            context.put("idCidade", Integer.valueOf(usuario.getCidade()));

            cidade = buscaCidadePorIdProcessor.process(context);
        }

        if (Objects.nonNull(usuario.getEstado()) &&
                Objects.nonNull(usuario.getCidade()) &&
                cidade.getEstado().getId() != estado.getId()) {
            throw new UnprocessableEntityException("Cidade não pertence ao Estado informado");
        }

        return usuarioRepository.update(UsuarioMapper.INSTANCE.toDomain(usuario, estado, cidade));
    }
}
