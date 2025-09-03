package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repo.CategoryRepo;
import guru.springframework.repo.RecipeRepo;
import guru.springframework.repo.UnitOfMeasureRepo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepo categoryRepository;
    private final RecipeRepo recipeRepository;
    private final UnitOfMeasureRepo unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepo categoryRepository,
                           RecipeRepo recipeRepository,
                           UnitOfMeasureRepo unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        // ===== Get Units of Measure =====
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found");

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found");

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found");

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found");

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found");

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupsUomOptional.isPresent()) throw new RuntimeException("Expected UOM Not Found");

        // Get actual UOMs
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        // ===== Get Categories =====
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) throw new RuntimeException("Expected Category Not Found");

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) throw new RuntimeException("Expected Category Not Found");

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // ===== Example Recipe (Guac + Tacos) =====
        // (Keep your guacamole + taco recipe building code here; unchanged)
        // ...

        return recipes;
    }
}
