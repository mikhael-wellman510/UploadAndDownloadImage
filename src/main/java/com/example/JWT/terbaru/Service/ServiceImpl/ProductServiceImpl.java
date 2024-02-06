package com.example.JWT.terbaru.Service.ServiceImpl;

import com.example.JWT.terbaru.Dto.Response.File.ImageResponse;
import com.example.JWT.terbaru.Dto.Response.ProdukResponse;
import com.example.JWT.terbaru.Entity.File.Produk;
import com.example.JWT.terbaru.Repositori.ProductRepositori;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    private final Path fileStorageLocation = Paths.get("/home/user/Documents/New JWT 2023/JWT terbaru (1)/JWT-terbaru/src/main/resources/static");
    private final ProductRepositori productRepositori;

        public List<ProdukResponse> getAllProducts () throws FileNotFoundException, MalformedURLException {

            List<Produk> getAll = productRepositori.findAll();
          // Todo -> Set Image Agar bisa Online
           List<ProdukResponse> result = new ArrayList<>();


            for (Produk i : getAll){
                System.out.println("crot : " + i.getImageData().getImageName());

                try {
                    Path targetLocation = this.fileStorageLocation.resolve(i.getImageData().getImageName()).normalize();
                 Resource resource = new UrlResource(targetLocation.toUri());

                 // Todo -> Agar bisa Online , harus di taruh di static

                   ProdukResponse pr  =  ProdukResponse.builder()
                            .id(i.getId())
                            .namaProduk(i.getNamaProduk())
                            .hargaProduk(i.getHargaProduk())
                            .descriptionProduk(i.getDescriptionProduk())
                            .stokProduk(i.getStokProduk())
                            .image(resource.getFilename())
                            .build();

                    result.add(pr);
                }catch (MalformedURLException e){
                    throw new FileNotFoundException("File not found ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ;

            }

            System.out.println("prr::" + result);

            return result;
        };
}
