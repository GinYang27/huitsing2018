package com.huitsing.server.dao;

import org.springframework.data.repository.CrudRepository;

import com.huitsing.server.entity.Hello;

public interface HelloDAO extends CrudRepository<Hello, Long>{
	Hello findById(Integer id);
}
