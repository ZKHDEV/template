package com.zkh.core.dao;

import com.zkh.core.bean.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role,String> {
}
