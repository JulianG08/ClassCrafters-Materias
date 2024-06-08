package co.edu.uco.tutorspace.data.dao.impl;

import co.edu.uco.tutorspace.data.dao.MateriaDAO;
import co.edu.uco.tutorspace.entity.MateriaEntity;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAOImpl implements MateriaDAO {

    private final Connection connection;

    public MateriaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(MateriaEntity materia) throws SQLException {
        String sql = "INSERT INTO materia (nombre, institucion) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, materia.getNombre());
            preparedStatement.setString(2, materia.getInstitucion());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<MateriaEntity> consultar(MateriaEntity filtro) throws SQLException {
        List<MateriaEntity> materias = new ArrayList<>();
        String sql = "SELECT id, nombre, institucion FROM materia WHERE 1=1";
        if (filtro.getId() > 0) {
            sql += " AND id = " + filtro.getId();
        }
        if (!TextHelper.isNullOrEmpty(filtro.getNombre())) {
            sql += " AND nombre LIKE '%" + filtro.getNombre() + "%'";
        }
        if (!TextHelper.isNullOrEmpty(filtro.getInstitucion())) {
            sql += " AND institucion = '" + filtro.getInstitucion() + "'";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                MateriaEntity materia = MateriaEntity.build(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("institucion")
                );
                materias.add(materia);
            }
        }

        return materias;
    }

    @Override
    public void actualizar(MateriaEntity materia) throws SQLException {
        String sql = "UPDATE materia SET nombre = ?, institucion = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, materia.getNombre());
            preparedStatement.setString(2, materia.getInstitucion());
            preparedStatement.setInt(3, materia.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM materia WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean existsByNameAndInstitucion(String nombre, String institucion) throws SQLException {
        String sql = "SELECT COUNT(*) FROM materia WHERE nombre = ? AND institucion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, institucion);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
