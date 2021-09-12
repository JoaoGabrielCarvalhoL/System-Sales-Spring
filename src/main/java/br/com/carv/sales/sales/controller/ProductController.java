package br.com.carv.sales.sales.controller;

import br.com.carv.sales.sales.entities.Client;
import br.com.carv.sales.sales.entities.Product;
import br.com.carv.sales.sales.repositories.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findProductById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        Product productToSave = productRepository.save(product);
        return ResponseEntity.ok(productToSave);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteProduct/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.noContent().build();

        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable @Valid Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.save(product.get());
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllProducts")
    public List<Product> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findProduct")
    public List<Product> findProduct(@RequestBody Product product) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> productResult = new ArrayList<Product>();

        if (!allProducts.isEmpty()) {
            for (Product product1 : allProducts) {
                if (product1.getDescriptionProduct().contains(product.getDescriptionProduct())) {
                    productResult.add((product1));
                }
            }

            return productResult;
        }

        return (List<Product>) ResponseEntity.notFound().build();
    }

}
