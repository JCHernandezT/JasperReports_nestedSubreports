package com.juan.nestedsubreport.dao;

import com.juan.nestedsubreport.model.Book;

public interface BookRepo extends BaseRepo<Book, Long> {

	Iterable<Book> findByOrderByName();

}
