package com.juan.nestedsubreport.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<T, ID extends Serializable> extends CrudRepository<T, ID> {

}
