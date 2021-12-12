package com.ciclo4.reto2.service;

import com.ciclo4.reto2.model.Product;
import com.ciclo4.reto2.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProducts(String reference){
        return productRepository.getProduct(reference);
    }

    public Product save(Product cleaningProduct){
        if(cleaningProduct.getReference() == null) {
            return cleaningProduct;
        }else{
            return productRepository.create(cleaningProduct);
        }
    }

    public Product update(Product product) {

        if (product.getReference() != null) {
            Optional<Product> productAux = productRepository.getProduct(product.getReference());
            if (!productAux.isEmpty()) {
                if (product.getBrand() != null) {
                    productAux.get().setBrand(product.getBrand());
                }
                if (product.getCategory() != null) {
                    productAux.get().setCategory(product.getCategory());
                }
                if (product.getPresentation()!= null) {
                    productAux.get().setPresentation(product.getPresentation());
                }
                if (product.getDescription() != null) {
                    productAux.get().setDescription(product.getDescription());
                }
                if (product.getPrice() != 0.0) {
                    productAux.get().setPrice(product.getPrice());
                }
                if (product.getPrice() != 0.0) {
                    productAux.get().setPrice(product.getPrice());
                }
                if (product.getQuantity() != 0) {
                    productAux.get().setQuantity(product.getQuantity());
                }
                if (product.getPhotography() != null) {
                    productAux.get().setPhotography(product.getPhotography());
                }
                productAux.get().setAvailability(product.isAvailability());
                productRepository.update(productAux.get());
                return productAux.get();
            } else {
                return product;
            }
        }else{
            return product;
        }
    }

    public boolean delete(String reference){
        Boolean aboolean= getProducts(reference).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
        return aboolean;
    }
}

