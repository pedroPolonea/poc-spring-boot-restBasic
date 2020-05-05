package com.example.restBasic.mapper;

import com.example.restBasic.dto.BookDTO;
import com.example.restBasic.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookEntityToBookDTO(BookEntity book);

    BookEntity bookDTOToBookEntity(BookDTO bookDTO);
}
