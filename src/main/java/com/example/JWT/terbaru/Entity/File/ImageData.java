package com.example.JWT.terbaru.Entity.File;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "m_image_data")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "image_name")
    private String imageName ;

    @Column(name = "tangal_upload")
    private LocalDateTime tanggalUpload;


//    @OneToMany(mappedBy = "produks" , cascade = CascadeType.PERSIST)
//    private List<Produk> produks;


    @Override
    public String toString() {
        return "ImageData{" +
                "id='" + id + '\'' +
                ", imageName='" + imageName + '\'' +
                ", tanggalUpload=" + tanggalUpload +
                '}';
    }
}
