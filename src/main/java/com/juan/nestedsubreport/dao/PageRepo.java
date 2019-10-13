package com.juan.nestedsubreport.dao;

import com.juan.nestedsubreport.model.Page;

public interface PageRepo extends BaseRepo<Page, Long> {

	Iterable<Page> findByBookEntityIdOrderById(Long id);

	void deleteByBookEntityId(Long id);

}
