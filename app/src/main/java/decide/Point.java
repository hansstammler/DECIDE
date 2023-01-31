package decide;

/* Adapted from the same source as the SmallestEnclosingCircle. */
/**
 * Point object, has x and y coordinate.
 * Has been adapted from the same source as SmallestEnclosingCircle
 */
final class Point {
	
	public double x;
	public double y;
	
	/**
	 * Point constructor, creates a point with coordinates x and y
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Subtracts the point p from the point this function is called on.
	 * Essentially vector subtracion.
	 * @param p a point p to subtract from current point.
	 * @return a new point with the position of the vector subtraction.
	 */
	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y);
	}
	
	/**
	 * Calculates the euclidean distance between current point and p.
	 * @param p other point to be evaluated.
	 * @return distance
	 */
	public double distance(Point p) {
		return Math.hypot(x - p.x, y - p.y);
	}
	
	
	/**
	 * Gives the cross product of current point and p
	 * @param p another point to calculate the cross product
	 * @return the cross product
	 */
	public double cross(Point p) {
		return x * p.y - y * p.x;
	}

	/**
	 * Returns angle between point and p, or -1 if any of them coincides with the vertex.
	 * @param p Point
	 * @param vertex Point
	 * @return angle or -1
	 */
	public double angle(Point p, Point vertex) {
		// Check if any of the point and p coincide with the vertex
		if((x == vertex.x && y == vertex.y) || (p.x == vertex.x && p.y == vertex.y)) return -1;

		// Otherwise, calculate angle
		double num = x * p.x + y * p.y;
		double den = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
		return Math.acos(num/den);
	}
	
	/**
	 * Gives a string representation of the point
	 * @return the string representation of the point
	 */
	public String toString() {
		return String.format("Point(%g, %g)", x, y);
	}
}