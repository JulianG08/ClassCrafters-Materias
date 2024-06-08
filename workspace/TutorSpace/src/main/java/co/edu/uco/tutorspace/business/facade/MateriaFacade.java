package co.edu.uco.tutorspace.business.facade;

import co.edu.uco.tutorspace.dto.MateriaDTO;
import java.util.List;

public interface MateriaFacade {
    void crear(MateriaDTO materia);
    List<MateriaDTO> consultar(MateriaDTO filtro);
    void actualizar(MateriaDTO materia);
    void eliminar(int id);
}
