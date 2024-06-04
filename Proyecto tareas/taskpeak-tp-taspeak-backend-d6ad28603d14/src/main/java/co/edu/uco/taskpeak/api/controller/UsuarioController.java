package co.edu.uco.taskpeak.api.controller;

import co.edu.uco.taskpeak.api.response.UsuarioResponse;
import co.edu.uco.taskpeak.business.fachade.concrete.ConsultarUsuariosFachaImpl;
import co.edu.uco.taskpeak.crosscutting.exceptions.TaskPeakException;
import co.edu.uco.taskpeak.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<UsuarioResponse> consultar(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String correo) {

        UsuarioResponse usuarioResponse = UsuarioResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            UUID uuid = null;
            if (id != null && !id.isEmpty()) {
                try {
                    uuid = UUID.fromString(id);
                } catch (IllegalArgumentException e) {
                    usuarioResponse.getMensajes().add("El formato del ID es inválido");
                    return new ResponseEntity<>(usuarioResponse, HttpStatus.BAD_REQUEST);
                }
            }

            final var usuarioDtoFilter = UsuarioDTO.build(uuid, null, null, correo, null);
            final ConsultarUsuariosFachaImpl fachada = new ConsultarUsuariosFachaImpl();

            usuarioResponse.setData(fachada.execute(usuarioDtoFilter));
            usuarioResponse.getMensajes().add("Usuarios consultados exitosamente");
        } catch (final TaskPeakException exception) {
            exception.printStackTrace();
            usuarioResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception) {
            exception.printStackTrace();
            usuarioResponse.getMensajes().add("Se ha presentado un problema inesperado");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(usuarioResponse, httpStatusResponse);
    }
}
