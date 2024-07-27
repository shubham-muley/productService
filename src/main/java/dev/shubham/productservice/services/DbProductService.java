package dev.shubham.productservice.services;

import dev.shubham.productservice.dtos.CreateProductResponseDto;
import dev.shubham.productservice.exceptions.NoProductsException;
import dev.shubham.productservice.exceptions.ProductNotFoundException;
import dev.shubham.productservice.models.Category;
import dev.shubham.productservice.models.Product;
import dev.shubham.productservice.repositories.CategoryRepository;
import dev.shubham.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//TODO: After adding updateProduct code
//      replace RequestDTOs with simple attributes to make it more generic
//      so that any other service can also use it without any issue.

@Service("DbProductService")
public class DbProductService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public DbProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProductDetails(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findByIdIs(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() throws NoProductsException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new NoProductsException();
        }
        return products;
    }

    @Override
    public CreateProductResponseDto createProduct(String title, double price,
                                                  String categoryTitle, String description,
                                                  String image) {
        Product product = Product
                .builder()
                .title(title)
                .price(price)
                .description(description)
                .image(image)
                .build();

        Optional<Category> category = categoryRepository.findByTitle(categoryTitle);
        if (category.isPresent()) {
            product.setCategory(category.get());
        }else{
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            product.setCategory(newCategory);
        }

        product = productRepository.save(product);

        return CreateProductResponseDto
                .builder()
                .id(product.getId())
                .image(product.getImage())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    @Override
    public Product getProductByTitleAndCategory(String title, String categoryTitle) throws ProductNotFoundException {
        //TODO: Add exception to be thrown when more than 1 result is returned
        Optional<Product> product = productRepository.findByTitleAndCategory(title, categoryTitle);
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @Override
    public Product getProductByTitle(String title) throws ProductNotFoundException {
        //TODO: Add exception to be thrown when more than 1 result is returned
        Optional<Product> product = productRepository.findByTitle(title);
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @Override
    public void deleteProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);
    }

}
