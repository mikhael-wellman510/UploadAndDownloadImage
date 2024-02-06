package com.example.JWT.terbaru.Controller.File;


import com.example.JWT.terbaru.Constant.AppPath;
import com.example.JWT.terbaru.Dto.Request.File.ImageAndProdukRequest;
import com.example.JWT.terbaru.Dto.Response.CommonResponse.CommonResponse;
import com.example.JWT.terbaru.Dto.Response.File.ImageAndProductResponse;
import com.example.JWT.terbaru.Service.ServiceImpl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.UPLOAD)
public class ImageController {

    private final ImageServiceImpl imageService ;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file , @RequestParam String namaProduk ,@RequestParam Integer hargaProduk , @RequestParam Integer stokProduk , @RequestParam String descriptionProduk){
        ImageAndProdukRequest build = ImageAndProdukRequest.builder()
                .image(file)
                .namaProduk(namaProduk)
                .hargaProduk(hargaProduk)
                .stokProduk(stokProduk)
                .descriptionProduk((descriptionProduk))
                .build();
        ImageAndProductResponse imageAndProductResponse = imageService.uploadImageAndProduct(build);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ImageAndProductResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Sukses Upload Product")
                        .data(imageAndProductResponse)
                        .build()
                );
    }

    @GetMapping(value = "/getImageAndProduk/{file}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String file)  {
     Resource resources;
        try {
            resources = imageService.getImageAndProduct(file);
        }catch (FileNotFoundException | MalformedURLException e){
            return ResponseEntity.notFound().build();
        }

        System.out.println(resources);
     return ResponseEntity.ok()
             .contentType(MediaType.parseMediaType("application/octet-stream"))
             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resources.getFilename() + "\"")
             .body(resources)
             ;
    }











    @GetMapping(value = "/getAllImageAndProduk", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getAllImagesAndProducts() throws MalformedURLException, FileNotFoundException {
        List<Resource> resources;
        try {
            resources = imageService.getAllImageAndProduct();
        } catch (FileNotFoundException | MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
                for (Resource resource : resources) {
                    addToZip(resource, zipOut);
                }
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "images.zip");

            ByteArrayResource byteArrayResource = new ByteArrayResource(baos.toByteArray());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(byteArrayResource);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    private void addToZip(Resource resource, ZipOutputStream zipOut) throws IOException {
        byte[] content = new byte[(int) resource.contentLength()];
        resource.getInputStream().read(content);

        ZipEntry zipEntry = new ZipEntry(resource.getFilename());
        zipOut.putNextEntry(zipEntry);
        zipOut.write(content);
        zipOut.closeEntry();
    }
}
