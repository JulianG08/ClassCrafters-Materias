package co.edu.uco.taskpeak.business.assembler.dto;

import co.edu.uco.taskpeak.business.domain.UsuarioDomain;
import co.edu.uco.taskpeak.dto.UsuarioDTO;

import java.util.List;

public interface DTODomainAssembler <D, T> {

    D ensamblarDominio(T dto);

    T ensamblarDTO(D dominio);

    List<T> ensamblarListaDTO(List<D> listaDominios);

    List<UsuarioDomain> ensamblarListaDominios(List<UsuarioDTO> listaDtos);

}
