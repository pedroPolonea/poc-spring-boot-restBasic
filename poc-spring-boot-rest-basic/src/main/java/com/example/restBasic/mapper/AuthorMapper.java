package com.example.restBasic.mapper;

import com.example.restBasic.dto.AuthorDTO;
import com.example.restBasic.entity.AuthorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO authorEntityToAuthorDTO(AuthorEntity author);

    @InheritInverseConfiguration
    AuthorEntity authorDTOToAuthorEntity(AuthorDTO authorDTO);

    List<AuthorDTO> authorsEntityToAuthorDTOs(List<AuthorEntity> authorsEntity);
}
