package br.com.gocharg.processor.usuario;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Cidade;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.domain.Usuario;
import br.com.gocharg.dto.UsuarioDTO;
import br.com.gocharg.exceptions.UnprocessableEntityException;
import br.com.gocharg.mappers.UsuarioMapper;
import br.com.gocharg.processor.cidade.BuscaCidadePorIdProcessor;
import br.com.gocharg.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharg.repository.UsuarioRepository;
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
