package ru.globux.tacos.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.globux.tacos.Ingredient;
import ru.globux.tacos.Taco;
import ru.globux.tacos.TacoOrder;
import ru.globux.tacos.Ingredient.Type;
import ru.globux.tacos.User;
import ru.globux.tacos.data.IngredientRepository;
import ru.globux.tacos.data.TacoRepository;
import ru.globux.tacos.data.UserRepository;

import java.security.Principal;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepo;
    private final UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo,
                                TacoRepository tacoRepo,
                                UserRepository userRepo) {
        this.ingredientRepository = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        String username = principal.getName();
        return userRepo.findByUsername(username);
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder order) {
        log.info("   --- Сохраняем taco");
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = tacoRepo.save(taco);
        order.addTaco(saved);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(),false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
