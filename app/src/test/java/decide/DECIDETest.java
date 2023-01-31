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
        params.LENGTH1 = 2;
        params.RADIUS1 = 0.5;
        params.EPSILON = params.PI/2;
        params.AREA1 = 2;
        params.Q_PTS = 3;
        params.QUADS = 2;
        params.DIST = 2;
        params.N_PTS = 5;
        params.K_PTS = 2;
        params.A_PTS = 2;
        params.B_PTS = 3;
        params.C_PTS = 2;
        params.D_PTS = 1;
        params.E_PTS = 3;
        params.F_PTS = 3;
        params.G_PTS = 3;
        params.LENGTH2 = 3;
        params.RADIUS2 = 5;
        params.AREA2 = 10;
		globals.POINTS = new Point[] { 
            new Point(0, 0), new Point(3, 0), new Point(1, 1), // 0 and 1
            new Point(2, 2), new Point(1, 1), new Point(2, 1), // 2
            new Point(0, 0), new Point(0, 4), new Point(4, 0), // 3 
            new Point(1, 1), new Point(-1, 1), new Point(-1, -1), // 4 and 5
            new Point(0, 0), new Point(1, 1), new Point(2, 4), new Point(4, 0), new Point(4, 1), // 6 and 7
            new Point(0, 0), new Point(1, 1), new Point(-1, 2), new Point(3, -1), new Point(2, 1), new Point(1, 0), new Point(0, 1), new Point(4, 1), // 8 and 13
            new Point(1, 1), new Point(4.5, 2), new Point(1, 1), new Point(5.3, 0.1), new Point(2.5, 10), new Point(1, 0), // 9
            new Point(1, 1), new Point(2, 1), new Point(-1, 2), new Point(3, -1), new Point(-3, -2), new Point(1, 0), new Point(0, 1), new Point(4, 1), new Point(1, -6), new Point(3, 0), // 10 and 14
            new Point(4, 0), new Point(0.3, 4.3), new Point(4.2, 0.12), new Point(7.6, 0), new Point(2, 0), // 11
            new Point(0.1, 0), new Point(0.3, 4.3), new Point(4.2, 0.12), new Point(2.5, 0) // 12
        };
        globals.NUMPOINTS = 50;
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
