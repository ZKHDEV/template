package com.zkh.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Test2 implements Serializable {
    private String username;
    private String password;
}
