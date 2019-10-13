package com.juan.nestedsubreport.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.nestedsubreport.dao.AuthorRepo;
import com.juan.nestedsubreport.dao.BookRepo;
import com.juan.nestedsubreport.dao.PageRepo;
import com.juan.nestedsubreport.model.Author;
import com.juan.nestedsubreport.model.Book;
import com.juan.nestedsubreport.model.Page;

@Service
public class BookService {

	private static Logger logger = LoggerFactory.getLogger(BookService.class);

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private PageRepo pageRepo;

	@Autowired
	private AuthorRepo authorRepo;

	public Book findBookByIdWithPages(Long id) {
		logger.debug("findBookByIdWithPages id=[{}]", id);

		List<Page> listPage = new ArrayList<Page>();
		Iterable<Page> it = pageRepo.findByBookEntityIdOrderById(id);
		it.forEach(page -> listPage.add(page));
		logger.debug("findBookByIdWithPages it=[{}]", it);

		Book bookEntity = bookRepo.findOne(id);
		bookEntity.setPageEntities(listPage);
		logger.debug("findBookByIdWithPages bookEntity=[{}]", bookEntity);

		return bookEntity;
	}

	public List<Book> findAll() {
		logger.debug("findAll");

		// get book
		List<Book> listBook = new ArrayList<Book>();
		Iterable<Book> it = bookRepo.findByOrderByName();
		it.forEach(instance -> listBook.add(instance));

		// find and set pages for every book
		for (Book book : listBook) {
			List<Page> listPage = new ArrayList<Page>();
			Iterable<Page> it2 = pageRepo.findByBookEntityIdOrderById(book.getId());
			it2.forEach(page -> listPage.add(page));
		}

		logger.debug("findAll listBook=[{}]", listBook);
		return listBook;
	}

	public Book save(Book bookEntity) {
		logger.debug("save bookEntity=[{}]", bookEntity);
		// saving author
		Author authorSaved = authorRepo.save(bookEntity.getAuthorEntity());
		// setting author to book
		bookEntity.setAuthorEntity(authorSaved);
		// saving book;
		Book bookSaved = bookRepo.save(bookEntity);
		// setting book to columns
		bookEntity.getPageEntities().forEach(instance -> instance.setBookEntity(bookEntity));
		// saving columns
		bookEntity.getPageEntities().forEach(instance -> pageRepo.save(instance));
		logger.debug("save bookSaved=[{}]", bookSaved);
		return bookSaved;
	}

	public void delete(Long id) {
		// delete pages
		pageRepo.deleteByBookEntityId(id);
		// delete book
		bookRepo.delete(id);
	}

}
