package co.edu.uco.taskpeak.business.assembler.dto.concrete;

import co.edu.uco.taskpeak.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.taskpeak.business.domain.UsuarioDomain;
import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTODomainAssembler implements DTODomainAssembler<UsuarioDomain, UsuarioDTO> {
    private static final DTODomainAssembler<UsuarioDomain, UsuarioDTO> instancia = new UsuarioDTODomainAssembler();

    public static final DTODomainAssembler<UsuarioDomain, UsuarioDTO> obtenerInstancia() {
        return instancia;
    }
    @Override
    public final UsuarioDomain ensamblarDominio(final UsuarioDTO dto) {
        var usuarioDtoTemp = ObjectHelper.getObjectHelper().getDefault(dto, UsuarioDTO.build());
        return UsuarioDomain.crear(usuarioDtoTemp.getId(),usuarioDtoTemp.getNombre(),usuarioDtoTemp.getApellido(),usuarioDtoTemp.getCorreo(),"");

    }
    @Override
    public final UsuarioDTO ensamblarDTO(final UsuarioDomain dominio) {
        var usuarioDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, UsuarioDomain.crear());
        return new UsuarioDTO(usuarioDomainTemp.getId(), usuarioDomainTemp.getNombre(), usuarioDomainTemp.getApellido(),usuarioDomainTemp.getCorreo(), "");
    }
    @Override
    public List<UsuarioDTO> ensamblarListaDTO(List<UsuarioDomain> listaDominios) {
        var listaDominiosTmp = ObjectHelper.getObjectHelper().getDefault(listaDominios, new ArrayList<>());
        var resultados = new ArrayList<UsuarioDTO>();
        for (var usuarioDomain : listaDominiosTmp) {
            var usuarioDTOTmp = ensamblarDTO((UsuarioDomain) usuarioDomain);
            resultados.add(usuarioDTOTmp);
        }
        return resultados;
    }

    @Override
    public List<UsuarioDomain> ensamblarListaDominios(List<UsuarioDTO> listaDtos) {
        var listaDtosTmp = ObjectHelper.getObjectHelper().getDefault(listaDtos, new ArrayList<UsuarioDTO>());
        var resultados = new ArrayList<UsuarioDomain>();

        for (UsuarioDTO usuarioDTO : listaDtosTmp) {
            var usuarioDomainTmp = ensamblarDominio(usuarioDTO);
            resultados.add(usuarioDomainTmp);
        }
        return resultados;
    }


}
