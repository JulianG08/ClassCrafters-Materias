package co.edu.uco.tutorspace.business.assembler.dto.impl;

import static co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.tutorspace.business.assembler.dto.AssemblerDTO;
import co.edu.uco.tutorspace.business.domain.UsuarioDomain;
import co.edu.uco.tutorspace.dto.UsuarioDTO;

public final class UsuarioAssemblerDTO implements AssemblerDTO<UsuarioDomain, UsuarioDTO>{

	private static final AssemblerDTO<UsuarioDomain, UsuarioDTO> instance = new UsuarioAssemblerDTO();

    public UsuarioAssemblerDTO() {
        super();
    }

    public static final AssemblerDTO<UsuarioDomain, UsuarioDTO> getInstance() {
        return instance;
    }

    @Override
    public final UsuarioDomain toDomain(final UsuarioDTO data) {
        var usuarioDtoTmp = getObjectHelper().getDefaultValue(data, UsuarioDTO.build());
        return UsuarioDomain.build(usuarioDtoTmp.getId(), usuarioDtoTmp.getNombre(), usuarioDtoTmp.getApellido(), usuarioDtoTmp.getTipoDocumento(), usuarioDtoTmp.getNumeroDocumento(), usuarioDtoTmp.getTipoUsuario(), usuarioDtoTmp.getCorreoElectronico(), usuarioDtoTmp.getClave(), usuarioDtoTmp.getTelefono(), usuarioDtoTmp.getDireccion(), usuarioDtoTmp.getInstitucion());
    }

    @Override
    public final UsuarioDTO toDTO(final UsuarioDomain domain) {
        var usuarioDomainTmp = getObjectHelper().getDefaultValue(domain, UsuarioDomain.build());
        return UsuarioDTO.build().setId(usuarioDomainTmp.getId()).setNombre(usuarioDomainTmp.getNombre()).setApellido(usuarioDomainTmp.getApellido()).setTipoDocumento(usuarioDomainTmp.getTipoDocumento()).setNumeroDocumento(usuarioDomainTmp.getNumeroDocumento()).setTipoUsuario(usuarioDomainTmp.getTipoUsuario()).setCorreoElectronico(usuarioDomainTmp.getCorreoElectronico()).setClave(usuarioDomainTmp.getClave()).setTelefono(usuarioDomainTmp.getTelefono()).setDireccion(usuarioDomainTmp.getDireccion()).setInstitucion(usuarioDomainTmp.getInstitucion());
    }

    @Override
    public final List<UsuarioDomain> toDomainCollection(final List<UsuarioDTO> dtoCollection) {
        var dtoCollectionTmp = getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<UsuarioDTO>());
        var resultadosDomain = new ArrayList<UsuarioDomain>();

        for (UsuarioDTO usuarioDTO : dtoCollectionTmp){
            var usuarioDomainTmp = toDomain(usuarioDTO);
            resultadosDomain.add(usuarioDomainTmp);
        }
        return resultadosDomain;
    }

    @Override
    public final List<UsuarioDTO> toDTOCollection(final List<UsuarioDomain> domainCollection) {
        var domainCollectionTmp = getObjectHelper().getDefaultValue(domainCollection, new ArrayList<UsuarioDomain>());
        return domainCollectionTmp.stream().map(this :: toDTO).toList();
    }
}
