package co.edu.uco.taskpeak.data.dao.sql.postgressql;

import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.data.dao.CategoriaTareaDAO;
import co.edu.uco.taskpeak.data.dao.sql.SqlConnection;
import co.edu.uco.taskpeak.entity.CategoriaTareaEntity;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoriaTareaPostgresSqlDAO extends SqlConnection implements CategoriaTareaDAO {
    public CategoriaTareaPostgresSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void crear(final CategoriaTareaEntity categoriaTarea) {
        final var sql = "INSERT INTO categoria_tarea(id, usuario, nombre_categoria, descripcion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, categoriaTarea.getId().toString());
            statement.setString(2, categoriaTarea.getUsuario().getId().toString());
            statement.setString(3, categoriaTarea.getNombreCategoria());
            statement.setString(4, categoriaTarea.getDescripcion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(final CategoriaTareaEntity categoriaTarea) {
        final var sql = "UPDATE categoria_tarea SET nombre_categoria = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, categoriaTarea.getNombreCategoria());
            statement.setString(2, categoriaTarea.getDescripcion());
            statement.setString(3, categoriaTarea.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }

    @Override
    public void eliminar(final UUID id) {
        final var sql = "DELETE FROM categoria_tarea WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }

    @Override
    public List<CategoriaTareaEntity> consultar(CategoriaTareaEntity filtro) {
        List<CategoriaTareaEntity> categorias = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT c.id as id,nombre_categoria,descripcion,u.id idUser,u.nombre nameUser,u.apellido apellidoUser,u.correo correoUser FROM categoria_tarea c JOIN usuario u on u.id = c.usuario ");
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getId() != null) {
                conditions.add("c.id = ?");
                parameters.add(filtro.getId().toString());
            }
            if (!filtro.getUsuario().toString().isEmpty()) {
                conditions.add("usuario = ?");
                parameters.add(filtro.getUsuario().getId().toString());
            }
            if (filtro.getNombreCategoria() != TextHelper.EMPTY) {
                conditions.add("LOWER(nombre_categoria) LIKE LOWER(?)");
                parameters.add("%" + filtro.getNombreCategoria().toLowerCase() + "%");
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
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String nombre_categoria = resultSet.getString("nombre_categoria");
                    String description = resultSet.getString("descripcion");
                    UUID iduser = UUID.fromString(resultSet.getString("iduser"));
                    String nameuser = resultSet.getString("nameuser");
                    String apellidouser = resultSet.getString("apellidouser");
                    String correouser = resultSet.getString("correouser");
                    CategoriaTareaEntity categoria = CategoriaTareaEntity.build(id, UsuarioEntity.build(iduser,nameuser,apellidouser,correouser,""),nombre_categoria,description);
                    categorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
