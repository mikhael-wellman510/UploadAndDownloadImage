package com.example.JWT.terbaru.Dto.Response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse {
    private String accessToken;
    private String name;
}
