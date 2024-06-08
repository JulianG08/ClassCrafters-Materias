package co.edu.uco.tutorspace.business.assembler;

public interface Assembler<DTO, Domain, Entity> {
    DTO toDTO(Domain domain);
    Domain toDomain(DTO dto);
    Entity toEntity(Domain domain);
    Domain fromEntity(Entity entity);
}
