package co.edu.uco.taskpeak.data.dao.sql.postgressql;

import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Prioridad;
import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.data.dao.TareaUsuarioDAO;
import co.edu.uco.taskpeak.data.dao.sql.SqlConnection;
import co.edu.uco.taskpeak.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TareaUsuarioPostgresSqlDAO extends SqlConnection implements TareaUsuarioDAO {

    public TareaUsuarioPostgresSqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void crear(TareaUsuarioEntity tareaUsuario) {
        final var sql = "INSERT INTO TareaUsuario(id, tarea, categoria) VALUES (?,?, ?));";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, tareaUsuario.getId().toString());
            statement.setString(2, tareaUsuario.getTarea().getId().toString());
            statement.setString(3, tareaUsuario.getCategoria().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(final TareaUsuarioEntity entidad) {
        final var sql = "UPDATE TareaUsuario SET tarea = ?, categoria = ? WHERE uuid = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, entidad.getTarea().toString());
            statement.setString(2, entidad.getCategoria().toString());
            statement.setString(3, entidad.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }

    @Override
    public List<TareaUsuarioEntity> consultar(TareaUsuarioEntity filtro) {
        List<TareaUsuarioEntity> tareaUsuarios = new ArrayList<>();
        var sql = new StringBuilder("SELECT tu.id id, tarea, categoria_tarea,ct.nombre_categoria nombrecategoria,descripcion, t.usuario idUser, t.nombre_tarea nombreTarea, t.descripcion_tarea descripcionTarea,et.id idestado, et.nombre_estado, t.prioridad prioridadTarea, t.fecha_limite fechaLimite, t.fecha_creacion fechaCreacion, t.ultima_actualizacion ultimaActualizacion FROM tarea_usuario tu JOIN tarea t on t.id = tu.tarea JOIN estado_tarea et on et.id = t.estado JOIN categoria_tarea ct on ct.id = tu.categoria_tarea ");

                List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getId() != null) {
                conditions.add("tu.id = ?");
                parameters.add(filtro.getId().toString());
            }
            if (filtro.getTarea().getUsuario().toString().isEmpty()) {
                conditions.add("u.usuario = ?");
                parameters.add(filtro.getTarea().getUsuario().toString());
            }
            if(filtro.getCategoria() !=null){
                conditions.add("tu.categoria = ?");
                parameters.add(filtro.getCategoria().getNombreCategoria());
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
                    UUID tareaid = UUID.fromString(resultSet.getString("tarea"));
                    UUID usuarioId = UUID.fromString(resultSet.getString("iduser"));
                    UUID categoriatareaid = UUID.fromString(resultSet.getString("categoria_tarea"));
                    String descripcioncategoria = resultSet.getString("descripcion");
                    String nombrecategoria = resultSet.getString("nombrecategoria");
                    String nombretarea = resultSet.getString("nombretarea");
                    String descripciontarea = resultSet.getString("descripciontarea");
                    int idestado = Integer.parseInt(resultSet.getString("idEstado"));
                    String nombreestado = resultSet.getString("nombre_estado");
                    String prioridadtarea = resultSet.getString("prioridadtarea");
                    LocalDateTime fechaLimite = LocalDateTime.parse(resultSet.getString("fechalimite"), DateHelper.FORMATTER);
                    LocalDateTime fechaCreacion = LocalDateTime.parse(resultSet.getString("fechacreacion"), DateHelper.FORMATTER);
                    LocalDateTime ultimaActualizacion = LocalDateTime.parse(resultSet.getString("ultimaactualizacion"), DateHelper.FORMATTER);
                    TareaUsuarioEntity tareaUsuario = TareaUsuarioEntity.build(uuid,TareaEntity.build(
                            tareaid,UsuarioEntity.build(usuarioId,"","","",""),
                            nombretarea,descripciontarea,EstadoTareaEntity.build(idestado, nombreestado),
                            Prioridad.valueOf(prioridadtarea),fechaLimite,fechaCreacion,ultimaActualizacion),
                            CategoriaTareaEntity.build(categoriatareaid,null,nombrecategoria,descripcioncategoria));
                    tareaUsuarios.add(tareaUsuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareaUsuarios;

    }

    @Override
    public void eliminar(final UUID id) {
        final var sql = "DELETE FROM TareaUsuario WHERE uuid =?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Proper error handling
        }
    }
}
