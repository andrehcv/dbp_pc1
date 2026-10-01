package com.utec.pc1.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Account implements UserDetails {
    //UserDetails:
    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column (nullable = false)
    private String password;
    @Column (nullable = false)
    @Builder.Default
    private String role = "ROLE_ADMIN"; //ROLE_CUSTOMER Y ROLE_VENDOR TMB
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
}
return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
}

public String getPassword() { return password; }

@Override public boolean isAccountNonExpired() { return true; }
@Override public boolean isAccountNonLocked() { return true; }
@Override public boolean isCredentialsNonExpired() { return true; }
@Override public boolean isEnabled() { return true; }

