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
    @DisplayName("LIC 1 shoudl return the correct boolean")
    void LIC1IsCorrect() {
        params.RADIUS1 = 1;
        Point[] points = {new Point(0., 0.), new Point(1., 1.), new Point(0.5, 0.5)};
        globals.POINTS = points;
        globals.NUMPOINTS = points.length;
        assertFalse(LICS.one(globals, params), "LIC1 did not output true when it should");

        Point[] points2 = {new Point(100000, 0), new Point(25, 0), new Point(10, 0)};
        globals.NUMPOINTS = points2.length;
        globals.POINTS = points2;
        assertTrue(LICS.one(globals, params), "LIC1 did not output true when it should");
        
        globals.POINTS = new Point[]{new Point(0., 0.)};
        globals.NUMPOINTS = 1;

        assertFalse(LICS.one(globals, params));
    }

    @Test
    @DisplayName("LIC 2 should return correct boolean")
    void LIC2IsCorrect() {
        params.EPSILON = params.PI/2;

        globals.POINTS = new Point[] {new Point(1, 0), new Point(0, 0), new Point(0, 0)};
        assertFalse(LICS.two(globals, params), "LIC2 did not output false when a point coincides with the vertex.");

        globals.POINTS = new Point[] {new Point(1, 0), new Point(0, 0), new Point(0, 1)};
        assertFalse(LICS.two(globals, params), "LIC2 did not output false when angle >= PI - EPSILON.");

        globals.POINTS = new Point[] {new Point(1, 0), new Point(0, 0), new Point(1, 1)};
        assertTrue(LICS.two(globals, params), "LIC2 did not output true when angle < PI - EPSILON.");
    }
    
    @Test
    @DisplayName("LIC 8 shoudl return the correct boolean")
    void LIC8IsCorrect() {
        params.RADIUS1 = 10;
        params.A_PTS = 1;
        params.B_PTS = 1;
        
        Point[] points = {new Point(0., 0.), new Point(1., 0.)};
        globals.POINTS = points;
        globals.NUMPOINTS = points.length;
        assertFalse(LICS.five(globals, params), "LIC1 did not output false when it should");


        Point[] points2 = {new Point(1000000, 0), new Point(2.5, 0), new Point(3, 0), new Point(3, 0), new Point(3, 0)};
        globals.POINTS = points2;
        globals.NUMPOINTS = points2.length;
        assertTrue(LICS.one(globals, params), "LIC1 did not output true when it should");
    }
    @Test
    @DisplayName("LIC 10 shoudl return the correct boolean")
    void LIC10IsCorrect() {
        params.AREA1 = 0.5;
        params.E_PTS = 1;
        params.F_PTS = 1;
        
        Point[] points = {new Point(0., 0.), new Point(0., 0.)};
        globals.POINTS = points;
        assertFalse(LICS.one(globals, params), "LIC1 did not output false when it should");

        Point[] points2 = {new Point(0, 0), new Point(0, 0), new Point(1, 0), new Point(1.5, 2), new Point(3, 0)};
        globals.POINTS = points2;
        assertTrue(LICS.one(globals, params), "LIC1 did not output true when it should");
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
        params.RADIUS1 = 1;
        params.RADIUS2 = 100000;
        assertTrue(LICS.thirteen(globals, params), "LIC13 did not output true when it should");
    }
}
