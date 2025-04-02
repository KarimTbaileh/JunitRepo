package najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import najah.code.Product;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

	private Product product;

	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("afterAll");
	}

	@BeforeEach
	void setup() {
		product = new Product("table", 200);
		System.out.println("make object ");
	}

	@AfterEach
	void close() {
		System.out.println("close");
	}

	@Test
	@Order(1)
	@DisplayName("test the setters and getters")
	void testSettersAndGetters() {
		assertNotNull(product);
		assertEquals("table", product.getName());
		assertEquals(200, product.getPrice());
		assertEquals(0, product.getDiscount());
	}

	@Test
	@Order(2)
	@DisplayName("test negative price")
	void testNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> new Product("Phone", -200));
	}

	@ParameterizedTest
	@Order(3)
	@DisplayName("test a Discount Method")
	@ValueSource(doubles = {10, 20, 30, 40, 50})
	void testDiscount(double discount) {
		product.applyDiscount(discount);
		assertEquals(discount, product.getDiscount());
	}

	@Test
	@Order(4)
	@DisplayName("test invalid discount")
	void testInvalidDiscount() {
		assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-30));
		assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(80));
	}

	@Test
	@Order(5)
	@DisplayName("test final price method")
	void testGetFinalPrice() {
		product.applyDiscount(20);
		assertEquals(160, product.getFinalPrice());
	}

	@Test
	@Order(6)
	@DisplayName("test time out")
	@Timeout(value = 1000,unit =	TimeUnit.MILLISECONDS)
	void testTimeout() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	@Order(7)
	@DisplayName("fail test")
	@Disabled("fail test because the value of discount is higher than 50")
	void testDisabled() {
		product.applyDiscount(110);
		assertEquals(90, product.getDiscount());
	}
}
