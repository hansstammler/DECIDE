package decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class LICSTest {
	InputVariables globals;
	Parameters params;

	@BeforeEach
	void setUp() {
		globals = new InputVariables();
		params = new Parameters();
	}

	@Test
	@DisplayName("LIC 0 should return correct boolean")
	void LIC0IsCorrect() {
		params.LENGTH1 = 2;

		Point[] points = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5) };
		globals.POINTS = points;
		globals.NUMPOINTS = points.length;
		assertFalse(LICS.zero(globals, params), "LIC 0 did not output false when it should");

		Point[] points2 = { new Point(1, 1), new Point(2, 2), new Point(3, 7) };
		globals.POINTS = points2;
		globals.NUMPOINTS = points2.length;
		assertTrue(LICS.zero(globals, params), "LIC 0 did not output true when it should");

		params.LENGTH1 = -1;
		globals.POINTS = points;
		assertFalse(LICS.zero(globals, params), "LIC 0 did not output false when it should");
	}

	/**
	 * Tests LIC1 condition, i.e. there exists at least three consecutive
	 * points that cannot be cointained in a circle of radius 1.
	 * 
	 * @return Needs to evaluate to true.
	 */
	@Test
	@DisplayName("Tests LIC1 for positive input.")
	void LIC1positiveTest() {
		Point[] points2 = { new Point(100000, 0), new Point(25, 0), new Point(10, 0) };
		globals.NUMPOINTS = points2.length;
		globals.POINTS = points2;
		params.RADIUS1 = 1;
		assertTrue(LICS.one(globals, params), "LIC1 did not output true when it should");
	}

	/**
	 * Tests the inverse of the LIC1 condition, i.e. all three consecutive
	 * points can be contained in a circle of size 1.
	 * 
	 * @return Needs to evaluate to false.
	 */
	@Test
	@DisplayName("Tests LIC1 for negative input.")
	void LIC1negativeTest() {
		params.RADIUS1 = 1;
		Point[] points = { new Point(0., 0.), new Point(1., 1.), new Point(0.5, 0.5) };
		globals.POINTS = points;
		globals.NUMPOINTS = points.length;
		assertFalse(LICS.one(globals, params), "LIC1 did not output true when it should");
	}

	/**
	 * Tests LIC1 with invalid input. The test data contains two points that are at
	 * the same spot. It should return false.
	 * 
	 * @return Needs to evaluate to false.
	 */
	@Test
	@DisplayName("LIC1 should return false for invalid inputs.")
	void LIC1invalidTest() {
		params.RADIUS1 = 1;

		globals.POINTS = new Point[] { new Point(0., 0.), new Point(0., 0.) };
		globals.NUMPOINTS = 2;
		assertFalse(LICS.one(globals, params));
	}

	@Test
	void LIC4IsTrueWhenQ_PTSPointsLieInMoreThanQUADSQuadrants() {
		params.Q_PTS = 2;
		params.QUADS = 1;
		Point[] points = { new Point(1, 1), new Point(-1, 2), new Point(0.5, 0.5) };
		globals.POINTS = points;
		assertTrue(LICS.four(globals, params),
				"LIC4 should output true when Q_PTS consecutive data points lie in more than QUADS quadrants");
	}

	@Test
	void LIC4IsFalseWhenQ_PTSPointsLieInLessThanQUADSQuadrants() {
		params.Q_PTS = 2;
		params.QUADS = 2;
		Point[] points = { new Point(1, 1), new Point(1, 2), new Point(0.5, 0.5) };
		globals.POINTS = points;
		assertFalse(LICS.four(globals, params),
				"LIC4 should output false when Q_PTS consecutive data points lie in less than QUADS quadrants");
	}

	@Test
	@DisplayName("LIC 2 should return correct boolean")
	void LIC2IsCorrect() {
		params.EPSILON = params.PI / 2;
		globals.NUMPOINTS = 3;

		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 0) };
		assertFalse(LICS.two(globals, params), "LIC2 did not output false when a point coincides with the vertex.");

		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 1) };
		assertFalse(LICS.two(globals, params), "LIC2 did not output false when angle >= PI - EPSILON.");

		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(1, 1) };
		assertTrue(LICS.two(globals, params), "LIC2 did not output true when angle < PI - EPSILON.");
	}

	@Test
	void LIC3IsTrueWhenAreaIsGreaterThanAREA1() {
		Point[] points = { new Point(1, 0), new Point(1, 5), new Point(2, 0), new Point(4, 0), new Point(5, 0) };
		globals.POINTS = points;
		params.AREA1 = 2;
		assertTrue(LICS.three(globals, params), "LIC3 should output true when area is greater than AREA1");
	}

	@Test
	void LIC3IsFalseWhenAreaIsEqualToAREA1() {
		Point[] points = { new Point(0, 0), new Point(2, 0), new Point(0, 2) };
		globals.POINTS = points;
		params.AREA1 = 2;
		assertFalse(LICS.three(globals, params), "LIC3 should output false when area is equal to AREA1");
	}

	@Test
	void LIC3IsFalseWhenAreaIsLesserThanAREA1() {
		Point[] points = { new Point(1, 0), new Point(1, 5), new Point(2, 0), new Point(4, 0), new Point(5, 0) };
		globals.POINTS = points;
		params.AREA1 = 10;
		assertFalse(LICS.three(globals, params), "LIC3 should output false when area is lesser than AREA1");
	}

	/**
	 * Tests LIC8 condition, as described in the assignment on p4. It uses points
	 * that are very far away together with a comparably small radius.
	 * 
	 * @return Needs to evaluate to true.
	 */
	@Test
	@DisplayName("Tests LIC8 for positive input.")
	void LIC8positiveTest() {
		params.RADIUS1 = 10;
		params.A_PTS = 1;
		params.B_PTS = 1;

		Point[] points2 = { new Point(1000000, 0), new Point(2.5, 0), new Point(3, 0), new Point(3, 0),
				new Point(3, 0) };
		globals.POINTS = points2;
		globals.NUMPOINTS = points2.length;
		assertTrue(LICS.one(globals, params), "LIC1 did not output true when it should");
	}
	
	/**
	 * Tests the inverse of the LIC8 condition, as described in the assignment on
	 * p4. It uses points
	 * that are very close together and a comparably small radius.
	 * 
	 * @return Needs to evaluate to false.
	 */
	@Test
	@DisplayName("Tests LIC8 for negative input.")
	void LIC8negativeTest() {
		params.RADIUS1 = 10;
		params.A_PTS = 1;
		params.B_PTS = 1;

		Point[] points = { new Point(0., 0.), new Point(1., 0.) };
		globals.POINTS = points;
		globals.NUMPOINTS = points.length;
		assertFalse(LICS.five(globals, params), "LIC1 did not output false when it should");
	}

	@Test
	@DisplayName("LIC 9 should return correct boolean")
	void LIC9IsCorrect() {
		params.EPSILON = params.PI / 2;
		params.C_PTS = 1;
		params.D_PTS = 1;

		globals.NUMPOINTS = 1;
		globals.POINTS = new Point[] { new Point(0, 0) };
		assertFalse(LICS.nine(globals, params), "LIC9 did not output false when NUMPOINTS < 5.");

		globals.NUMPOINTS = 5;
		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0),
				new Point(0, 0) };
		assertFalse(LICS.nine(globals, params), "LIC9 did not output false when a point coincides with the vertex.");

		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0),
				new Point(0, 1) };
		assertFalse(LICS.nine(globals, params), "LIC9 did not output false when angle >= PI - EPSILON.");

		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0),
				new Point(1, 1) };
		assertTrue(LICS.nine(globals, params), "LIC9 did not output true when angle < PI - EPSILON.");
	}

	@Test
	@DisplayName("LIC 10 should return the correct boolean")
	void LIC10IsCorrect() {
		params.AREA1 = 0.5;
		params.E_PTS = 1;
		params.F_PTS = 1;

		Point[] points = { new Point(0., 0.), new Point(0., 0.) };
		globals.POINTS = points;
		globals.NUMPOINTS = points.length;
		assertFalse(LICS.one(globals, params), "LIC1 did not output false when it should");

		Point[] points2 = { new Point(0, 0), new Point(0, 0), new Point(1, 0), new Point(1.5, 2), new Point(3, 0) };
		globals.POINTS = points2;
		globals.NUMPOINTS = points2.length;
		assertTrue(LICS.one(globals, params), "LIC1 did not output true when it should");
	}

	/**
	 * There are no two consecutive data points where the latter is larger than the
	 * former.
	 * 
	 * @result Test will pass if LIC5 is correctly implemeted.
	 */
	@Test
	@DisplayName("LIC 5 should return false for negative instance")
	void LIC5NegativeTest() {
		Point[] points = { new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0) };
		globals.POINTS = points;
		assertFalse(LICS.five(globals, params), "LIC5 did not output false when it should");
	}

	/**
	 * There are two consecutive data points where the latter is larger than the
	 * former.
	 * 
	 * @result Test will pass if LIC5 is correctly implemeted.
	 */
	@Test
	@DisplayName("LIC 5 should return true for positive instance")
	void LIC5PositiveTest() {
		Point[] points = { new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(5, 0), new Point(4, 0) };
		globals.POINTS = points;
		assertTrue(LICS.five(globals, params), "LIC5 did not output true when it should");
	}

	@Test
	@DisplayName("LIC 6 should return correct boolean")
	void LIC6IsCorrect() {
		Point[] points1 = { new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0) };
		params.N_PTS = 3;
		params.DIST = 1.5;
		globals.POINTS = points1;
		globals.NUMPOINTS = points1.length;
		assertFalse(LICS.six(globals, params), "LIC6 did not output false when it should");

		Point[] points2 = { new Point(1, 1), new Point(2, 2), new Point(3, 20), new Point(4, 4), new Point(5, 5) };
		params.N_PTS = 3;
		params.DIST = 5;
		globals.POINTS = points2;
		globals.NUMPOINTS = points2.length;
		assertTrue(LICS.six(globals, params), "LIC6 did not output true when it should");

		Point[] points3 = { new Point(4, 4), new Point(2, 3), new Point(4, 4), new Point(4, 5), new Point(5, 6) };
		params.N_PTS = 3;
		params.DIST = 1.5;
		globals.POINTS = points3;
		globals.NUMPOINTS = points3.length;
		assertTrue(LICS.six(globals, params), "LIC6 did not output true when it should");

		Point[] points4 = { new Point(4, 4), new Point(2, 3), new Point(4, 4), new Point(4, 5), new Point(5, 6) };
		params.N_PTS = 3;
		params.DIST = 10;
		globals.POINTS = points4;
		globals.NUMPOINTS = points4.length;
		assertFalse(LICS.six(globals, params), "LIC6 did not output false when it should");

		params.N_PTS = 2;
		params.DIST = 1.5;
		globals.POINTS = points3;
		globals.NUMPOINTS = points3.length;
		assertFalse(LICS.six(globals, params), "LIC6 did not output false when it should");
	}

	@Test
	@DisplayName("LIC 7 should return correct boolean")
	void LIC7IsCorrect() {
		Point[] points1 = { new Point(2, 3), new Point(4, 5), new Point(6, 7), new Point(8, 9), new Point(10, 11) };
		params.K_PTS = 2;
		params.LENGTH1 = 2.0;
		globals.POINTS = points1;
		globals.NUMPOINTS = points1.length;
		assertTrue(LICS.seven(globals, params), "LIC7 did not output true when it should");

		Point[] points2 = { new Point(2, 3), new Point(4, 5), new Point(6, 7), new Point(8, 9), new Point(10, 11) };
		params.K_PTS = 4;
		params.LENGTH1 = 4;
		globals.POINTS = points2;
		globals.NUMPOINTS = points2.length;
		assertFalse(LICS.seven(globals, params), "LIC7 did not output false when it should");

		params.K_PTS = 0;
		params.LENGTH1 = 2.0;
		globals.POINTS = points1;
		globals.NUMPOINTS = points1.length;
		assertFalse(LICS.seven(globals, params), "LIC7 did not output false when it should");
	}

	/**
	 * There are no two data points separated by G_PTS where the latter is larger
	 * than the former.
	 * 
	 * @result Test will pass if LIC11 is correctly implemeted.
	 */
	@Test
	@DisplayName("LIC 11 should return false for negative instance")
	void LIC11NegativeTest() {
		globals.NUMPOINTS = 5;
		params.G_PTS = 2;

		Point[] points = { new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0) };
		globals.POINTS = points;
		assertFalse(LICS.eleven(globals, params), "LIC11 did not output false when it should");
	}

	/**
	 * There are two data points separated by G_PTS where the latter is larger than
	 * the former.
	 * 
	 * @result Test will pass if LIC5 is correctly implemeted.
	 */
	@Test
	@DisplayName("LIC 11 should return true for positive instance")
	void LIC11PositiveTest() {
		globals.NUMPOINTS = 5;
		params.G_PTS = 2;

		Point[] points = { new Point(1, 0), new Point(6, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0) };
		globals.POINTS = points;
		assertTrue(LICS.eleven(globals, params), "LIC11 did not output true when it should");
	}

	@Test
	@DisplayName("LIC 12 should return correct boolean")
	void LIC12IsCorrect() {
		params.K_PTS = 1;

		globals.NUMPOINTS = 2;
		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0) };
		assertFalse(LICS.twelve(globals, params), "LIC12 did not output false when NUMPOINTS < 3.");

		globals.NUMPOINTS = 3;

		params.LENGTH1 = 2;
		params.LENGTH2 = 2;
		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 1) };
		assertFalse(LICS.twelve(globals, params), "LIC12 did not output false when distance <= LENGTH1.");

		params.LENGTH1 = 1;
		params.LENGTH2 = 1;
		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 1) };
		assertFalse(LICS.twelve(globals, params), "LIC12 did not output false when distance >= LENGTH2.");

		params.LENGTH1 = 1;
		params.LENGTH2 = 2;
		globals.POINTS = new Point[] { new Point(1, 0), new Point(0, 0), new Point(0, 1) };
		assertTrue(LICS.twelve(globals, params),
				"LIC12 did not output true when distance > LENGTH1 and distance < LENGTH2.");
	}

	/**
	 * There are no three data points separated by A_PTS and B_PTS that could be
	 * contained in a circle of RADIUS2
	 * or three that cannot be contained in a circle of radius 1.
	 * 
	 * @result Test will pass if LIC5 is correctly implemeted.
	 */
	@Test
	@DisplayName("LIC 13 should return false for negative instance")
	void LIC13NegativeTest() {
		globals.NUMPOINTS = 5;
		params.A_PTS = 1;
		params.B_PTS = 1;
		params.RADIUS1 = 100000;
		params.RADIUS2 = 1;

		Point[] points = { new Point(1, 1), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0) };
		globals.POINTS = points;
		assertFalse(LICS.thirteen(globals, params), "LIC13 did not output false when it should");

	}

	/**
	 * There are three data points separated by A_PTS and B_PTS that could be
	 * contained in a circle of RADIUS2
	 * and three that cannot be contained in a circle of radius 1.
	 * 
	 * @result Test will pass if LIC5 is correctly implemeted.
	 */
	@Test
	@DisplayName("LIC 13 should return true for positive instance")
	void LIC13PositiveTest() {
		globals.NUMPOINTS = 5;
		params.A_PTS = 1;
		params.B_PTS = 1;

		Point[] points = { new Point(1, 1), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0) };
		globals.POINTS = points;
		params.RADIUS1 = 1;
		params.RADIUS2 = 100000;
		assertTrue(LICS.thirteen(globals, params), "LIC13 did not output true when it should");
	}

	@Test
	void LIC14IsTrueWhenAreaOneIsGreaterThanAREA1AndAreaTwoLesserThanAREA2() {
		params.E_PTS = 1;
		params.F_PTS = 1;
		params.AREA1 = 1.5;
		params.AREA2 = 2;
		globals.NUMPOINTS = 6;
		Point[] points = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(0, 2),
				new Point(0, 1) };
		globals.POINTS = points;
		assertTrue(LICS.fourteen(globals, params),
				"LIC14 should output true when one set of three data points, separated by exactly E PTS and F PTS consecutive intervening points form a triangle > AREA1 and another triangle < AREA2");
	}

	@Test
	void LIC14IsTrueWhenAreaOneIsGreaterThanAREA1AndAreaTwoLesserThanAREA2Simple() {
		params.E_PTS = 1;
		params.F_PTS = 1;
		params.AREA1 = 1;
		params.AREA2 = 3;
		globals.NUMPOINTS = 6;
		Point[] points = { new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0), new Point(0, 2) };
		globals.POINTS = points;
		assertTrue(LICS.fourteen(globals, params),
				"LIC14 should output true when one set of three data points, separated by exactly E PTS and F PTS consecutive intervening points form a triangle > AREA1 and another triangle < AREA2");
	}

	@Test
	void LIC14IsFalseWhenAreaOneIsLesserThanAREA1AndAreaTwoLesserThanAREA2() {
		params.E_PTS = 1;
		params.F_PTS = 1;
		params.AREA1 = 3;
		params.AREA2 = 2;
		globals.NUMPOINTS = 6;
		Point[] points = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(0, 2),
				new Point(0, 1) };
		globals.POINTS = points;
		assertFalse(LICS.fourteen(globals, params), "LIC14 should output false when condition 1 fails and 2 holds");
	}

	@Test
	void LIC14IsFalseWhenAreaOneIsGreaterThanAREA1AndAreaTwoGreaterThanAREA2() {
		params.E_PTS = 1;
		params.F_PTS = 1;
		params.AREA1 = 1.5;
		params.AREA2 = 0.1;
		globals.NUMPOINTS = 6;
		Point[] points = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(0, 2),
				new Point(0, 1) };
		globals.POINTS = points;
		assertFalse(LICS.fourteen(globals, params), "LIC14 should output false when condition 1 holds and 2 fails");
	}

	@Test
	void LIC14IsFalseWhenAreaOneIsLesserThanAREA1AndAreaTwoGreaterThanAREA2() {
		params.E_PTS = 1;
		params.F_PTS = 1;
		params.AREA1 = 3;
		params.AREA2 = 0.1;
		globals.NUMPOINTS = 6;
		Point[] points = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(0, 2),
				new Point(0, 1) };
		globals.POINTS = points;
		assertFalse(LICS.fourteen(globals, params), "LIC14 should output false when condition 1 fails and 2 fails");
	}

	@Test
	void LIC14IsFalseWhenAreaOneIsGreaterThanAREA1AndAreaTwoLesserThanAREA2ButNUMPOINTSLessThan5() {
		params.E_PTS = 1;
		params.F_PTS = 1;
		params.AREA1 = 1.5;
		params.AREA2 = 2;
		globals.NUMPOINTS = 4;
		Point[] points = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(0, 2),
				new Point(0, 1) };
		globals.POINTS = points;
		assertFalse(LICS.fourteen(globals, params),
				"LIC14 should output false when condition 1 hold and 2 holds but NUMPOINTS < 5");
	}
}
