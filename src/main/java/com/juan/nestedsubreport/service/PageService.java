package com.juan.nestedsubreport.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.nestedsubreport.dao.PageRepo;
import com.juan.nestedsubreport.model.Page;

@Service
public class PageService {

	private static Logger logger = LoggerFactory.getLogger(PageService.class);

	@Autowired
	private PageRepo pageRepo;

	public List<Page> findAll() {
		logger.debug("findAll");
		List<Page> listPage = new ArrayList<Page>();
		Iterable<Page> it = pageRepo.findAll();
		it.forEach(page -> listPage.add(page));
		logger.debug("findAll listPage=[{}]", listPage);
		return listPage;
	}

}
