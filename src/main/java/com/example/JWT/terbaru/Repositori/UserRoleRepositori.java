package com.example.JWT.terbaru.Repositori;

import com.example.JWT.terbaru.Constant.Erole;
import com.example.JWT.terbaru.Entity.Jwt.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepositori extends JpaRepository<UserRole , String> {
    Optional<UserRole> findByRole(Erole role);
}
