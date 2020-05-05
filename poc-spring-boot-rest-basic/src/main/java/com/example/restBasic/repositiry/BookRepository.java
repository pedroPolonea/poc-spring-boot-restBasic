package com.example.restBasic.repositiry;

import com.example.restBasic.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author s2it_psilva
 * @version $Revision: $<br/>
 * $Id: $
 * @since 11/14/18 23:21 PM
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
