package com.huitsing.server.dao;

import org.springframework.data.repository.CrudRepository;

import com.huitsing.server.entity.UserDetail;

public interface UserDetailDAO extends CrudRepository<UserDetail, Long>{

}
