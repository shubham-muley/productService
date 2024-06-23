package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductDto;
import dev.shubham.productservice.dtos.FakeStoreProductDto;
import dev.shubham.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public Product createProduct(CreateProductDto createProductDto){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(createProductDto.getTitle());
        fakeStoreProductDto.setDescription(createProductDto.getDescription());
        fakeStoreProductDto.setPrice(createProductDto.getPrice());
        fakeStoreProductDto.setImage(createProductDto.getImage());
        fakeStoreProductDto.setCategory(createProductDto.getCategory());
        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );

//        assert response != null;
        return response.toProduct();
    }
}
