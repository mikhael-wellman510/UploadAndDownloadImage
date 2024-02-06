package com.example.JWT.terbaru.Entity.File;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.List;

@Entity
@Table(name = "m_product")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Produk {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column(name = "nama_produk")
    private String namaProduk;

    @Column(name = "harga_produk")
    private Integer hargaProduk;

    @Column(name = "stok_produk")
    private Integer stokProduk;

    @Column(name = "description_produk")
    private String descriptionProduk;

    @ManyToOne
    @JoinColumn(name = "image_data_id")
    private ImageData imageData;

    @Override
    public String toString() {
        return "Produk{" +
                "id='" + id + '\'' +
                ", namaProduk='" + namaProduk + '\'' +
                ", hargaProduk=" + hargaProduk +
                ", stokProduk=" + stokProduk +
                ", descriptionProduk='" + descriptionProduk + '\'' +
                ", imageData=" + imageData +
                '}';
    }
}
