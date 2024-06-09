package co.edu.uco.tutorspace.data.dao.entity.concrete.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.tutorspace.crosscutting.exceptions.customs.DataTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;
import co.edu.uco.tutorspace.data.dao.entity.MateriaDAO;
import co.edu.uco.tutorspace.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.tutorspace.entity.MateriaEntity;

public class MateriaPostgreSqlDAO extends SqlConnection implements MateriaDAO {

	public MateriaPostgreSqlDAO(final Connection conexion){
        super(conexion);
    }

    @Override
    public final void crear(MateriaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO Materia (id, nombre, institucion)");
        sentenciaSql.append("VALUES (?,?,?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setString(2, data.getNombre());
            sentenciaSqlPreparada.setString(3, data.getInstitucion());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031), data.getNombre());
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032, data.getNombre()));
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033), data.getNombre());
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00034), data.getNombre());
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }
    }

    @Override
    public List<MateriaEntity> consultar (MateriaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT m.id, m.nombre, m.institucion")
                .append("FROM Materia m")
                .append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND p.id = ?");
            parametros.add(data.getId());
        }
        
        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND m.nombre = ?");
            parametros.add(data.getNombre());
        }
        if (!TextHelper.isNullOrEmpty(data.getInstitucion())) {
            sentenciaSql.append(" AND m.institucion = ?");
            parametros.add(data.getInstitucion());
        }

        final List<MateriaEntity> materias = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    MateriaEntity materia = new MateriaEntity().build()
                        .setId(UUID.fromString(resultado.getString("id")))
                        .setNombre(resultado.getString("nombre"))
                        .setInstitucion(resultado.getString("institucion"));

                    materias.add(materia);
                }
            }
            
        } catch (final SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036), data.getNombre());

            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00037);
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038), data.getNombre());

            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, exception);
        }

        return materias;
    }

    @Override
    public void actualizar(MateriaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE Materia ");
        sentenciaSql.append("SET nombre = ?, institucion = ? ");
        sentenciaSql.append("WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setString(1, data.getNombre());
            sentenciaSqlPreparada.setString(2, data.getInstitucion());
            sentenciaSqlPreparada.setObject(3, data.getId());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00039), data.getNombre());
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00040, data.getNombre()));
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00041), data.getNombre());
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00042), data.getNombre());
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }
    }

    @Override
    public void eliminar(MateriaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM Materia ");
        sentenciaSql.append("WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00043), data.getNombre());
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00044, data.getNombre()));
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045), data.getNombre());
            var mensajeTecnico = TextHelper.reemplazarParametro(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046), data.getNombre());
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }
    }
}
