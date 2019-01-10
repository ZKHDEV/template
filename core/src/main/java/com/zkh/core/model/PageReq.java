package com.zkh.core.model;

import com.zkh.core.utils.StringUtil;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//分页请求类
@Data
public class PageReq {
    private int page = 1;
    private int pagesize = 10;
    private String sortfield = "";
    private String sort = "";
    private String keyword = "";

    public Pageable toPageable() {
        Pageable pageable = null;

        if(StringUtil.isEmpty(sortfield)) {
            pageable = new PageRequest(page - 1, pagesize);
        } else {
            pageable = new PageRequest(page - 1, pagesize,
                    sort.toLowerCase().startsWith("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                    sortfield);
        }
        return pageable;
    }
}
