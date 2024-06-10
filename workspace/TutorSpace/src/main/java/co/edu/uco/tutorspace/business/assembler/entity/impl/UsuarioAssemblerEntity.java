package co.edu.uco.tutorspace.business.assembler.entity.impl;

import static co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.tutorspace.business.assembler.entity.AssemblerEntity;
import co.edu.uco.tutorspace.business.domain.UsuarioDomain;
import co.edu.uco.tutorspace.entity.UsuarioEntity;

public class UsuarioAssemblerEntity implements AssemblerEntity<UsuarioDomain, UsuarioEntity> {

	private static final AssemblerEntity<UsuarioDomain, UsuarioEntity> instance = new UsuarioAssemblerEntity();

    public UsuarioAssemblerEntity() {
        super();
    }

    public static final AssemblerEntity<UsuarioDomain, UsuarioEntity> getInstance() {
        return instance;
    }

    @Override
    public final UsuarioDomain toDomain(final UsuarioEntity data) {
        var usuarioEntityTmp = getObjectHelper().getDefaultValue(data, UsuarioEntity.build());
        return UsuarioDomain.build(usuarioEntityTmp.getId(), usuarioEntityTmp.getNombre(), usuarioEntityTmp.getApellido(), usuarioEntityTmp.getTipoDocumento(), usuarioEntityTmp.getNumeroDocumento(), usuarioEntityTmp.getTipoUsuario(), usuarioEntityTmp.getCorreoElectronico(), usuarioEntityTmp.getClave(), usuarioEntityTmp.getTelefono(), usuarioEntityTmp.getDireccion(), usuarioEntityTmp.getInstitucion());
    }

    @Override
    public final UsuarioEntity toEntity(final UsuarioDomain domain) {
        var usuarioDomainTmp = getObjectHelper().getDefaultValue(domain, UsuarioDomain.build());
        return UsuarioEntity.build().setId(usuarioDomainTmp.getId()).setNombre(usuarioDomainTmp.getNombre()).setApellido(usuarioDomainTmp.getApellido()).setTipoDocumento(usuarioDomainTmp.getTipoDocumento()).setNumeroDocumento(usuarioDomainTmp.getNumeroDocumento()).setTipoUsuario(usuarioDomainTmp.getTipoUsuario()).setCorreoElectronico(usuarioDomainTmp.getCorreoElectronico()).setClave(usuarioDomainTmp.getClave()).setTelefono(usuarioDomainTmp.getTelefono()).setDireccion(usuarioDomainTmp.getDireccion()).setInstitucion(usuarioDomainTmp.getInstitucion());
    }

    @Override
    public final List<UsuarioDomain> toDomainCollection(final List<UsuarioEntity> entityCollection) {
        var entityCollectionTmp = getObjectHelper().getDefaultValue(entityCollection, new ArrayList<UsuarioEntity>());
        var resultadosDomain = new ArrayList<UsuarioDomain>();

        for (UsuarioEntity usuarioEntity : entityCollectionTmp){
            var usuarioDomainTmp= toDomain(usuarioEntity);
            resultadosDomain.add(usuarioDomainTmp);
        }
        return resultadosDomain;
    }

    @Override
    public List<UsuarioEntity> toEntityCollection(List<UsuarioDomain> domainCollection) {
        var domainCollectionTmp = getObjectHelper().getDefaultValue(domainCollection,new ArrayList<UsuarioDomain>());
        return domainCollectionTmp.stream().map(this :: toEntity).toList();
    }
}
