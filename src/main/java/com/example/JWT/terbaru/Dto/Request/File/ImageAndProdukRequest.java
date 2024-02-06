package com.example.JWT.terbaru.Dto.Request.File;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ImageAndProdukRequest {

    private MultipartFile image;
    private String namaProduk;
    private Integer hargaProduk;
    private Integer stokProduk;
    private String descriptionProduk;

    @Override
    public String toString() {
        return "ImageAndProdukRequest{" +
                "image=" + image +
                ", namaProduk='" + namaProduk + '\'' +
                ", hargaProduk=" + hargaProduk +
                ", stokProduk=" + stokProduk +
                ", descriptionProduk='" + descriptionProduk + '\'' +
                '}';
    }
}
