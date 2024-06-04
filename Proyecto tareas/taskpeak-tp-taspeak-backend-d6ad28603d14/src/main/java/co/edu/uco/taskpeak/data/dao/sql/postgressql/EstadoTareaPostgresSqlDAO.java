package co.edu.uco.taskpeak.data.dao.sql.postgressql;

import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Prioridad;
import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.data.dao.CategoriaTareaDAO;
import co.edu.uco.taskpeak.data.dao.EstadoTareaDAO;
import co.edu.uco.taskpeak.data.dao.sql.SqlConnection;
import co.edu.uco.taskpeak.entity.CategoriaTareaEntity;
import co.edu.uco.taskpeak.entity.EstadoTareaEntity;
import co.edu.uco.taskpeak.entity.TareaEntity;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstadoTareaPostgresSqlDAO extends SqlConnection implements EstadoTareaDAO {
    public EstadoTareaPostgresSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void crear(final EstadoTareaEntity estadoTarea) {
        final var sql = "INSERT INTO estado_tarea(id, nombre_estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, Integer.toString(estadoTarea.getId()));
            statement.setString(2, estadoTarea.getNombreEstado());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actualizar(final EstadoTareaEntity estadoTarea) {
        final var sql = "UPDATE estado_tarea SET nombre_estado = ? WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, estadoTarea.getNombreEstado());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }
    @Override
    public void eliminar(final int id) {
        final var sql = "DELETE FROM estado_tarea WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, Integer.toString(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }

    @Override
    public List<EstadoTareaEntity> consultar(EstadoTareaEntity filtro) {
        List<EstadoTareaEntity> estados = new ArrayList<>();
        var sql = new StringBuilder("SELECT id,nombre_estado FROM estado_tarea ");
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getId() != 0) {
                conditions.add("t.id = ?");
                parameters.add(filtro.getId());
            }
            if (filtro.getNombreEstado() != null) {
                conditions.add("LOWER(nombre_estado) LIKE LOWER(?)");
                parameters.add("%" + filtro.getNombreEstado().toLowerCase() + "%");
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
                    int id = Integer.parseInt(resultSet.getString("id"));
                    String nombreEstado = resultSet.getString("nombre_estado");
                    EstadoTareaEntity estadoTarea =EstadoTareaEntity.build(id,nombreEstado);
                    estados.add(estadoTarea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }
}
