package com.tyler.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/all")
    public @ResponseBody List<Category> all() {
        List<Category> categories = new ArrayList<Category>();
        categoryRepository.findAll().forEach((category) -> {
            categories.add(category);
        });
        return categories;
    }
}