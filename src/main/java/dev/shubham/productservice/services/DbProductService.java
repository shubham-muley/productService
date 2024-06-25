package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductDto;
import dev.shubham.productservice.models.Category;
import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.repositories.CategoryRepository;
import dev.shubham.productservice.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DbProductService")
public class DbProductService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public DbProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProductDetails(Long id) {
        return productRepository.findByIdIs(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(CreateProductDto createProductDto) {
        Product product = new Product();
        product.setTitle(createProductDto.getTitle());
        product.setDescription(createProductDto.getDescription());
        product.setPrice(createProductDto.getPrice());
        product.setImage(createProductDto.getImage());

        Category category = categoryRepository.findByTitle(createProductDto.getCategory());
        if (category != null) {
            product.setCategory(category);
        }else{
            Category newCategory = new Category();
            newCategory.setTitle(createProductDto.getCategory());
            product.setCategory(newCategory);
        }

        return productRepository.save(product);
    }

    @Override
    public Product getProductByTitleAndCategory(String title, String categoryTitle) {
        return productRepository.findByTitleAndCategory(title, categoryTitle);
    }

    @Override
    public Product getProductByTitle(String title) {
        return productRepository.findByTitle(title);
    }

}
