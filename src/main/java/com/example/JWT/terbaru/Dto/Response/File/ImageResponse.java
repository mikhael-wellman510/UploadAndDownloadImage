package com.example.JWT.terbaru.Dto.Response.File;

import lombok.*;
import org.springframework.core.io.Resource;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ImageResponse {
    private Resource image;
}
