package co.edu.uco.tutorspace.business.facade.impl;

import co.edu.uco.tutorspace.business.assembler.impl.MateriaAssembler;
import co.edu.uco.tutorspace.business.domain.MateriaDomain;
import co.edu.uco.tutorspace.business.facade.MateriaFacade;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.data.dao.MateriaDAO;
import co.edu.uco.tutorspace.data.dao.impl.MateriaDAOImpl;
import co.edu.uco.tutorspace.dto.MateriaDTO;
import co.edu.uco.tutorspace.entity.MateriaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MateriaFacadeImpl implements MateriaFacade {

    private final MateriaDAO materiaDAO;

    public MateriaFacadeImpl(Connection connection) {
        this.materiaDAO = new MateriaDAOImpl(connection);
    }

    @Override
    public void crear(MateriaDTO materia) {
        try {
            MateriaDomain domain = MateriaAssembler.getInstance().toDomain(materia);
            validarDatosObligatorios(domain);
            validarLongitudNombre(domain.getNombre());
            validarFormatoNombre(domain.getNombre());

            if (materiaDAO.existsByNameAndInstitucion(domain.getNombre(), domain.getInstitucion())) {
                throw new TutorSpaceException("La materia ya existe en la institución.", "La materia con ese nombre ya existe en la institución.", Lugar.BASE_DE_DATOS);
            }

            materiaDAO.crear(MateriaAssembler.getInstance().toEntity(domain));
        } catch (SQLException e) {
            throw TutorSpaceException.buildDatabaseException("Error al crear la materia en la base de datos", "No se pudo crear la materia, por favor intente nuevamente.", e);
        }
    }

    @Override
    public List<MateriaDTO> consultar(MateriaDTO filtro) {
        try {
            List<MateriaEntity> entities = materiaDAO.consultar(MateriaAssembler.getInstance().toEntity(MateriaAssembler.getInstance().toDomain(filtro)));
            return entities.stream()
                    .map(MateriaAssembler.getInstance()::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw TutorSpaceException.buildDatabaseException("Error al consultar las materias en la base de datos", "No se pudieron consultar las materias, por favor intente nuevamente.", e);
        }
    }

    @Override
    public void actualizar(MateriaDTO materia) {
        try {
            MateriaDomain domain = MateriaAssembler.getInstance().toDomain(materia);
            validarDatosObligatorios(domain);
            validarLongitudNombre(domain.getNombre());
            validarFormatoNombre(domain.getNombre());

            if (materiaDAO.existsByNameAndInstitucion(domain.getNombre(), domain.getInstitucion())) {
                throw new TutorSpaceException("La materia ya existe en la institución.", "La materia con ese nombre ya existe en la institución.", Lugar.BASE_DE_DATOS);
            }

            materiaDAO.actualizar(MateriaAssembler.getInstance().toEntity(domain));
        } catch (SQLException e) {
            throw TutorSpaceException.buildDatabaseException("Error al actualizar la materia en la base de datos", "No se pudo actualizar la materia, por favor intente nuevamente.", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            materiaDAO.eliminar(id);
        } catch (SQLException e) {
            throw TutorSpaceException.buildDatabaseException("Error al eliminar la materia en la base de datos", "No se pudo eliminar la materia, por favor intente nuevamente.", e);
        }
    }

    private void validarDatosObligatorios(MateriaDomain materia) {
        if (TextHelper.isNullOrEmpty(materia.getNombre()) || TextHelper.isNullOrEmpty(materia.getInstitucion())) {
            throw new TutorSpaceException("Datos incompletos.", "El nombre y la institución son obligatorios.", Lugar.NEGOCIO);
        }
    }

    private void validarLongitudNombre(String nombre) {
        if (nombre.length() < 3 || nombre.length() > 100) {
            throw new TutorSpaceException("Longitud del nombre inválida.", "El nombre debe tener entre 3 y 100 caracteres.", Lugar.NEGOCIO);
        }
    }

    private void validarFormatoNombre(String nombre) {
        if (!nombre.matches("[a-zA-Z]+")) {
            throw new TutorSpaceException("Formato de nombre inválido.", "El nombre solo puede contener letras del abecedario (sin espacios ni caracteres especiales).", Lugar.NEGOCIO);
        }
    }
}
