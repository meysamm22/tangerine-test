package com.tangerine.tangerinetest;

import com.tangerine.tangerinetest.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("springresttest")
public class ProductController {

    @Autowired
    private ProductDao productDao;
    @GetMapping("/prid/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id){
        return new ResponseEntity<>(productDao.findById(id).orElseThrow(NotFoundException::new), HttpStatus.OK);

    }

    @PutMapping("/prid/{id}")
    public ResponseEntity<Product> edit(@PathVariable Integer id, @RequestParam String name){
        Product product = productDao.findById(id).orElseThrow(NotFoundException::new);
        product.setPrdname(name);
        productDao.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<Product> insert(@RequestParam String name){
        productDao.save(new Product(name));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/prids")
    public ResponseEntity<Iterable<Product>> list(){
        return new ResponseEntity<>(productDao.findAll(), HttpStatus.OK);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
