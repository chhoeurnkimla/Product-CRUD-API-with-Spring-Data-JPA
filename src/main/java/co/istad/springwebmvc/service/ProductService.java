package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createNewProduct(ProductCreateRequest request);

    List<ProductResponse> findProducts();

    ProductResponse findProductById(Integer id);

    ProductResponse editProductByUuid(String uuid, ProductEditRequest request);
    void deleteByUuid(String uuid);

}
