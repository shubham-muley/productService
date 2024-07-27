package dev.shubham.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "product")
@SQLDelete(sql = "UPDATE product SET is_deleted = true where id=?")
@Where(clause = "is_deleted=false")
public class Product extends BaseModel{
    private String title;
    private double price;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;
    private String description;
    private String image;
}
