package com.zkh.core.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(indexes = { @Index(name = "user_name_unique", columnList = "username", unique = true) })
public class User extends BaseEntity{
    private String username;
    private String nick;
    @JsonIgnore
    private String salt;
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(name="user_role",
            joinColumns={ @JoinColumn(name="user_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")})
    private List<Role> roles;
}
