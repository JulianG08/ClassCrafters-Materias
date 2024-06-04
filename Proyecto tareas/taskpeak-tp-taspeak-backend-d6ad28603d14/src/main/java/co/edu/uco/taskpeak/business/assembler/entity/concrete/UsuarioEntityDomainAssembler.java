package co.edu.uco.taskpeak.business.assembler.entity.concrete;

import co.edu.uco.taskpeak.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.taskpeak.business.domain.UsuarioDomain;
import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioEntityDomainAssembler implements EntityDomainAssembler<UsuarioDomain, UsuarioEntity> {
    private static final EntityDomainAssembler<UsuarioDomain, UsuarioEntity> instancia = new UsuarioEntityDomainAssembler();
    public static final EntityDomainAssembler<UsuarioDomain, UsuarioEntity> obtenerInstancia() {
        return instancia;
    }
    @Override
    public final UsuarioDomain ensamblarDominio(final UsuarioEntity entity) {
        var usuarioEntityTemp = ObjectHelper.getObjectHelper().getDefault(entity, UsuarioEntity.build(UUID.randomUUID()));
        return UsuarioDomain.crear(usuarioEntityTemp.getId(),usuarioEntityTemp.getNombre(), usuarioEntityTemp.getApellido(), usuarioEntityTemp.getCorreo(),usuarioEntityTemp.getPassowrd());
    }

    @Override
    public final UsuarioEntity ensamblarEntidad(final UsuarioDomain dominio) {
        var usuarioDomainTemp = ObjectHelper.getObjectHelper().getDefault(dominio, UsuarioDomain.crear());
        return UsuarioEntity.build(usuarioDomainTemp.getId(),usuarioDomainTemp.getNombre(), usuarioDomainTemp.getApellido(), usuarioDomainTemp.getCorreo(),usuarioDomainTemp.getPassword());
    }

    @Override
    public List<UsuarioDomain> ensamblarListaDominios(List<UsuarioEntity> listaEntidades) {
        var listaEntidadesTmp = ObjectHelper.getObjectHelper()
                .getDefault(listaEntidades, new ArrayList<UsuarioEntity>());
        var resultados = new ArrayList<UsuarioDomain>();

        for (UsuarioEntity usuarioEntity : listaEntidadesTmp) {
            var usuarioDomainTmp = ensamblarDominio(usuarioEntity);
            resultados.add(usuarioDomainTmp);
        }
        return resultados;
    }
}
