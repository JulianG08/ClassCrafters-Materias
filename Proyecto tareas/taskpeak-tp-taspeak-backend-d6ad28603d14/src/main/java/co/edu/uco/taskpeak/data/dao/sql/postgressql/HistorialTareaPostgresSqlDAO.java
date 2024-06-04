package co.edu.uco.taskpeak.data.dao.sql.postgressql;

import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.data.dao.HistorialTareaDAO;
import co.edu.uco.taskpeak.data.dao.sql.SqlConnection;
import co.edu.uco.taskpeak.entity.EstadoTareaEntity;
import co.edu.uco.taskpeak.entity.HistorialTareaEntity;
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

public class HistorialTareaPostgresSqlDAO extends SqlConnection implements HistorialTareaDAO {
    public HistorialTareaPostgresSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void crear(final HistorialTareaEntity historialTarea) {
        final var sql = "INSERT INTO hisorial_tarea(id, tarea, estato_anterior, estado_nuevo, fecha_actualizacion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, historialTarea.getId().toString());
            statement.setString(2, historialTarea.getTarea().getId().toString());
            statement.setString(3, historialTarea.getEstadoAnterior().getNombreEstado());
            statement.setString(4, historialTarea.getEstadoNuevo().getNombreEstado());
            statement.setString(5, historialTarea.getFechaActualizacion().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HistorialTareaEntity> consultar(HistorialTareaEntity filtro) {
        List<HistorialTareaEntity> historial = new ArrayList<>();
        var sql = new StringBuilder("SELECT h.id id, tarea,estado_anterior,estado_nuevo, editor, fecha_actualizacion,u.nombre nombreUser,apellido apellidoUser,u.correo correoUser,ea.nombre_estado nombreestadoanterior,en.nombre_estado nombreestadonuevo FROM historial_tarea h JOIN tarea t on t.id = h.tarea join usuario u on u.id = t.usuario JOIN estado_tarea ea on ea.id = estado_anterior  JOIN estado_tarea en on en.id = estado_nuevo ");
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getId() != null) {
                conditions.add("h.id = ?");
                parameters.add(filtro.getId().toString());
            }
            if (filtro.getTarea() !=null) {
                conditions.add("t.id = ?");
                parameters.add(filtro.getTarea().getId().toString());
            }
            if (filtro.getEditor()!=null) {
                conditions.add("u.id = ?");
                parameters.add(filtro.getEditor().getId().toString());
            }
            if (filtro.getFechaActualizacion() != null) {
                conditions.add("fecha_actualizacion = ?");
                parameters.add(filtro.getEditor().getId().toString());
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
                    UUID historialId = UUID.fromString(resultSet.getString("id"));
                    UUID tareaId = UUID.fromString(resultSet.getString("tarea"));
                    int estadoAnteriorId = Integer.parseInt( resultSet.getString("estado_anterior"));
                    String estadoAnterior = resultSet.getString("nombreestadoanterior");
                    int estadoNuevoId = Integer.parseInt( resultSet.getString("estado_nuevo"));
                    String estadoNuevo = resultSet.getString("nombreestadonuevo");
                    LocalDateTime fecha_actulizacion = LocalDateTime.parse(resultSet.getString("fecha_actualizacion"), DateHelper.FORMATTER);
                    UUID userId = UUID.fromString(resultSet.getString("editor"));
                    String nombreuser = resultSet.getString("nombreuser");
                    String apellidouser = resultSet.getString("apellidouser");
                    String correouser = resultSet.getString("correouser");
                    HistorialTareaEntity historialTarea = HistorialTareaEntity.build(
                            historialId,
                            TareaEntity.build(tareaId),
                            EstadoTareaEntity.build(estadoAnteriorId,estadoAnterior),
                            EstadoTareaEntity.build(estadoNuevoId,estadoNuevo),
                            UsuarioEntity.build(userId,nombreuser,apellidouser,correouser,""),
                            fecha_actulizacion
                    );
                    historial.add(historialTarea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historial;
    }
}
