package com.kozlovskaya.spring.online.store.controllers;


import com.kozlovskaya.spring.online.store.converters.ProductConverter;
import com.kozlovskaya.spring.online.store.dto.ProductDto;
import com.kozlovskaya.spring.online.store.entities.Product;
import com.kozlovskaya.spring.online.store.exeptions.ResourceNotFoundException;
import com.kozlovskaya.spring.online.store.services.ProductService;
import com.kozlovskaya.spring.online.store.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;


    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer min,
            @RequestParam(name = "max_cost", required = false) Integer max,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if(page < 1) {
            page = 1;
        }

        return productService.findAll (min, max, titlePart, page).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.updateProduct(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    /*@GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeCost(productId, delta);
    }*/

    /*@GetMapping("/products/cost_between")
    public List<Product> findProductByCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(required = false)Integer max) {
        System.out.println(min + " " + max);
        return productService.findAllByCostBetween(min, max);
    }*/





}
