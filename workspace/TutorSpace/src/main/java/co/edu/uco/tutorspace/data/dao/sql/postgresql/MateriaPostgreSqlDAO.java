package co.edu.uco.tutorspace.data.dao.sql.postgresql;

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
import co.edu.uco.tutorspace.data.dao.entity.MateriaDAO;
import co.edu.uco.tutorspace.data.dao.sql.SqlConnection;
import co.edu.uco.tutorspace.entity.MateriaEntity;

public class MateriaPostgreSqlDAO extends SqlConnection implements MateriaDAO {

	public MateriaPostgreSqlDAO(final Connection conexion) {
		super(conexion);
	}
	
	@Override
	public final void crear(final MateriaEntity data) {
		final StringBuilder sentenciaSQL = new StringBuilder();
		
		sentenciaSQL.append("INSERT INTO Materia(id,nombre,institucion)");
		sentenciaSQL.append("SELECT ?,?,?");
	
		
		try (final PreparedStatement sentenciaSQLPreparada = getConexion().prepareStatement(sentenciaSQL.toString())){
			
			sentenciaSQLPreparada.setObject(1,data.getId() );
			sentenciaSQLPreparada.setString(2, data.getNombre());
			sentenciaSQLPreparada.setObject(3, data.getInstitucion());
			
			sentenciaSQLPreparada.executeUpdate();
			
		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de crear la materia \"${1}\", por favor intente de nuevo y si el problema persiste contacte al administrador...";
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQLexception tatando de realizar el insert de la materia \"${1}\" en la tabla Materia" + "de la base de datos postgreSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario,excepcion);
			
		} catch (final Exception excepcion) {
			var mensajeUsuario = "se ha presentado un problema tratando de crear la materia \"${1}\" y si el problemas contacte a el administrador ...";
			var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el insert de la materia \"${1}\" en la tabla materia" + "de la base de datos postgreSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario,excepcion);
		}
		
	}

	@Override
	public List<MateriaEntity> consultar(final MateriaEntity data) {
	    final StringBuilder sentenciaSql = new StringBuilder();
	    sentenciaSql.append("SELECT m.id, m.nombre, m.institucion");
	    sentenciaSql.append(" FROM Materia m");
	    sentenciaSql.append(" WHERE 1=1");

	    final List<Object> parametros = new ArrayList<>();

	    if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
	        sentenciaSql.append(" AND m.id = ?");
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
	                MateriaEntity materia = MateriaEntity.build();
	                materia.setId(UUID.fromString(resultado.getString("id")));
	                materia.setNombre(resultado.getString("nombre"));
	                materia.setInstitucion(resultado.getString("institucion"));
	                
	                materias.add(materia);
	            }
	        }

	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las materias. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de las materias en la tabla \"Materia\" de la base de datos postgreSQL.";
	        throw new DataTutorSpaceException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las materias. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta de las materias en la tabla \"Materia\" de la base de datos postgreSQL.";
	        throw new DataTutorSpaceException(mensajeUsuario, mensajeTecnico, excepcion);
	    }

	    return materias;
	}
	
	@Override
	public void actualizar(MateriaEntity data) {
		final StringBuilder sentenciaSQL = new StringBuilder();
		
		sentenciaSQL.append("UPDATE Materia SET nombre= ?,institucion= ? WHERE id= ?");

		try (final PreparedStatement sentenciaSQLPreparada = getConexion().prepareStatement(sentenciaSQL.toString())){
			sentenciaSQLPreparada.setObject(3,data.getId() );
			sentenciaSQLPreparada.setString(1, data.getNombre());
			sentenciaSQLPreparada.setString(2, data.getInstitucion());
			
			sentenciaSQLPreparada.executeUpdate();
			
		}catch (final SQLException excepcion) {
			var mensajeUsuario = "se ha presentado un prblema tratando de modificar la materia \\\"${1}\\\" y si el problemas contacte a el administrador ...";
			var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el update de la materia \"${1}\" en la tabla Materia" + "de la base de datos postgreSql para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario,excepcion);
			
		}catch (final Exception excepcion) {
			var mensajeUsuario = "se ha presentado un problema tratando de modificar la materia \"${1}\" y si el problemas contacte a el administrador ...";
			var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el update de la materia \"${1}\" en la tabla Materia" + "de la base de datos postgreSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
			throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario,excepcion);
		
		}
	}
	
	@Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("DELETE FROM Materia WHERE id = ?");

        try (final PreparedStatement sentenciaSQLPreparada = getConexion().prepareStatement(sentenciaSQL.toString())){
            sentenciaSQLPreparada.setObject(1, id);

            sentenciaSQLPreparada.executeUpdate();
        } catch(final SQLException excepcion) {
            var mensajeUsuario = "se ha presentado un prblemao tratando de eliminar la materia \"${1}\" y si el problemas contacte a el administrador ...";
            var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el delete de la materia \\\"${1}\\\" en la tabla Materia\"\r\n" + "+ \"de la base de datos postgreSql.para mas detalles revise de forma completa la excepcionRaiz presentada";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario);

        } catch(final Exception excepcion) {
            var mensajeUsuario = "\"se ha presentado un prblema tratando de eliminar la materia \\\"${1}\\\" y si el problemas contacte a el administrador ...\"";
            var mensajeTecnico = "Se ha presentado una excepcion se tipo SQLexception tatando de realizar el delete de la materia \"${1}\" en la tabla Materia" + "de la base de datos postgreSql.para mas detalles revise de forma completa la excepcionRaiz presentada ";
            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }
}
