package decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


public class LICSTest {

    InputVariables globals;
    Parameters params;

    @BeforeEach
    void setUp(){
        globals = new InputVariables();
        params = new Parameters();
    }

    @Test
    @DisplayName("LIC 0 should return correct boolean")
    void LIC0IsCorrect(){
        params.LENGTH1 = 2;

        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5)};
        globals.POINTS = points;
        assertFalse(LICS.zero(globals, params), "LIC 0 did not output false when it should");

        Point[] points2 = {new Point(1, 1), new Point(2, 2), new Point(3, 7)};
        globals.POINTS = points2;
        assertTrue(LICS.zero(globals, params), "LIC 0 did not output true when it should");
    }
    
    @Test
    @DisplayName("LIC 5 should return correct boolean")
    void LIC5IsCorrect(){
        Point[] points = {new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0)};
        globals.POINTS = points;
        assertFalse(LICS.five(globals, params), "LIC5 did not output false when it should");

        Point[] points2 = {new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(5, 0), new Point(4, 0)};
        globals.POINTS = points2;
        assertTrue(LICS.five(globals, params), "LIC5 did not output true when it should");
    }

    @Test
    @DisplayName("LIC 6 should return correct boolean")
    void LIC6IsCorrect(){
        Point[] points1 = {new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0)};
        params.N_PTS = 3;
        params.DIST = 1.5;
        globals.POINTS = points1;
        assertFalse(LICS.six(globals, params), "LIC6 did not output false when it should");

        Point[] points2 = {new Point(1, 1), new Point(2, 2), new Point(3, 20), new Point(4, 4), new Point(5, 5)};
        params.N_PTS = 3;
        params.DIST = 5;
        globals.POINTS = points2;
        assertTrue(LICS.six(globals, params), "LIC6 did not output true when it should");

        Point[] points3 = {new Point(4, 4), new Point(2, 3), new Point(4, 4), new Point(4, 5), new Point(5, 6)};
        params.N_PTS = 3;
        params.DIST = 1.5;
        globals.POINTS = points3;
        assertTrue(LICS.six(globals, params), "LIC6 did not output true when it should");

        Point[] points4 = {new Point(4, 4), new Point(2, 3), new Point(4, 4), new Point(4, 5), new Point(5, 6)};
        params.N_PTS = 3;
        params.DIST = 10;
        globals.POINTS = points4;
        assertFalse(LICS.six(globals, params), "LIC6 did not output false when it should");
    }

    @Test
    @DisplayName("LIC 11 should return correct boolean")
    void LIC11IsCorrect(){
        globals.NUMPOINTS = 5;
        params.G_PTS = 2;

        Point[] points = {new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0)};
        globals.POINTS = points;
        assertFalse(LICS.eleven(globals, params), "LIC11 did not output false when it should");

        Point[] points2 = {new Point(1, 0), new Point(6, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0)};
        globals.POINTS = points2;
        assertTrue(LICS.eleven(globals, params), "LIC11 did not output true when it should");
    }

    @Test
    @DisplayName("LIC 13 should return correct boolean")
    void LIC13IsCorrect(){
        globals.NUMPOINTS = 5;
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 100000;
        params.RADIUS2 = 1;

        Point[] points = {new Point(1, 1), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0)};
        globals.POINTS = points;
        assertFalse(LICS.thirteen(globals, params), "LIC13 did not output false when it should");
        params.RADIUS2 = 100000;
        assertTrue(LICS.thirteen(globals, params), "LIC13 did not output true when it should");
    }
}
