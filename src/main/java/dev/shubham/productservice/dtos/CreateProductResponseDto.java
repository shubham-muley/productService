package dev.shubham.productservice.dtos;

import dev.shubham.productservice.models.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateProductResponseDto {
        private Long id;
        private String title;
        private double price;
        private Category category;
        private String description;
        private String image;
}
