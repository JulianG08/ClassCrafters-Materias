package co.edu.uco.taskpeak.data.dao.sql.postgressql;

import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Prioridad;
import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.data.dao.RecordatorioDAO;
import co.edu.uco.taskpeak.data.dao.sql.SqlConnection;
import co.edu.uco.taskpeak.entity.EstadoTareaEntity;
import co.edu.uco.taskpeak.entity.RecordatorioEntity;
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

public class RecordatorioPostgresSqlDAO extends SqlConnection implements RecordatorioDAO {
    @Override
    public void crear(final RecordatorioEntity recordatorio) {
        final var sql = "INSERT INTO recordatorio(id, tarea, fechaRecordatorio) VALUES (?,?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, recordatorio.getId().toString());
            statement.setString(2, recordatorio.getTarea().getId().toString());
            statement.setString(3, recordatorio.getFechaRecordatorio().toString());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public RecordatorioPostgresSqlDAO(final Connection connection) {super(connection);}

    @Override
    public void actualizar(final RecordatorioEntity recordatorio) {
        final var sql = "UPDATE recordatorio SET tarea =?, fechaRecordatorio =? WHERE id =?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, recordatorio.getTarea().getId().toString());
            statement.setString(2, recordatorio.getFechaRecordatorio().toString());
            statement.setString(3, recordatorio.getId().toString());
            statement.executeUpdate();
        }catch (SQLException e){
            // Proper error handling
        }
    }

    @Override
    public List<RecordatorioEntity> consultar(RecordatorioEntity filtro) {
        List<RecordatorioEntity> recordatorios = new ArrayList<>();
        var sql = new StringBuilder("SELECT r.id idrecordatorio, t.id idtarea,t.nombre_tarea nombretarea,t.descripcion_tarea descripciontarea, et.id idestadotarea, et.nombre_estado estadotarea,t.prioridad prioridadtarea, U.id idusuario, u.correo correousuario, r.fecharecordatorio fecharecordatorio from recordatorio r JOIN tarea t on t.id = r.tarea JOIN public.usuario u on u.id = t.usuario JOIN public.estado_tarea et on et.id = t.estado ");
        List<String> conditions = new ArrayList<>();
        if (filtro != null){
            if (filtro.getId()!= null){
                conditions.add("id = '" + filtro.getId().toString() + "'");
            }
            if (filtro.getTarea()!= null){
                conditions.add("tarea = '" + filtro.getTarea().getId().toString() + "'");
            }
            if (filtro.getFechaRecordatorio()!= null){
                conditions.add("fechaRecordatorio = '" + filtro.getFechaRecordatorio().toString() + "'");
            }
        }
        if (!conditions.isEmpty()){
            sql.append(" WHERE ");
            sql.append(String.join(" OR ", conditions));
        }
        try (PreparedStatement statement = getConnection().prepareStatement(sql.toString())){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                UUID idrecordatorio = UUID.fromString(resultSet.getString("idrecordatorio"));
                UUID idtarea = UUID.fromString(resultSet.getString("idtarea"));
                String nombretarea = resultSet.getString("nombretarea");
                String descripciontarea = resultSet.getString("descripciontarea");
                int idestado = Integer.parseInt(resultSet.getString("idestadotarea"));
                String estadotarea = resultSet.getString("estadotarea");
                String prioridadtarea = resultSet.getString("prioridadtarea");
                UUID idusuario = UUID.fromString(resultSet.getString("idusuario"));
                String correousuario = resultSet.getString("correousuario");
                LocalDateTime fecharecordatorio = LocalDateTime.parse(resultSet.getString("fecharecordatorio"), DateHelper.FORMATTER);
                RecordatorioEntity recordatorio = RecordatorioEntity.build(
                        idrecordatorio,
                        TareaEntity.build(idtarea, UsuarioEntity.build(idusuario,correousuario,"","",""),
                        nombretarea,
                        descripciontarea,
                        EstadoTareaEntity.build(idestado,estadotarea),
                        Prioridad.valueOf(prioridadtarea),null, null, null ),
                        fecharecordatorio
                );
                recordatorios.add(recordatorio);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return recordatorios;
    }

    @Override
    public void eliminar(final UUID id) {
        final var sql = "DELETE FROM Recordatorio WHERE uuid = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setString(1, id.toString());
            statement.executeUpdate();
        } catch (SQLException e){
            // Proper error handling
        }
    }
}
