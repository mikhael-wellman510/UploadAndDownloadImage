package com.example.JWT.terbaru.Repositori;

import com.example.JWT.terbaru.Entity.Jwt.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositori extends JpaRepository<UserInfo ,String> {
   UserInfo findByUsername(String username);
}
