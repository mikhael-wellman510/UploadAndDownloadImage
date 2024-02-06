package com.example.JWT.terbaru.Service;

import com.example.JWT.terbaru.Dto.Request.LoginRequest;
import com.example.JWT.terbaru.Dto.Request.RegisterRequest;
import com.example.JWT.terbaru.Dto.Response.LoginResponse;
import com.example.JWT.terbaru.Dto.Response.RegisterResponse;

public interface AuthService {
    RegisterResponse register (RegisterRequest registerRequest);
    LoginResponse login (LoginRequest loginRequest);
}
