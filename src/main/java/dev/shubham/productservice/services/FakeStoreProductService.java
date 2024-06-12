package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductDto;
import dev.shubham.productservice.dtos.FakeStoreProductDto;
import dev.shubham.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Override
    public ResponseEntity<Product> getSingleProductDetails(Long id) {

        FakeStoreProductDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

//        assert response != null;
        return new ResponseEntity<>(response.toProduct(), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<Product[]> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class
        );

        Product[] products = new Product[response.length];
        int i=0;
        for(FakeStoreProductDto product : response){
            products[i++] = product.toProduct();
        }

        //Sending ResponseEntity for returning status code and body
//        ResponseEntity<Product[]> responseEntity = new ResponseEntity<>(
//                products,
//                HttpStatus.OK
//        );
        return new ResponseEntity<>(
                products,
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Product> createProduct(CreateProductDto createProductDto){
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
        return new ResponseEntity<>(response.toProduct(), HttpStatus.CREATED);
    }
}
