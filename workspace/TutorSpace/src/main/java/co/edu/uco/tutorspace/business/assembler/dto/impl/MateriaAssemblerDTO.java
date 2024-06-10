package co.edu.uco.tutorspace.business.assembler.dto.impl;

import co.edu.uco.tutorspace.business.assembler.dto.AssemblerDTO;
import co.edu.uco.tutorspace.business.domain.MateriaDomain;
import static co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.tutorspace.dto.MateriaDTO;

public final class MateriaAssemblerDTO implements AssemblerDTO<MateriaDomain, MateriaDTO>{

    private static final AssemblerDTO<MateriaDomain, MateriaDTO> instance = new MateriaAssemblerDTO();

    public MateriaAssemblerDTO() {
        super();
    }

    public static final AssemblerDTO<MateriaDomain, MateriaDTO> getInstance() {
        return instance;
    }

    @Override
    public final MateriaDomain toDomain(final MateriaDTO data) {
        var materiaDtoTmp = getObjectHelper().getDefaultValue(data, MateriaDTO.build());
        return MateriaDomain.build(materiaDtoTmp.getId(), materiaDtoTmp.getNombre(), materiaDtoTmp.getInstitucion());
    }

    @Override
    public final MateriaDTO toDTO(final MateriaDomain domain) {
        var materiaDomainTmp = getObjectHelper().getDefaultValue(domain, MateriaDomain.build());
        return MateriaDTO.build().setId(materiaDomainTmp.getId()).setNombre(materiaDomainTmp.getNombre()).setInstitucion(materiaDomainTmp.getInstitucion());
    }

    @Override
    public final List<MateriaDomain> toDomainCollection(final List<MateriaDTO> dtoCollection) {
        var dtoCollectionTmp = getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<MateriaDTO>());
        var resultadosDomain = new ArrayList<MateriaDomain>();

        for (MateriaDTO materiaDTO : dtoCollectionTmp){
            var materiaDomainTmp = toDomain(materiaDTO);
            resultadosDomain.add(materiaDomainTmp);
        }
        return resultadosDomain;
    }

    @Override
    public final List<MateriaDTO> toDTOCollection(final List<MateriaDomain> domainCollection) {
        var domainCollectionTmp = getObjectHelper().getDefaultValue(domainCollection, new ArrayList<MateriaDomain>());
        return domainCollectionTmp.stream().map(this :: toDTO).toList();
    }
}
