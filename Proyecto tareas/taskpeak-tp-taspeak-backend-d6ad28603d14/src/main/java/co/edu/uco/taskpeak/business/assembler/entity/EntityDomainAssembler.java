package co.edu.uco.taskpeak.business.assembler.entity;

import java.util.List;

public interface EntityDomainAssembler <D,E> {
    D ensamblarDominio (E entity);

    E ensamblarEntidad (D dominio);

    List<D> ensamblarListaDominios(List<E> listaEntidades);
}
