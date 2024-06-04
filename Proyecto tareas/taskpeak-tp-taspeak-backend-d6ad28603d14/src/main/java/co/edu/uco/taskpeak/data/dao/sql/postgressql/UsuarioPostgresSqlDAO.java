package co.edu.uco.taskpeak.data.dao.sql.postgressql;

import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.data.dao.UsuarioDAO;
import co.edu.uco.taskpeak.data.dao.sql.SqlConnection;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioPostgresSqlDAO extends SqlConnection implements UsuarioDAO {
    public UsuarioPostgresSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void crear(final UsuarioEntity usuario) {
        final var sql = "INSERT INTO usuario(id, nombre, apellido, correo, password) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, usuario.getId().toString());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getApellido());
            statement.setString(4, usuario.getCorreo());
            statement.setString(5, usuario.getPassowrd());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(final UsuarioEntity usuario) {
        final var sql = "UPDATE Usuario SET nombre = ?, apellido = ?, correo = ?, password = ? WHERE uuid = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getPassowrd());
            statement.setString(5, usuario.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }

    @Override
    public void eliminar(final UUID uuid) {
        final var sql = "DELETE FROM Usuario WHERE uuid = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }

    @Override
    public List<UsuarioEntity> consultar(UsuarioEntity filtro) {
        List<UsuarioEntity> usuarios = new ArrayList<>();
        System.out.println("filtro " + filtro.getNombre());
        var sql = new StringBuilder("SELECT id, nombre, apellido, correo, password FROM usuario ");
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getId() != null) {
                conditions.add("id = ?");
                parameters.add(filtro.getId().toString());
            }
            if (filtro.getCorreo() != TextHelper.EMPTY) {
                conditions.add("LOWER(correo) LIKE LOWER(?)");
                parameters.add("%" + filtro.getCorreo().toLowerCase() + "%");
            }
        }
        if (!conditions.isEmpty()) {
            sql.append("WHERE ");
            sql.append(String.join(" OR ", conditions));
        }

        try (PreparedStatement statement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID uuid = UUID.fromString(resultSet.getString("id"));
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String correo = resultSet.getString("correo");
                    String password = resultSet.getString("password");
                    UsuarioEntity usuario = UsuarioEntity.build(uuid, nombre, apellido, correo, password);
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
