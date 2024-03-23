package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.ProductRepository;
import co.istad.springwebmvc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        // check
        if (productRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product name already existing ! "
            );
        }

        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setImportedDate(LocalDateTime.now());
        product.setStatus(true);
        productRepository.save(product);

    }



    public List<ProductResponse> findProducts() {
        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).toList();
    }




    @Override
    public ProductResponse findProductById(Integer id) {
        Product product =  productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product has not been found!"
        ));

        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(), product.getQty());

    }



    @Override
    public ProductResponse editProductByUuid(String uuid, ProductEditRequest request) {
        if(!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        Product product = productRepository.findProductByUuid(uuid);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        productRepository.save(product);
        return new ProductResponse(product.getUuid(),product.getName(),product.getPrice(), product.getQty());
    }



    @Override
    public void deleteByUuid(String uuid) {
        log.info("product uuid {}",uuid);
        if(!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found!"
            );
        }
        Product product = this.productRepository.findProductByUuid(uuid);
        productRepository.deleteById(product.getId());
    }



}
