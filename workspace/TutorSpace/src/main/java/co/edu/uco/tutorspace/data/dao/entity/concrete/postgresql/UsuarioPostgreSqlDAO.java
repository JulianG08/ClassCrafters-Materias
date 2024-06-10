package co.edu.uco.tutorspace.data.dao.entity.concrete.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.tutorspace.crosscutting.exceptions.customs.DataTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;
import co.edu.uco.tutorspace.data.dao.entity.UsuarioDAO;
import co.edu.uco.tutorspace.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.tutorspace.entity.UsuarioEntity;

public class UsuarioPostgreSqlDAO extends SqlConnection implements UsuarioDAO {

    public UsuarioPostgreSqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(final UsuarioEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO Usuario (id, nombre, apellido, tipo_documento, numero_documento, tipo_usuario, correo_electronico, clave, telefono, direccion, institucion) ");
        sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setString(2, data.getNombre());
            sentenciaSqlPreparada.setString(3, data.getApellido());
            sentenciaSqlPreparada.setString(4, data.getTipoDocumento());
            sentenciaSqlPreparada.setLong(5, data.getNumeroDocumento());
            sentenciaSqlPreparada.setString(6, data.getTipoUsuario());
            sentenciaSqlPreparada.setString(7, data.getCorreoElectronico());
            sentenciaSqlPreparada.setString(8, data.getClave());
            sentenciaSqlPreparada.setString(9, data.getTelefono());
            sentenciaSqlPreparada.setString(10, data.getDireccion());
            sentenciaSqlPreparada.setString(11, data.getInstitucion());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de crear el usuario.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de insertar el usuario en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de crear el usuario.";
            var mensajeTecnico = "Se ha presentado una excepción inesperada tratando de insertar el usuario en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }
    }

    @Override
    public List<UsuarioEntity> consultar(final UsuarioEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT u.id, u.nombre, u.apellido, u.tipo_documento, u.numero_documento, u.tipo_usuario, u.correo_electronico, u.clave, u.telefono, u.direccion, u.institucion ");
        sentenciaSql.append("FROM Usuario u ");
        sentenciaSql.append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND u.id = ?");
            parametros.add(data.getId());
        }

        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND u.nombre = ?");
            parametros.add(data.getNombre());
        }

        if (!TextHelper.isNullOrEmpty(data.getApellido())) {
            sentenciaSql.append(" AND u.apellido = ?");
            parametros.add(data.getApellido());
        }

        if (!TextHelper.isNullOrEmpty(data.getTipoDocumento())) {
            sentenciaSql.append(" AND u.tipo_documento = ?");
            parametros.add(data.getTipoDocumento());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getNumeroDocumento()) && data.getNumeroDocumento() != 0L) {
            sentenciaSql.append(" AND u.numero_documento = ?");
            parametros.add(data.getNumeroDocumento());
        }

        if (!TextHelper.isNullOrEmpty(data.getTipoUsuario())) {
            sentenciaSql.append(" AND u.tipo_usuario = ?");
            parametros.add(data.getTipoUsuario());
        }

        if (!TextHelper.isNullOrEmpty(data.getCorreoElectronico())) {
            sentenciaSql.append(" AND u.correo_electronico = ?");
            parametros.add(data.getCorreoElectronico());
        }

        final List<UsuarioEntity> usuarios = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    UsuarioEntity usuario = UsuarioEntity.build()
                        .setId(UUID.fromString(resultado.getString("id")))
                        .setNombre(resultado.getString("nombre"))
                        .setApellido(resultado.getString("apellido"))
                        .setTipoDocumento(resultado.getString("tipo_documento"))
                        .setNumeroDocumento(resultado.getLong("numero_documento"))
                        .setTipoUsuario(resultado.getString("tipo_usuario"))
                        .setCorreoElectronico(resultado.getString("correo_electronico"))
                        .setClave(resultado.getString("clave"))
                        .setTelefono(resultado.getString("telefono"))
                        .setDireccion(resultado.getString("direccion"))
                        .setInstitucion(resultado.getString("institucion"));

                    usuarios.add(usuario);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar los usuarios.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de consultar los usuarios en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar los usuarios.";
            var mensajeTecnico = "Se ha presentado una excepción inesperada tratando de consultar los usuarios en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }

        return usuarios;
    }

    @Override
    public void actualizar(final UsuarioEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE Usuario ");
        sentenciaSql.append("SET nombre = ?, apellido = ?, tipo_documento = ?, numero_documento = ?, tipo_usuario = ?, correo_electronico = ?, clave = ?, telefono = ?, direccion = ?, institucion = ? ");
        sentenciaSql.append("WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setString(1, data.getNombre());
            sentenciaSqlPreparada.setString(2, data.getApellido());
            sentenciaSqlPreparada.setString(3, data.getTipoDocumento());
            sentenciaSqlPreparada.setLong(4, data.getNumeroDocumento());
            sentenciaSqlPreparada.setString(5, data.getTipoUsuario());
            sentenciaSqlPreparada.setString(6, data.getCorreoElectronico());
            sentenciaSqlPreparada.setString(7, data.getClave());
            sentenciaSqlPreparada.setString(8, data.getTelefono());
            sentenciaSqlPreparada.setString(9, data.getDireccion());
            sentenciaSqlPreparada.setString(10, data.getInstitucion());
            sentenciaSqlPreparada.setObject(11, data.getId());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de actualizar el usuario.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de actualizar el usuario en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de actualizar el usuario.";
            var mensajeTecnico = "Se ha presentado una excepción inesperada tratando de actualizar el usuario en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }
    }

    @Override
    public void eliminar(final UsuarioEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM Usuario ");
        sentenciaSql.append("WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            sentenciaSqlPreparada.setObject(1, data.getId());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar el usuario.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de eliminar el usuario en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar el usuario.";
            var mensajeTecnico = "Se ha presentado una excepción inesperada tratando de eliminar el usuario en la tabla Usuario.";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }
}
}