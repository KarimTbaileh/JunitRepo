package najah.test;

import najah.code.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
 class UserServiceTest {
	private UserService userService;

	@BeforeEach
	void setUp() {
		userService = new UserService();
	}

	@Test
	@DisplayName("Test Email")
	void testIsValidEmail() {
		assertTrue(userService.isValidEmail("Karim@Tbaileh.com"));
		assertFalse(userService.isValidEmail("KarimTbaileh.com"));
		assertFalse(userService.isValidEmail("karim@.com"));
		assertFalse(userService.isValidEmail(null));
	}

	@Test
	@DisplayName("Test Authenticate")
	void testAuthenticateValid() {
		assertTrue(userService.authenticate("admin", "1234"));
	}

	@Test
	@DisplayName("Test Reject Pass")
	void testAuthenticateInvalid() {
		assertFalse(userService.authenticate("admin", "12334555"));
		assertFalse(userService.authenticate("user", "1234"));
		assertFalse(userService.authenticate("", ""));
		assertFalse(userService.authenticate(null, null));
	}
}
