package co.edu.uco.tutorspace.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.uco.tutorspace.controller.response.MateriaResponse;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.dto.MateriaDTO;

public class MateriaController {

	@GetMapping("/dummy")
    public MateriaDTO dummy(){
        return MateriaDTO.build();
    }

    @GetMapping("/p")
    public ResponseEntity<MateriaResponse> consultar(){
    	
        var httpStatusCode = HttpStatus.ACCEPTED;
        var materiaResponse = new MateriaResponse();

        try {

            var materiasDto = MateriaDTO.build();
            var facade = new ConsultarMateriasFacade();
            
            var materiasRetorno = new ArrayList<MateriaDTO>();
            materiasRetorno.add(MateriaDTO.build());
            materiasRetorno.add(MateriaDTO.build());
            materiasRetorno.add(MateriaDTO.build());
            materiasRetorno.add(MateriaDTO.build());
            materiasResponse.setDatos(facade.execute(materiasDto));
            materiaResponse.getMensajes().add("Materias consultadas exitosamente");
        } catch (final TutorSpaceException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            materiaResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        } catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = "Se ha presntado un problema tratando de consultar las materias";
            materiaResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();
        }

        return new ResponseEntity<>(materiaResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<MateriaResponse> crear(@RequestBody MateriaDTO materia){
        var httpStatusCode = HttpStatus.ACCEPTED;
        var materiaResponse = new MateriaResponse();

        try {

            var facade = new RegistrarMateriaFacade();

            facade.execute(materia);
            materiaResponse.getMensajes().add("Materia creada exitosamente");
        } catch (final TutorSpaceException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            
            materiaResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        } catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            
            var mensajeUsuario = "Se ha presntado un problema tratando de registrar la nueva materia";
            materiaResponse.getMensajes().add(mensajeUsuario);
            exception.printStackTrace();
        }

        return new ResponseEntity<>(materiaResponse, httpStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MateriaResponse> eliminar(@PathVariable UUID id){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var materiaResponse = new MateriaResponse();

        try {

            //var facade = new EliminarCiudadFacade();

            //facade.execute(id);
            materiaResponse.getMensajes().add("Materia eliminada exitosamente");
        } catch (final TutorSpaceException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            materiaResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        } catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = "Se ha presntado un problema tratando de eliminar la materia";
            materiaResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();
        }

        return new ResponseEntity<>(materiaResponse, httpStatusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaResponse> modificar(@PathVariable UUID id, @RequestBody MateriaDTO materiaDTO){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var materiaResponse = new MateriaResponse();

        try {
            materiaDTO.setId(id);
            //var facade = new ModificarCiudadFacade();

            //facade.execute(id);
            materiaResponse.getMensajes().add("Materia modificada exitosamente");
        } catch (final TutorSpaceException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            materiaResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        } catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = "Se ha presntado un problema tratando de modificar la materia";
            materiaResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();
        }

        return new ResponseEntity<>(materiaResponse, httpStatusCode);
    }
}
