package dev.shubham.productservice.dtos;

import dev.shubham.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private String id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
