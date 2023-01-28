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
    @DisplayName("LIC 5 should return correct boolean")
    void LIC5IsCorrect(){
        Point[] points = {new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0)};
        globals.POINTS = points;
        assertFalse(LICS.five(globals, params), "LIC5 did not output false when it should");

        Point[] points2 = {new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(5, 0), new Point(4, 0)};
        globals.POINTS = points2;
        assertTrue(LICS.five(globals, params), "LIC5 did not output false when it should");
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
        assertTrue(LICS.eleven(globals, params), "LIC11 did not output false when it should");
    }
}
