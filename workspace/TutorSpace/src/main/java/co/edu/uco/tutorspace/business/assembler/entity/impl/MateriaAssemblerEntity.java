package co.edu.uco.tutorspace.business.assembler.entity.impl;

import co.edu.uco.tutorspace.business.assembler.entity.AssemblerEntity;
import co.edu.uco.tutorspace.business.domain.MateriaDomain;
import static co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.tutorspace.entity.MateriaEntity;

public final class MateriaAssemblerEntity implements AssemblerEntity<MateriaDomain, MateriaEntity> {

    private static final AssemblerEntity<MateriaDomain, MateriaEntity> instance = new MateriaAssemblerEntity();

    public MateriaAssemblerEntity() {
        super();
    }

    public static final AssemblerEntity<MateriaDomain, MateriaEntity> getInstance() {
        return instance;
    }

    @Override
    public final MateriaDomain toDomain(final MateriaEntity data) {
        var materiaEntityTmp = getObjectHelper().getDefaultValue(data, MateriaEntity.build());
        return MateriaDomain.build(materiaEntityTmp.getId(), materiaEntityTmp.getNombre(), materiaEntityTmp.getInstitucion());
    }

    @Override
    public final MateriaEntity toEntity(final MateriaDomain domain) {
        var materiaDomainTmp = getObjectHelper().getDefaultValue(domain, MateriaDomain.build());
        return MateriaEntity.build().setId(materiaDomainTmp.getId()).setNombre(materiaDomainTmp.getNombre()).setInstitucion(materiaDomainTmp.getInstitucion());
    }

    @Override
    public final List<MateriaDomain> toDomainCollection(final List<MateriaEntity> entityCollection) {
        var entityCollectionTmp = getObjectHelper().getDefaultValue(entityCollection, new ArrayList<MateriaEntity>());
        var resultadosDomain = new ArrayList<MateriaDomain>();

        for (MateriaEntity materiaEntity : entityCollectionTmp){
            var materiaDomainTmp= toDomain(materiaEntity);
            resultadosDomain.add(materiaDomainTmp);
        }
        return resultadosDomain;
    }

    @Override
    public List<MateriaEntity> toEntityCollection(List<MateriaDomain> domainCollection) {
        var domainCollectionTmp = getObjectHelper().getDefaultValue(domainCollection,new ArrayList<MateriaDomain>());
        return domainCollectionTmp.stream().map(this :: toEntity).toList();
    }
}
