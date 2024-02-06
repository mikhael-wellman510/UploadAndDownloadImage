package com.example.JWT.terbaru.Entity.Jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "m_users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;

    private String password;

    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  UserRole role;

    //Todo -> Ada kemungkinan set nya salah

}
