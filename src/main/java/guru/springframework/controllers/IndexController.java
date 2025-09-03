package guru.springframework.controllers;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import guru.springframework.repo.CategoryRepo;
import guru.springframework.repo.UnitOfMeasureRepo;
import guru.springframework.domain.Category;

import java.util.Optional;
@Controller
public class IndexController {

    private final CategoryRepo categoryRepository;
    private final UnitOfMeasureRepo unitOfMeasureRepository;

    public IndexController(CategoryRepo categoryRepository, UnitOfMeasureRepo unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category Id: " + categoryOptional.get().getId());
        System.out.println("UOM Id: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}