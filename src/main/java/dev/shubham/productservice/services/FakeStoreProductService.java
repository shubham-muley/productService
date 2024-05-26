package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.FakeStoreProductDto;
import dev.shubham.productservice.models.Category;
import dev.shubham.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProductDetails(Long id) {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);

        Product productDetails = new Product();
        productDetails.setId(fakeStoreProductDto.getId());
        productDetails.setTitle(fakeStoreProductDto.getTitle());
        productDetails.setDescription(fakeStoreProductDto.getDescription());
        productDetails.setPrice(fakeStoreProductDto.getPrice());
        productDetails.setImage(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        productDetails.setCategory(category);

        return productDetails;
    }

    @Override
    public void getAllProducts() {

    }
}
