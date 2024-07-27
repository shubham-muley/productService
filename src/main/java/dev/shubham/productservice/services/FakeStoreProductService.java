package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductRequestDto;
import dev.shubham.productservice.dtos.CreateProductResponseDto;
import dev.shubham.productservice.dtos.FakeStoreProductDto;
import dev.shubham.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Override
    public Product getSingleProductDetails(Long id) {

        FakeStoreProductDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class
        );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto product : response){
            products.add(product.toProduct());
        }
        return products;
    }

    @Override
    public CreateProductResponseDto createProduct(String title, double price,
                                                  String categoryTitle, String description,
                                                  String image){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(categoryTitle);
        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );

//        assert response != null;

        return null;
    }

    @Override
    public Product getProductByTitleAndCategory(String title, String categoryTitle) {
        return null;
    }

    @Override
    public Product getProductByTitle(String title) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
