package com.zkh.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Test {
    @NotNull
    public Integer id;

    public String username;
    @JsonIgnore
    public String password;
}
