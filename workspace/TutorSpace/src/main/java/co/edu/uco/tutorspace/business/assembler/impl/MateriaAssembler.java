package co.edu.uco.tutorspace.business.assembler.impl;

import co.edu.uco.tutorspace.business.assembler.Assembler;
import co.edu.uco.tutorspace.business.domain.MateriaDomain;
import co.edu.uco.tutorspace.dto.MateriaDTO;
import co.edu.uco.tutorspace.entity.MateriaEntity;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;

public class MateriaAssembler implements Assembler<MateriaDTO, MateriaDomain, MateriaEntity> {

    private static final MateriaAssembler instance = new MateriaAssembler();

    private MateriaAssembler() {
        super();
    }

    public static MateriaAssembler getInstance() {
        return instance;
    }

    @Override
    public MateriaDTO toDTO(MateriaDomain domain) {
        if (ObjectHelper.getObjectHelper().isNull(domain)) {
            return MateriaDTO.build();
        }
        return MateriaDTO.build()
                .setId(domain.getId())
                .setNombre(domain.getNombre())
                .setInstitucion(domain.getInstitucion());
    }

    @Override
    public MateriaDomain toDomain(MateriaDTO dto) {
        if (ObjectHelper.getObjectHelper().isNull(dto)) {
            return MateriaDomain.create();
        }
        return MateriaDomain.create(dto.getId(), dto.getNombre(), dto.getInstitucion());
    }

    @Override
    public MateriaEntity toEntity(MateriaDomain domain) {
        if (ObjectHelper.getObjectHelper().isNull(domain)) {
            return MateriaEntity.build();
        }
        return MateriaEntity.build(domain.getId(), domain.getNombre(), domain.getInstitucion());
    }

    @Override
    public MateriaDomain fromEntity(MateriaEntity entity) {
        if (ObjectHelper.getObjectHelper().isNull(entity)) {
            return MateriaDomain.create();
        }
        return MateriaDomain.create(entity.getId(), entity.getNombre(), entity.getInstitucion());
    }

    public MateriaDTO toDTO(MateriaEntity entity) {
        if (ObjectHelper.getObjectHelper().isNull(entity)) {
            return MateriaDTO.build();
        }
        return MateriaDTO.build()
                .setId(entity.getId())
                .setNombre(entity.getNombre())
                .setInstitucion(entity.getInstitucion());
    }
}
