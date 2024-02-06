package com.example.JWT.terbaru.Dto.Request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginRequest {
    private String username;
    private String password;
}
