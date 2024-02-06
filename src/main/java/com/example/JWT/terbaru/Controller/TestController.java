package com.example.JWT.terbaru.Controller;

import com.example.JWT.terbaru.Constant.AppPath;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class TestController {


    @GetMapping("/ping")
    @PreAuthorize("hasRole('ADMIN')")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/ping2")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String test2() {
        try {
            return "Welcome tEST 2";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
