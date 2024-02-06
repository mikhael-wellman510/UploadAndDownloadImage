package com.example.JWT.terbaru.Dto.Request;

import com.example.JWT.terbaru.Constant.Erole;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Erole role;
}
