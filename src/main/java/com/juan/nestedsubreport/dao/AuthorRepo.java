package com.juan.nestedsubreport.dao;

import java.util.Optional;

import com.juan.nestedsubreport.model.Author;

public interface AuthorRepo extends BaseRepo<Author, Long> {

	Iterable<Author> findByOrderByNameAsc();

	Optional<Author> findByCode(String code);

}
