package com.example.JWT.terbaru.Controller;


import com.example.JWT.terbaru.Constant.AppPath;
import com.example.JWT.terbaru.Dto.Response.CommonResponse.CommonResponse;
import com.example.JWT.terbaru.Dto.Response.ProdukResponse;
import com.example.JWT.terbaru.Service.ServiceImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PRODUCT)
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping(value = "/getAllProducts" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProductWithFoto() throws FileNotFoundException, MalformedURLException {
        List<ProdukResponse> produkResponses = productService.getAllProducts();
        // Todo
        System.out.println("hasil : " + produkResponses);
        return ResponseEntity.status(HttpStatus.CREATED)

                .body(CommonResponse.<List<ProdukResponse>>builder()

                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succes Get Data")
                        .data(produkResponses)

                        .build()
                );
    }
}
