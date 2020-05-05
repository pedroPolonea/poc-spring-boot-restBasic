package com.example.restBasic.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.*;

/**
 * @author s2it_psilva
 * @version $Revision: $<br/>
 * $Id: $
 * @since 11/14/18 23:21 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorDTO {

    private Long id;
    @NotEmpty(message = "O campo nome é obrigatório!")
    private String name;
    private List<BookDTO> books;
}
