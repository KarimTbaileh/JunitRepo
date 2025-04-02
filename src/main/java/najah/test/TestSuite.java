package najah.test;

import najah.code.UserService;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SelectClasses({CalculatorTest.class, UserService.class, ProductTest.class, RecipeTest.class, UserServiceTest.class,RecipeBookTest.class})
public class TestSuite {
}