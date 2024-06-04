package co.edu.uco.taskpeak.data.dao.sql.postgressql;

import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Prioridad;
import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.data.dao.TareaDAO;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TareaPostgresSqlDAO extends SqlConnection implements TareaDAO {


    public TareaPostgresSqlDAO(final Connection connection) {
        super(connection);
    }


    @Override
    public void crear(final TareaEntity tarea) {
        final var sql = "INSERT INTO Tarea(id, usuario, nombreTarea, descripcionTarea, estado, prioridad, fechaLimite, fechaCreacion, ultimaActualizacion) VALUES (?,?,?,?,?,?,?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, tarea.getId().toString());
            statement.setString(2, tarea.getUsuario().getId().toString());
            statement.setString(3, tarea.getNombreTarea());
            statement.setString(4, tarea.getDescripcionTarea());
            statement.setString(5, tarea.getEstado().getNombreEstado());
            statement.setString(6, tarea.getPrioridad().toString());
            statement.setString(7, tarea.getFechaLimite().toString());
            statement.setString(8, tarea.getFechaCreacion().toString());
            statement.setString(9, tarea.getUltimaActualizacion().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(final TareaEntity tarea) {
        final var sql = "UPDATE Tarea SET usuario =?, nombreTarea =?, descripcionTarea =?, estado =?, prioridad =?, fechaLimite =?, fechaCreacion =?, ultimaActualizacion =? WHERE uuid =?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, tarea.getUsuario().toString());
            statement.setString(2, tarea.getNombreTarea());
            statement.setString(3, tarea.getDescripcionTarea());
            statement.setString(4, tarea.getEstado().toString());
            statement.setString(5, tarea.getPrioridad().toString());
            statement.setString(6, tarea.getFechaLimite().toString());
            statement.setString(7, tarea.getFechaCreacion().toString());
            statement.setString(8, tarea.getUltimaActualizacion().toString());
            statement.setString(9, tarea.getId().toString());
        } catch (SQLException e) {
            // Proper error handling
        }
    }

    @Override
    public void eliminar(final UUID uuid) {
        final var sql = "DELETE FROM Tarea WHERE uuid =?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }

    }

    @Override
    public List<TareaEntity> consultar(TareaEntity filtro) {
        List<TareaEntity> tareas = new ArrayList<>();
        var sql = new StringBuilder("SELECT t.id id, usuario, nombre_tarea, descripcion_tarea, estado, prioridad, fecha_limite, fecha_creacion, ultima_actualizacion,u.id idUser,u.nombre nameUser,u.apellido apellidoUser,u.correo correoUser,et.id idEstado,et.nombre_estado nombreEstado FROM tarea t JOIN usuario u on u.id = t.usuario JOIN estado_tarea et on et.id = t.estado ");
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getId() != null) {
                conditions.add("t.id = ?");
                parameters.add(filtro.getId().toString());
            }
            if (!filtro.getUsuario().toString().isEmpty()) {
                conditions.add("usuario = ?");
                parameters.add(filtro.getUsuario().getId().toString());
            }
            if (filtro.getNombreTarea() != null) {
                conditions.add("LOWER(nombre_tarea) LIKE LOWER(?)");
                parameters.add("%" + filtro.getNombreTarea().toLowerCase() + "%");
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
                    UUID uuid = UUID.fromString(resultSet.getString("id"));
                    String nombreTarea = resultSet.getString("nombre_tarea");
                    String descripcionTarea = resultSet.getString("descripcion_tarea");
                    int idestado = Integer.parseInt(resultSet.getString("idEstado"));
                    String nombreestado = resultSet.getString("nombreestado");
                    String prioridad = resultSet.getString("prioridad");
                    LocalDateTime fechaLimite = LocalDateTime.parse(resultSet.getString("fecha_limite"), DateHelper.FORMATTER);
                    LocalDateTime fechaCreacion = LocalDateTime.parse(resultSet.getString("fecha_creacion"), DateHelper.FORMATTER);
                    LocalDateTime ultimaActualizacion = LocalDateTime.parse(resultSet.getString("ultima_actualizacion"), DateHelper.FORMATTER);
                    UUID idUser = UUID.fromString(resultSet.getString("iduser"));
                    String nameuser = resultSet.getString("nameuser");
                    String apellidouser = resultSet.getString("apellidouser");
                    String correouser = resultSet.getString("correouser");
                    TareaEntity tarea = TareaEntity.build(uuid, UsuarioEntity.build(idUser, nameuser, apellidouser, correouser, ""), nombreTarea, descripcionTarea, EstadoTareaEntity.build(idestado, nombreestado), Prioridad.valueOf(prioridad), fechaLimite, fechaCreacion, ultimaActualizacion);
                    tareas.add(tarea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }
}
