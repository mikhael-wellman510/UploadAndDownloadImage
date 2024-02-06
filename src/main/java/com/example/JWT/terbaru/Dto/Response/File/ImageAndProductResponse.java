package com.example.JWT.terbaru.Dto.Response.File;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ImageAndProductResponse {

    private String fileName;
    private LocalDateTime localDateTime;
    private String namaProduk;
    private Integer hargaProduk;
    private Integer stokProduk ;
    private String descriptionProduk;


}
