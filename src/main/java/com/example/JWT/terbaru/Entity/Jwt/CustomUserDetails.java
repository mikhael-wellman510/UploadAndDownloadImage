package com.example.JWT.terbaru.Entity.Jwt;

import com.example.JWT.terbaru.Constant.Erole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Todo -> Authentikasi dan Otoritas
public class CustomUserDetails extends UserInfo implements UserDetails {

    private String username;
    private String password;
    private Erole role;
    public  Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserInfo byUsername) {
        this.username = byUsername.getUsername();
        this.password= byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();



        UserRole role = byUsername.getRole();
        if (role != null) {
            auths.add(new SimpleGrantedAuthority(role.getRole().name()));
        }


        this.authorities = auths;
    }

    // Todo -> Jangan Lupa di Buat True
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
