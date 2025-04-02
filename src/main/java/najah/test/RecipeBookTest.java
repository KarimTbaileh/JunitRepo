package najah.test;



import najah.code.Recipe;
import najah.code.RecipeBook;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class RecipeBookTest {

    private RecipeBook recipeBook;
    private Recipe recipe1, recipe2, recipe3, recipe4, recipe5;

    @BeforeEach
    void setup() {
        recipeBook = new RecipeBook();

        recipe1 = new Recipe();
        recipe1.setName("Espresso");

        recipe2 = new Recipe();
        recipe2.setName("Latte");

        recipe3 = new Recipe();
        recipe3.setName("Cappuccino");

        recipe4 = new Recipe();
        recipe4.setName("Mocha");

        recipe5 = new Recipe();
        recipe5.setName("Americano");
    }


    @Test
    @Order(1)
    @DisplayName("Test getRecipes")
    void testGetRecipes() {
        Recipe[] recipes = recipeBook.getRecipes();
        assertNotNull(recipes);
        assertEquals(4, recipes.length);
        for (Recipe recipe : recipes) {
            assertNull(recipe);
        }
    }


    @Test
    @Order(2)
    @DisplayName("Add a recipe")
    void testAddRecipe() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertEquals("Espresso", recipeBook.getRecipes()[0].getName());
    }


    @Test
    @Order(3)
    @DisplayName("Add duplicate recipe")
    void testAddDuplicateRecipe() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertFalse(recipeBook.addRecipe(recipe1));
    }


    @Test
    @Order(4)
    @DisplayName("Add to max recipes")
    void testAddMaxRecipes() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertTrue(recipeBook.addRecipe(recipe2));
        assertTrue(recipeBook.addRecipe(recipe3));
        assertTrue(recipeBook.addRecipe(recipe4));
        assertFalse(recipeBook.addRecipe(recipe5));
    }


    @Test
    @Order(5)
    @DisplayName("Test Delete an existing recipe")
    void testDeleteRecipe() {
        recipeBook.addRecipe(recipe1);
        String deleted = recipeBook.deleteRecipe(0);
        assertEquals("Espresso", deleted);
        assertNotNull(recipeBook.getRecipes()[0]);
    }


    @Test
    @Order(6)
    @DisplayName("Test Delete a non-existing recipe")
    void testDeleteNonExistingRecipe() {
        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    @Order(7)
    @DisplayName("Test Edit an existing recipe")
    void testEditRecipe() {
        recipeBook.addRecipe(recipe1);
        String oldName = recipeBook.editRecipe(0, recipe2);
        assertEquals("Espresso", oldName);
        assertEquals("", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @Order(8)
    @DisplayName("Test Edit a non-existing recipe")
    void testEditNonExistingRecipe() {
        assertNull(recipeBook.editRecipe(0, recipe2));
    }

    @Test
    @Order(9)
    @DisplayName("testSynchronization")
    void testSynchronization() {
        assertAll(
                () -> assertTrue(recipeBook.addRecipe(recipe1)),
                () -> assertTrue(recipeBook.addRecipe(recipe2))
        );

        assertNotNull(recipeBook.getRecipes()[0]);
        assertNotNull(recipeBook.getRecipes()[1]);
    }
}
