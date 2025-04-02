package najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import najah.code.Calculator;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorTest {

	private Calculator calc;

	@BeforeAll
	static void setupAll() {
		System.out.println("setup");
	}

	@AfterAll
	static void closeAll() {
		System.out.println("close");
	}

	@BeforeEach
	void setup() {
		calc = new Calculator();
		System.out.println("Make new oject from Calculator");
	}

	@AfterEach
	void clean() {
		System.out.println("clean after each test");
	}

	@Test
	@DisplayName("test Addition")
	@Order(1)
	void testAddition() {
		assertEquals(10, calc.add(5, 5));
		assertEquals(15, calc.add(7, 8));
		assertEquals(0, calc.add());
	}

	@ParameterizedTest
	@DisplayName("test add using csv sourse ")
	@CsvSource({
			"1,2,3",
			"2,3,5",
			"11,22,33"
	})
	@Order(2)
	void testAdditionParameterized(int a, int b, int expected) {
		assertEquals(expected, calc.add(a, b));
	}

	@Test
	@DisplayName("test divisoin")
	@Order(3)
	void testDivision() {
		assertEquals(2, calc.divide(10, 5));
		assertEquals(5, calc.divide(25, 5));
	}

	@Test
	@DisplayName("test division and use assert throw")
	@Order(4)
	void testDivisionByZero() {
		assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
	}

	@Test
	@DisplayName("test fact")
	@Order(5)
	void testFactorialValid() {
		assertEquals(1, calc.factorial(0));
		assertEquals(1, calc.factorial(1));
		assertEquals(120, calc.factorial(5));
	}


	@Test
	@DisplayName("test time")
	@Order(7)
	@Timeout(value = 5000,unit =	TimeUnit.MILLISECONDS)
	void testTimeout() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

	@Test
	@DisplayName("fail test")
	@Disabled("fail because the result of adding not true")
	@Order(8)
	void testFailingTest() {
		assertEquals(20, calc.add(7, 8));
	}
}

