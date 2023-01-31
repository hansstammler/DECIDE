package decide;

public class Geometry {

    /**
     * Calculates the area of a triangle from a set of three Point objects representing
     * the corners of the triangle.
     * 
     * @param a Point
     * @param b Point
     * @param c Point
     * @return double
     */
    static double triangleArea(Point a, Point b, Point c) {
        double area = 0.5 * ((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y));
        return area > 0 ? area : -area;
    }

}
