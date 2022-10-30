package com.tangerine.tangerinetest;

import com.tangerine.tangerinetest.dao.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController @RequestMapping("springresttest")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/prid/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id){
        logger.info("Product#" + id + " is about to fetch ...");
        return new ResponseEntity<>(productDao.findById(id).orElseThrow(NotFoundException::new), HttpStatus.OK);

    }

    @PutMapping("/prid/{id}")
    public ResponseEntity<Product> edit(@PathVariable Integer id, @RequestParam String name){
        Product product = productDao.findById(id).orElseThrow(NotFoundException::new);
        product.setPrdname(name);
        productDao.save(product);
        logger.info("Product#" + id + " updated.");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<Product> insert(@RequestParam String name){
        productDao.save(new Product(name));
        logger.info("Product " + name + " saved.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/prids")
    public ResponseEntity<Iterable<Product>> list(){
        logger.info("List of products is about to prepared...");
        return new ResponseEntity<>(productDao.findAll(), HttpStatus.OK);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(){
        logger.error("The Requested product is not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
