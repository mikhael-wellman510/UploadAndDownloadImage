package com.example.JWT.terbaru.Dto.Response;

import com.example.JWT.terbaru.Constant.Erole;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterResponse {
    private String username;
    private String password;
    private String email;
    private Erole role;

}
