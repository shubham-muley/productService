package dev.shubham.productservice.dtos;

import dev.shubham.productservice.models.Category;
import dev.shubham.productservice.models.Product;
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

    public Product toProduct(){
        Product productDetails = new Product();
        productDetails.setId(getId());
        productDetails.setTitle(getTitle());
        productDetails.setDescription(getDescription());
        productDetails.setPrice(getPrice());
        productDetails.setImage(getImage());
        Category category = new Category();
        category.setTitle(getCategory());
        productDetails.setCategory(category);
        return productDetails;
    }
}
