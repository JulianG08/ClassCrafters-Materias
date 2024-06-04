package co.edu.uco.taskpeak.data.dao.factory;

import co.edu.uco.taskpeak.data.dao.TareaDAO;
import co.edu.uco.taskpeak.data.dao.UsuarioDAO;
import co.edu.uco.taskpeak.data.dao.factory.sql.postgressql.PostgresSqlDAOFactory;
import co.edu.uco.taskpeak.data.dao.sql.postgressql.*;
import co.edu.uco.taskpeak.entity.*;

import java.util.List;
import java.util.UUID;

public abstract class DAOFactory {
    public static final DAOFactory getFactory() {

        return new PostgresSqlDAOFactory();
//		switch (factory) {
//
//			case AZURE_SQL: {
//				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
//				var mensajeTecnico = "No existe una factoria de base de datos configurada para POSTGRES_SQL";
//
//				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
//			}
//			case POSTGRES_SQL: {
//				return  new PostgresSqlDAOFactory();
//			}
//			case SQL_SERVER: {
//				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
//				var mensajeTecnico = "No existe una factoria de base de datos configurada para SQL_SERVER";
//
//				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
//			}
//			case MYSQL: {
//				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
//				var mensajeTecnico = "No existe una factoria de base de datos configurada para MYSQL";
//
//				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
//			}
//			case ORACLE: {
//				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
//				var mensajeTecnico = "No existe una factoria de base de datos configurada para ORACLE";
//
//				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
//			}
//			default:
//				return new PostgresSqlDAOFactory();
//		}
    }

    protected abstract void obtenerConexion();

    public abstract void iniciarTransaccion();

    public abstract void confirmarTransaccion();

    public abstract void cancelarTransaccion();

    public abstract void cerrarConexion();

    public abstract UsuarioDAO getUsuarioDAO();

    public abstract TareaDAO getTareaDAO();

    public abstract CategoriaTareaPostgresSqlDAO getCategoriaTareaDAO();

    public abstract HistorialTareaPostgresSqlDAO getHistorialTareaDAO();
    public abstract EstadoTareaPostgresSqlDAO getEstadoTareaDAO();
    public abstract TareaUsuarioPostgresSqlDAO getTareaUsuarioDAO();

    public abstract RecordatorioPostgresSqlDAO getRecordatorioDAO();

    public static void main(String[] args) {
        //UsuarioEntity nuevoUsuario = UsuarioEntity.build(UUID.randomUUID(), "brahian1", "sanabria1", "tes1t@gmail.com", "1234");
        //DAOFactory.getFactory().getUsuarioDAO().crear(nuevoUsuario);
//		List<UsuarioEntity> resultados = DAOFactory.getFactory().getUsuarioDAO().consultar(UsuarioEntity.build(null,null,null,"test",null));
//		for (UsuarioEntity usuario : resultados) {
//			System.out.println(usuario.getNombre());
//			System.out.println(usuario.getApellido());
//			System.out.println(usuario.getCorreo());
//		}

//        List<TareaEntity> resultadost = DAOFactory.getFactory().getTareaDAO().consultar(TareaEntity.build(null, UsuarioEntity.build(UUID.fromString("b2ef6b6f-87aa-441b-b465-6b5c5b9cb064")), null, null, null, null, null, null, null));
//        for (TareaEntity tarea : resultadost) {
//            System.out.println("ID: " + tarea.getId());
//            System.out.println("Nombre Tarea: " + tarea.getNombreTarea());
//            System.out.println("Descripcion Tarea: " + tarea.getDescripcionTarea());
//            System.out.println("Estado: " + tarea.getEstado());
//            System.out.println("Prioridad: " + tarea.getPrioridad());
//            System.out.println("Fecha Limite: " + tarea.getFechaLimite());
//            System.out.println("Fecha Creacion: " + tarea.getFechaCreacion());
//            System.out.println("Ultima Actualizacion: " + tarea.getUltimaActualizacion());
//            System.out.println("=====USUAIRO========");
//            System.out.println("Usuario: " + tarea.getUsuario().getId());
//            System.out.println("nombre: " + tarea.getUsuario().getNombre());
//            System.out.println("apellido: " + tarea.getUsuario().getApellido());
//            System.out.println("correo: " + tarea.getUsuario().getCorreo());
//        }
        //CategoriaTareaEntity newCategora=CategoriaTareaEntity.build(UUID.randomUUID(),UsuarioEntity.build(UUID.fromString("b2ef6b6f-87aa-441b-b465-6b5c5b9cb064"),"BRAHIAN","NOSE","NOSE","123"),"UNIVERSIDAD","");
        //DAOFactory.getFactory().getCategoriaTareaDAO().crear(newCategora);

//        List<CategoriaTareaEntity> resultados = DAOFactory.getFactory().getCategoriaTareaDAO().consultar(CategoriaTareaEntity.build(null,UsuarioEntity.build(UUID.fromString("dccfd5fa-2393-46ed-ae1f-5a399b637523")),"HoGar",""));
//		for (CategoriaTareaEntity categoriaTarea : resultados) {
//			System.out.println(categoriaTarea.getNombreCategoria());
//			System.out.println(categoriaTarea.getUsuario().getNombre());
//		}
//        List<HistorialTareaEntity> resultados = DAOFactory.getFactory().getHistorialTareaDAO().consultar(HistorialTareaEntity.build(null,TareaEntity.build(UUID.fromString("79ef7efb-ea34-4780-8a8a-911571315114")),null,null,UsuarioEntity.build(UUID.fromString("b2ef6b6f-87aa-441b-b465-6b5c5b9cb064")),null));
//        for (HistorialTareaEntity historialTarea : resultados) {
//            System.out.println(historialTarea.getEditor().getCorreo());
//            System.out.println(historialTarea.getEstadoNuevo().getNombreEstado());
//            System.out.println(historialTarea.getEstadoAnterior().getNombreEstado());
//        }

//        List<EstadoTareaEntity> resultados= DAOFactory.getFactory().getEstadoTareaDAO().consultar(EstadoTareaEntity.build(0,"C"));
//        for (EstadoTareaEntity estadoTarea : resultados) {
//            System.out.println(estadoTarea.getId());
//            System.out.println(estadoTarea.getNombreEstado());
//        }

//        List<TareaUsuarioEntity> resultados = DAOFactory.getFactory().getTareaUsuarioDAO().consultar(TareaUsuarioEntity.build(null, TareaEntity.build(UUID.fromString("79ef7efb-ea34-4780-8a8a-911571315114")),null ));
//        for (TareaUsuarioEntity tareaUsuario : resultados) {
//            System.out.println(tareaUsuario.getTarea().getNombreTarea());
//            System.out.println(tareaUsuario.getCategoria().getNombreCategoria());
//            System.out.println(tareaUsuario.getTarea().getUsuario().getNombre());
//       }

//        List<RecordatorioEntity> resultados = DAOFactory.getFactory().getRecordatorioDAO().consultar(RecordatorioEntity.build(null, TareaEntity.build(UUID.fromString("79ef7efb-ea34-4780-8a8a-911571315115")), null));
//        for (RecordatorioEntity recordatorio : resultados) {
//            System.out.println(recordatorio.getId());
//            System.out.println(recordatorio.getFechaRecordatorio());
//            System.out.println(recordatorio.getTarea().getNombreTarea());
//        }
    }
}
