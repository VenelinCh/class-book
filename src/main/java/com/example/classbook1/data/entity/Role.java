package com.example.classbook1.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
public class Role extends BaseEntity implements GrantedAuthority {
    private String authority;

    public void setAuthority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @ManyToMany
    private Set<User> users;
}
