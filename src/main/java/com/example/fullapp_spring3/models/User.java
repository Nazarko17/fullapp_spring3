package com.example.fullapp_spring3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String email;
    private String phoneNumber;
    private String avatar;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExamResult> examResults = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    private boolean isAccountNonExpired;
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    private boolean isAccountNonLocked;
    @Override
    public boolean isAccountNonLocked() { return this.isAccountNonLocked; }

    private boolean isCredentialsNonExpired;
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    private boolean isEnabled;
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
