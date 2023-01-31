package decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class DECIDETest {
	InputVariables globals;
	Parameters params;

	@BeforeEach
	void setUp() {
		globals = new InputVariables();
		params = new Parameters();
	}

    /**
	 * Tests DECIDE on input that should return true
	 * 
	 * @return Needs to evaluate to true.
	 */
	@Test
	@DisplayName("Tests DECIDE for positive input.")
	void DECIDEPositiveTest1() {
		assertTrue(App.DECIDE(globals, params), "DECIDE did not output true when it should");
	}

    /**
	 * Tests DECIDE on input that should return true
	 * 
	 * @return Needs to evaluate to true.
	 */
	@Test
	@DisplayName("Tests DECIDE for positive input.")
	void DECIDEPositiveTest2() {
		assertTrue(App.DECIDE(globals, params), "DECIDE did not output true when it should");
	}
    
    /**
	 * Tests DECIDE on input that should return true
	 * 
	 * @return Needs to evaluate to false.
	 */
	@Test
	@DisplayName("Tests DECIDE for negative input.")
	void DECIDENegativeTest() {
		assertFalse(App.DECIDE(globals, params), "DECIDE did not output true when it should");
	}
}
