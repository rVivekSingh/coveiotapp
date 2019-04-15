package com.coveiot.coveiotapp.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.coveiot.coveiotapp.model.Login;

@RepositoryRestResource(collectionResourceRel="userLogin", path="userLogin")
public interface LoginRepository extends PagingAndSortingRepository<Login, Long>{
public Login findByUserId(@Param("userId") String userId);
}
