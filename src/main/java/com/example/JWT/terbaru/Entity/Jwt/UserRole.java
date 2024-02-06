package com.example.JWT.terbaru.Entity.Jwt;

import com.example.JWT.terbaru.Constant.Erole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_users_role")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private Erole role;

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                ", role=" + role +
                '}';
    }
}
