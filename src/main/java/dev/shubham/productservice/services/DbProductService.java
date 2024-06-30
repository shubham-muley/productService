package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductRequestDto;
import dev.shubham.productservice.models.Category;
import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.repositories.CategoryRepository;
import dev.shubham.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Product createProduct(CreateProductRequestDto createProductRequestDto) {
        Product product = new Product();
        product.setTitle(createProductRequestDto.getTitle());
        product.setDescription(createProductRequestDto.getDescription());
        product.setPrice(createProductRequestDto.getPrice());
        product.setImage(createProductRequestDto.getImage());

        Optional<Category> category = categoryRepository.findByTitle(createProductRequestDto.getCategory());
        if (category.isPresent()) {
            product.setCategory(category.get());
        }else{
            Category newCategory = new Category();
            newCategory.setTitle(createProductRequestDto.getCategory());
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
    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
