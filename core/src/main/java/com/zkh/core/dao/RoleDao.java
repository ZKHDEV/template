package com.zkh.core.dao;

import com.zkh.core.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role,String> {
}
