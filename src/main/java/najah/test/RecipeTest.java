package najah.test;



import najah.code.Recipe;
import najah.code.RecipeException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeTest {

    private Recipe recipe;

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void setup() {
        recipe = new Recipe();
        System.out.println("make object from recipe");
    }

    @AfterEach
    void close() {
        System.out.println("close");
    }

    @Test
    @Order(1)
    @DisplayName("test defualt constructor")
    void testDefualtConstructor() {
        assertNotNull(recipe);
        assertEquals("", recipe.getName());
        assertEquals(0, recipe.getPrice());
        assertEquals(0, recipe.getAmtCoffee());
        assertEquals(0, recipe.getAmtMilk());
        assertEquals(0, recipe.getAmtSugar());
        assertEquals(0, recipe.getAmtChocolate());
    }

    @Test
    @Order(2)
    @DisplayName("test Setter and getter for Name")
    void testSetName() {
        recipe.setName("Cappuccino");
        assertEquals("Cappuccino", recipe.getName());
    }

    @Test
    @Order(3)
    @DisplayName("test negative price")
    void testNegativePrice() {
        assertThrows(RecipeException.class, () -> recipe.setPrice("-7"));
    }

    @ParameterizedTest
    @Order(4)
    @DisplayName("test setter and getter for price")
    @ValueSource(strings = {"0", "7", "180", "150"})
    void testValidPrice(String price) throws RecipeException {
        recipe.setPrice(price);
        assertEquals(Integer.parseInt(price), recipe.getPrice());
    }

    @Test
    @Order(5)
    @DisplayName(" Set invalid coffee amount")
    void testInvalidCoffeeAmount() {
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("-6"));
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("karim"));
    }

    @Test
    @Order(6)
    @DisplayName("Set and get valid coffee amount")
    void testValidCoffeeAmount() throws RecipeException {
        recipe.setAmtCoffee("7");
        assertEquals(7, recipe.getAmtCoffee());
    }

    @Test
    @Order(7)
    @DisplayName("test time out")
    @Timeout(value = 5000,unit = TimeUnit.MILLISECONDS)
    void testTimeout() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(8)
    @DisplayName("fail test ")
    @Disabled("fail test because the number should be positive  ")
    void testDisabled() throws RecipeException {
        recipe.setAmtChocolate("-10");
        assertEquals(-10, recipe.getAmtChocolate());
    }

    @Test
    @Order(9)
    @DisplayName("to string test")
    void testToString() {
        recipe.setName("Latte");
        assertEquals("Latte", recipe.toString());
    }

    @Test
    @Order(10)
    @DisplayName("test equal method")
    void testEquals() {
        Recipe r1 = new Recipe();
        r1.setName("Espresso");

        Recipe r2 = new Recipe();
        r2.setName("Espresso");

        Recipe r3 = new Recipe();
        r3.setName("Mocha");

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
    }


    @Test
    @Order(11)
    @DisplayName("test hashcode method")
    void testHashCode() {
        Recipe r1 = new Recipe();
        r1.setName("Cappuccino");

        Recipe r2 = new Recipe();
        r2.setName("Cappuccino");

        assertEquals(r1.hashCode(), r2.hashCode());
    }


    @ParameterizedTest
    @Order(12)
    @DisplayName("test set and get amt chocolate")
    @ValueSource(strings = {"0", "5", "20"})
    void testSetAndGetChocolate(String chocolate) throws RecipeException {
        recipe.setAmtChocolate(chocolate);
        assertEquals(Integer.parseInt(chocolate), recipe.getAmtChocolate());
    }


    @ParameterizedTest
    @Order(13)
    @DisplayName("test set and get amt suger")
    @ValueSource(strings = {"0", "2", "7", "15"})
    void testSetAndGetSuger(String sugar) throws RecipeException {
        recipe.setAmtSugar(sugar);
        assertEquals(Integer.parseInt(sugar), recipe.getAmtSugar());
    }


    @ParameterizedTest
    @Order(14)
    @DisplayName("test set and get amt Milk")
    @ValueSource(strings = {"0", "3", "10", "25"})
    void testSetAndGetMilk(String milk) throws RecipeException {
        recipe.setAmtMilk(milk);
        assertEquals(Integer.parseInt(milk), recipe.getAmtMilk());
    }


}


