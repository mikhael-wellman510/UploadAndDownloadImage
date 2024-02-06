package com.example.JWT.terbaru.Controller;

import com.example.JWT.terbaru.Constant.AppPath;
import com.example.JWT.terbaru.Dto.Request.LoginRequest;
import com.example.JWT.terbaru.Dto.Request.RegisterRequest;
import com.example.JWT.terbaru.Dto.Response.CommonResponse.CommonResponse;
import com.example.JWT.terbaru.Dto.Response.LoginResponse;
import com.example.JWT.terbaru.Dto.Response.RegisterResponse;
import com.example.JWT.terbaru.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {

    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:5175")
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authService.login(loginRequest);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully crated new Account")
                        .data(loginResponse)
                        .build()) ;
    }
    @CrossOrigin(origins = "http://localhost:5175")
    @PostMapping(value = "/createRegisters")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest registerRequest){
        System.out.println(registerRequest  );
        RegisterResponse registerResponse = authService.register(registerRequest);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully crated new Account")
                        .data(registerResponse)
                        .build()) ;
    }


}
