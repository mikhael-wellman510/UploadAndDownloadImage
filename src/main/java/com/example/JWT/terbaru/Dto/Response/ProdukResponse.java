package com.example.JWT.terbaru.Dto.Response;

import com.example.JWT.terbaru.Dto.Response.File.ImageResponse;
import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.URI;
import java.net.URL;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProdukResponse {
  private   String id ;
   private String namaProduk;
   private Integer hargaProduk;
   private Integer stokProduk;
   private String descriptionProduk;
   private String image;

//    @Override
//    public String toString() {
//        return "ProdukResponse{" +
//                "id='" + id + '\'' +
//                ", namaProduk='" + namaProduk + '\'' +
//                ", hargaProduk=" + hargaProduk +
//                ", stokProduk=" + stokProduk +
//                ", descriptionProduk='" + descriptionProduk + '\'' +
//                ", image=" + image +
//                '}';
//    }
}
