package com.example.JWT.terbaru.Repositori;

import com.example.JWT.terbaru.Entity.File.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepositori extends JpaRepository<ImageData ,String> {
//    Optional<ImageData> findByName(String name);

}
