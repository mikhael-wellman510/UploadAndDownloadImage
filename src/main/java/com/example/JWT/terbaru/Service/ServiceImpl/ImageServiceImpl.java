package com.example.JWT.terbaru.Service.ServiceImpl;

import com.example.JWT.terbaru.Dto.Request.File.ImageAndProdukRequest;
import com.example.JWT.terbaru.Dto.Response.File.ImageAndProductResponse;
import com.example.JWT.terbaru.Entity.File.ImageData;
import com.example.JWT.terbaru.Entity.File.Produk;
import com.example.JWT.terbaru.Repositori.ImageRepositori;
import com.example.JWT.terbaru.Repositori.ProductRepositori;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl {

    private final Path fileStorageLocation = Paths.get("/home/user/Documents/New JWT 2023/JWT terbaru (1)/JWT-terbaru/src/main/java/com/example/JWT/terbaru/Files");
    private final   ProductRepositori productRepositori;
    private final ImageRepositori imageRepositori;

    public ImageAndProductResponse uploadImageAndProduct(ImageAndProdukRequest imageAndProdukRequest){


        // Todo -> Save Image

        String mimiType = imageAndProdukRequest.getImage().getContentType();

        if (mimiType == null || (!mimiType.startsWith("image/"))){
            throw  new RuntimeException("Invalid Upload, Only Image");
        }

        try {
            Path targetLocation = this.fileStorageLocation.resolve(imageAndProdukRequest.getImage().getOriginalFilename());
            Files.copy(imageAndProdukRequest.getImage().getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            ImageData imageData =ImageData.builder()
                    .imageName(imageAndProdukRequest.getImage().getOriginalFilename())
                    .tanggalUpload(LocalDateTime.now())
//                    .produk(produk)
                    .build();
            imageRepositori.saveAndFlush(imageData);

            System.out.println(imageAndProdukRequest);
        // Todo -> Save Product
        Produk produk = Produk.builder()
                .namaProduk(imageAndProdukRequest.getNamaProduk())
                .hargaProduk(imageAndProdukRequest.getHargaProduk())
                .stokProduk(imageAndProdukRequest.getStokProduk())
                .descriptionProduk(imageAndProdukRequest.getDescriptionProduk())
                .imageData(imageData)
                .build();

        productRepositori.saveAndFlush(produk);


        }catch (IOException e){
            throw  new RuntimeException("Could Not Store" + imageAndProdukRequest.getImage().getOriginalFilename() +  " Pleas Check Again" + e);
        }

        return ImageAndProductResponse.builder()
                .fileName(imageAndProdukRequest.getImage().getOriginalFilename())
                .localDateTime(LocalDateTime.now())
                .namaProduk(imageAndProdukRequest.getNamaProduk())
                .hargaProduk(imageAndProdukRequest.getHargaProduk())
                .stokProduk(imageAndProdukRequest.getStokProduk())
                .descriptionProduk(imageAndProdukRequest.getDescriptionProduk())
                .build();

    }

    public Resource getImageAndProduct(String name) throws FileNotFoundException, MalformedURLException {
        try {

            Path targetLocation = this.fileStorageLocation.resolve(name).normalize();
            Resource resource = new UrlResource(targetLocation.toUri());


            if (resource.exists()){
                return resource;
            }else {
                throw  new FileNotFoundException("file Not Found" + name);
            }

        }catch (MalformedURLException e){
            throw new FileNotFoundException("File not found " + name);
        }

    }

    public List<Resource> getAllImageAndProduct() throws FileNotFoundException, MalformedURLException {


        // Todo - > Ambil file name
        List<ImageData>img = imageRepositori.findAll();
        System.out.println("img"  + img);
        List<Resource> resources = new ArrayList<>();

        for (ImageData i : img){
            Path targetLocation = fileStorageLocation.resolve(i.getImageName()).normalize();
            Resource resource = new UrlResource(targetLocation.toUri());
            System.out.println("loop : " + i.getImageName());
//            System.out.println("resource : " + resource.getFilename());
            resources.add(resource);
        }


        return resources;


    }






}
