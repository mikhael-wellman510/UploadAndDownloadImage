package com.example.JWT.terbaru.Service.Jwt;

import com.example.JWT.terbaru.Constant.Erole;
import com.example.JWT.terbaru.Dto.Request.LoginRequest;
import com.example.JWT.terbaru.Dto.Request.RegisterRequest;
import com.example.JWT.terbaru.Dto.Response.LoginResponse;
import com.example.JWT.terbaru.Dto.Response.RegisterResponse;
import com.example.JWT.terbaru.Entity.Jwt.UserInfo;
import com.example.JWT.terbaru.Entity.Jwt.UserRole;
import com.example.JWT.terbaru.Repositori.UserRepositori;
import com.example.JWT.terbaru.Repositori.UserRoleRepositori;
import com.example.JWT.terbaru.Service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepositori userRoleRepositori;
    private final UserRepositori userRepositori;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        UserRole userRole = userRoleRepositori.findByRole(Erole.ROLE_CUSTOMER)
                .orElseGet(() -> {
                    // Jika tidak ada, buat instance baru
                 UserRole userRole1 =  UserRole.builder()
                            .role(registerRequest.getRole())
                            .build();

                    return userRoleRepositori.saveAndFlush(userRole1);
                });
        UserInfo userInfo = UserInfo.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(userRole)
                .build();

        UserInfo userInfo1 = userRepositori.saveAndFlush(userInfo);
        System.out.println("Hasil User Info : " + userInfo1);
        return RegisterResponse.builder()
                .username(userInfo1.getUsername())
                .password(userInfo1.getPassword())
                .email(userInfo1.getEmail())
                .role(userInfo1.getRole().getRole())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("hasil auth : " + authentication);
        String accessTokenn = jwtService.GenerateToken(loginRequest.getUsername());
        String claims = jwtService.extractUsername(accessTokenn);
        if(authentication.isAuthenticated()){
            return LoginResponse.builder()
                    .accessToken(accessTokenn)
                    .name(claims).
                    build();


        } else {

            throw new UsernameNotFoundException("invalid user request..!!");
        }


    }
}
