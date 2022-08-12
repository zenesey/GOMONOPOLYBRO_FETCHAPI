package com.alex.crudapponboot.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private long id;
    @Column(name = "role")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    public Role(long id, String role) {
        this.id = id;
        this.name = role;
    }
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> userList;

    public Role() {
    }

    @Override
    public String toString() {
        return  name;
    }
}