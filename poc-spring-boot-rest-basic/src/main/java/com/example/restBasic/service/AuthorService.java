package com.example.restBasic.service;


import java.util.List;
import java.util.Optional;

import com.example.restBasic.dto.AuthorDTO;
import com.example.restBasic.entity.AuthorEntity;
import com.example.restBasic.exceptions.ResourceNotFoundException;
import com.example.restBasic.mapper.AuthorMapper;
import com.example.restBasic.repositiry.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author s2it_psilva
 * @version $Revision: $<br/>
 * $Id: $
 * @since 11/14/18 23:21 PM
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        final List<AuthorEntity> authors = authorRepository.findAll();
        return authorMapper.authorsEntityToAuthorDTOs(authors);
    }

    public Page<AuthorDTO> getAll(Pageable pageable) {
        final Page<AuthorEntity> authorsPage = authorRepository.findAll(pageable);
        Page<AuthorDTO> authorDTOPage = new PageImpl<AuthorDTO>(
                authorMapper.authorsEntityToAuthorDTOs(authorsPage.getContent()),
                authorsPage.getPageable(),
                authorsPage.getTotalElements()
        );

        return authorDTOPage;
    }

    public AuthorDTO getById(Long id){
        final AuthorEntity author = getAuthorEntity(id);

        return authorMapper.authorEntityToAuthorDTO(author);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED)
    public AuthorDTO create(AuthorDTO authorDTO){
        AuthorEntity authorEntity = authorMapper.authorDTOToAuthorEntity(authorDTO);

        final AuthorEntity authorSave = authorRepository.save(authorEntity);
        return authorMapper.authorEntityToAuthorDTO(authorSave);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED)
    public AuthorDTO update(AuthorDTO authorDTO){
        getById(authorDTO.getId());
        AuthorEntity authorEntity = authorMapper.authorDTOToAuthorEntity(authorDTO);

        final AuthorEntity authorSave = authorRepository.save(authorEntity);
        return authorMapper.authorEntityToAuthorDTO(authorSave);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED)
    public void delete(Long id){
        final AuthorEntity authorEntity = getAuthorEntity(id);
        authorRepository.delete(authorEntity);
    }

    private AuthorEntity getAuthorEntity(Long id) {
        final Optional<AuthorEntity> author = authorRepository.findById(id);
        author.orElseThrow(() -> new ResourceNotFoundException("O autor id " + id + " não foi encontrado."));
        return author.get();
    }
}
