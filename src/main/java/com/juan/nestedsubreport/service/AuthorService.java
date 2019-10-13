package com.juan.nestedsubreport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.nestedsubreport.dao.AuthorRepo;
import com.juan.nestedsubreport.model.Author;

@Service
public class AuthorService {

	private static Logger logger = LoggerFactory.getLogger(AuthorService.class);

	@Autowired
	private AuthorRepo authorRepo;

	public List<Author> findAllAuthors() {
		logger.debug("findAllAuthors");
		List<Author> list = new ArrayList<Author>();
		Iterable<Author> it = authorRepo.findByOrderByNameAsc();
		it.forEach(instance -> list.add(instance));
		logger.debug("findAllAuthors it=[{}]", it);
		return list;
	}

	public Author findOne(long id) {
		logger.debug("findOne id=[{}]", id);
		Author author = authorRepo.findOne(id);
		logger.debug("findOne authorEntity=[{}]", author);
		return author;
	}

	public Author save(Author authorEntity) {
		logger.debug("save authorEntity=[{}]", authorEntity);
		Author authorSaved = authorRepo.save(authorEntity);
		logger.debug("save authorSaved=[{}]", authorSaved);
		return authorSaved;
	}

	public void delete(Long id) {
		authorRepo.delete(id);
	}

	public Author findByCode(String code) {
		logger.debug("findByCode code=[{}]", code);
		Optional<Author> authorOptional = authorRepo.findByCode(code);
		logger.debug("findByCode authorEntity=[{}]", authorOptional.get());
		return authorOptional.get();
	}

}
