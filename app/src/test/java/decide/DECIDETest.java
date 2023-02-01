package decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class DECIDETest {
	//InputVariables globals;
	//Parameters params;
    App app;

	@BeforeEach
	void setUp() {
		//globals = new InputVariables();
		//params = new Parameters();
        app = new App();
	}

    /**
	 * Tests DECIDE on input and parameters that should result in a launch.
     * The LCM is randomly generated but all LICS are true and all elements
     * in the PUV is false, which should result in a launch.
	 * 
	 * @return Needs to evaluate to true.
	 */
	@Test
	@DisplayName("Tests DECIDE for positive input.")
	void DECIDEPositiveTest1() {
        app.params.LENGTH1 = 2;
        app.params.RADIUS1 = 0.5;
        app.params.EPSILON = app.params.PI/2;
        app.params.AREA1 = 2;
        app.params.Q_PTS = 3;
        app.params.QUADS = 2;
        app.params.DIST = 2;
        app.params.N_PTS = 5;
        app.params.K_PTS = 2;
        app.params.A_PTS = 2;
        app.params.B_PTS = 3;
        app.params.C_PTS = 2;
        app.params.D_PTS = 1;
        app.params.E_PTS = 3;
        app.params.F_PTS = 3;
        app.params.G_PTS = 3;
        app.params.LENGTH2 = 3;
        app.params.RADIUS2 = 5;
        app.params.AREA2 = 10;
		app.inputVariables.POINTS = new Point[] { 
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
        app.inputVariables.NUMPOINTS = 50;
		LogicalConnectorMatrix lcm = new LogicalConnectorMatrix('r');
        app.inputVariables.LCM = lcm;
        boolean[] puv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            puv[i] = false;
        }
        app.inputVariables.PUV = puv;

		assertTrue(app.DECIDE(), "DECIDE did not output true when it should");
	}

    /**
	 * Tests DECIDE with an LCM matrix that is full of NOTUSED entries. Must return true because the results
	 * in the FUV do not depend on the CMV anymore. The used points and values are essentially random.
	 * 
	 * @return Needs to evaluate to true.
	 */
	@Test
	@DisplayName("Tests DECIDE for positive input.")
	void DECIDEPositiveTest2() {
        app.params.LENGTH1 = 3;
        app.params.RADIUS1 = 100.;
        app.params.EPSILON = app.params.PI/2;
        app.params.AREA1 = 100;
        app.params.Q_PTS = 2;
        app.params.QUADS = 1;
        app.params.DIST = 1;
        app.params.N_PTS = 1;
        app.params.K_PTS = 1;
        app.params.A_PTS = 1;
        app.params.B_PTS = 1;
        app.params.C_PTS = 1;
        app.params.D_PTS = 1;
        app.params.E_PTS = 1;
        app.params.F_PTS = 1;
        app.params.G_PTS = 1;
        app.params.LENGTH2 = 1;
        app.params.RADIUS2 = 100;
        app.params.AREA2 = 100;
		app.inputVariables.POINTS = new Point[] { 
            new Point(0, 1), new Point(8, 1), new Point(4, 1), // 0 and 1
            new Point(3, 6), new Point(4, 19), new Point(25, 5), // 2
            new Point(1, 6), new Point(70, 45), new Point(14, 2.32), // 3 
            new Point(1, 1), new Point(-1, -3), new Point(-9, -1), // 4 and 5
            new Point(2342, 123), new Point(123, 123412), new Point(34, 677), new Point(24, 123), new Point(5, 1), // 6 and 7
            new Point(0, 0), new Point(1, 666), new Point(1, -2), new Point(3, 123), new Point(1, 3), new Point(7, 0), new Point(0, 0), new Point(6, 1), // 8 and 13
            new Point(1, 1), new Point(4.564, 2.2333), new Point(12, 1), new Point(54.3, 0.21), new Point(62.5, 10), new Point(16, 0), // 9
            new Point(12, 1), new Point(21, 1), new Point(-31, 24), new Point(34, -11), new Point(-31, -2), new Point(17, 0.54), new Point(0.22, 1.0002), new Point(45, 11), new Point(1234.123, -6.1312), new Point(-3.41, -0.623), // 10 and 14
            new Point(124, 0), new Point(0., 0.), new Point(19.2, 0.121321), new Point(7.6421, 0.6234), new Point(-2.234, -0.312), // 11
            new Point(15.872, 111), new Point(0.3, 762.1456), new Point(14.245, 12431.12), new Point(612.451, -142213.3512) // 12
        };
		LogicalConnectorMatrix lcm = new LogicalConnectorMatrix();
		app.inputVariables.LCM = lcm;
        app.inputVariables.NUMPOINTS = 50;
		assertTrue(app.DECIDE(), "DECIDE did not output true when it should");

	}
    
    /**
	 * Tests DECIDE on input that should return true
	 * 
	 * @return Needs to evaluate to false.
	 */
	@Test
	@DisplayName("Tests DECIDE for negative input.")
	void DECIDENegativeTest() {
		assertFalse(app.DECIDE(), "DECIDE did not output true when it should");
	}
}
