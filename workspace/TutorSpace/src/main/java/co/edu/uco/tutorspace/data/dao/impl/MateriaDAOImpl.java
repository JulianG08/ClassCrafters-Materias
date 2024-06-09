package co.edu.uco.tutorspace.data.dao.impl;

import co.edu.uco.tutorspace.data.dao.MateriaDAO;
import co.edu.uco.tutorspace.entity.MateriaEntity;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.DataTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MateriaDAOImpl implements MateriaDAO {

    private final Connection connection;

    public MateriaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(MateriaEntity materia) {
        String sql = "INSERT INTO materia (id, nombre, institucion) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setString(2, materia.getNombre());
            preparedStatement.setString(3, materia.getInstitucion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            String mensajeTecnico = "Error al crear la materia en la base de datos";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, e);
        }
    }

    @Override
    public List<MateriaEntity> consultar(MateriaEntity filtro) {
        List<MateriaEntity> materias = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre, institucion FROM materia WHERE 1=1");

        if (filtro.getId() != null) {
            sql.append(" AND id = '").append(filtro.getId().toString()).append("'");
        }
        if (!TextHelper.isNullOrEmpty(filtro.getNombre())) {
            sql.append(" AND nombre LIKE '%").append(filtro.getNombre()).append("%'");
        }
        if (!TextHelper.isNullOrEmpty(filtro.getInstitucion())) {
            sql.append(" AND institucion = '").append(filtro.getInstitucion()).append("'");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                MateriaEntity materia = MateriaEntity.build(
                    (UUID) resultSet.getObject("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("institucion")
                );
                materias.add(materia);
            }
        } catch (SQLException e) {
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            String mensajeTecnico = "Error al consultar las materias en la base de datos";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, e);
        }

        return materias;
    }

    @Override
    public void actualizar(MateriaEntity materia) {
        String sql = "UPDATE materia SET nombre = ?, institucion = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, materia.getNombre());
            preparedStatement.setString(2, materia.getInstitucion());
            preparedStatement.setObject(3, materia.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            String mensajeTecnico = "Error al actualizar la materia en la base de datos";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, e);
        }
    }

    @Override
    public void eliminar(UUID id) {
        String sql = "DELETE FROM materia WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            String mensajeTecnico = "Error al eliminar la materia en la base de datos";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, e);
        }
    }

    @Override
    public boolean existsByNameAndInstitucion(String nombre, String institucion) {
        String sql = "SELECT COUNT(*) FROM materia WHERE nombre = ? AND institucion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, institucion);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            String mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            String mensajeTecnico = "Error al verificar si la materia existe en la base de datos";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, e);
        }
        return false;
    }
}

