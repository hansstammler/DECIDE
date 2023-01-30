package decide;

import java.util.ArrayList;

public class LICS {
	static boolean zero(InputVariables globals, Parameters params) {
		return false;
	}
	
	/**
	 * 
	 * @param globals Instance of InputVariables, gives the state to evaluate
	 * @param params Instance of parameters
	 * @return truth value of launch condition 1
	 */

	static boolean one(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 3) {
			return false;
		}
		boolean could_bound = true;
		for (int i = 0; i + 2 < globals.NUMPOINTS && could_bound; i+= 3) {
			ArrayList<Point> points = new ArrayList<Point>();
			for (int j = 0; j < 3; j++) {
				points.add(globals.POINTS[i+j]);
			}
			/* This function uses an implementation for the bounding circle problem because that is nontrivial. */
			Circle cir = SmallestEnclosingCircle.makeCircle(points);
			could_bound &= cir.r <= params.RADIUS1;
		}

		return !could_bound;
	}

	/**
	 * Returns true if there exists a set of three consecutive points 
	 * in array POINTS in globals which form an angle 
	 * (with second point as vertex) such that 
	 * angle < (PI - EPISILON) or angle > (PI + EPSILON).
	 * If not met, or any point coincides with the vertex, false is returned.
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean two(InputVariables globals, Parameters params) {
		for(int i = 0; i < globals.NUMPOINTS - 2; i++) {
			double vertex[] = {globals.POINTS[i + 1].x, globals.POINTS[i + 1].y};
			double u[] = {globals.POINTS[i].x - vertex[0], globals.POINTS[i].y - vertex[1]};
			double v[] = {globals.POINTS[i + 2].x - vertex[0], globals.POINTS[i + 2].y - vertex[1]};

			double angle = getAngle(u, v, vertex);

			if(angle == -1) return false;  // If undefined angle, LIC is not satisfied
			if(angle < params.PI - params.EPSILON || angle > params.PI + params.EPSILON) return true;
		}
		return false;
	}

	/**
	 * Returns true if there exists at least one set of three consecutive data 
	 * points that are the vertices of a triangle with area greater than AREA1.
	 * 
	 * The area is calculted with the determinant from the matrix of the three points
	 * a, b and c:
	 * X1 Y1 1
	 * X2 Y2 1
	 * X3 Y3 1
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean three(InputVariables globals, Parameters params) {
		for (int i = 0; i < globals.POINTS.length - 1; i++) {     // Why -1?
			Point a = globals.POINTS[i];
			Point b = globals.POINTS[i + 1];
			Point c = globals.POINTS[i + 2];
			double area = (a.x*(b.y - c.y) + b.x*(c.y - a.y) + c.x*(a.y - b.y))/2;
			if(area >= params.AREA1)
				return true;
		}
		return false;
	}

	static boolean four(InputVariables globals, Parameters params) {
		return false;
	}

	/**
	 * Returns true if there exists two consectutive points in array POINTS in globals
	 * such that the difference in x values of a point and the point prior to it is negative.
	 * Returns false otherwise.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean five(InputVariables globals, Parameters params) {
		for(int i = 0; i < globals.POINTS.length - 1; i++){
			if(globals.POINTS[i+1].x - globals.POINTS[i].x < 0)
				return true;
		}
		return false;
	}

	static boolean six(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean seven(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean eight(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 5) {
			return false;
		}
		boolean could_bound = true;
		for (int i = 0; i + params.A_PTS + params.B_PTS + 1 < globals.POINTS.length; i++){
			Point a = globals.POINTS[i];
			Point b = globals.POINTS[i+params.A_PTS + 1];
			Point c = globals.POINTS[i+params.A_PTS+params.B_PTS + 2];
			
			ArrayList<Point> points = new ArrayList<Point>();
			points.add(a);
			points.add(b);
			points.add(c);
			/* This function uses an implementation for the bounding circle problem because that is nontrivial. */
			Circle cir = SmallestEnclosingCircle.makeCircle(points);
			could_bound &= cir.r <= params.RADIUS1;
		}
		
		return !could_bound;
	}

	/**
	 * Returns true if there exists a set of three points separated by exactly 
	 * C_PTS and D_PTS consecutive intervening points, respectively,
	 * in array POINTS in globals which form an angle 
	 * (with second point as vertex) such that 
	 * angle < (PI - EPISILON) or angle > (PI + EPSILON).
	 * If not met, or any point coincides with the vertex, or NUMPOINTS < 5, 
	 * false is returned.
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean nine(InputVariables globals, Parameters params) {
		if(globals.NUMPOINTS < 5) return false;

		for(int i = 0; i < globals.NUMPOINTS - params.C_PTS - params.D_PTS - 2; i++) {
			double vertex[] = {globals.POINTS[i + params.C_PTS + 1].x, globals.POINTS[i + params.C_PTS + 1].y};
			double u[] = {globals.POINTS[i].x - vertex[0], globals.POINTS[i].y - vertex[1]};
			double v[] = {globals.POINTS[i + params.C_PTS + params.D_PTS + 2].x - vertex[0], 
							globals.POINTS[i + params.C_PTS + params.D_PTS + 2].y - vertex[1]};

			double angle = getAngle(u, v, vertex);

			if(angle == -1) return false;  // If undefined angle, LIC is not satisfied
			if(angle < params.PI - params.EPSILON || angle > params.PI + params.EPSILON) return true;			
		}
		return false;
	}
	/**
	 * Returns true if there exists at least one set of three data points separated by exactly E PTS and F PTS con-
secutive intervening points, respectively, that are the vertices of a triangle with area greater
than AREA1.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */

	static boolean ten(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 5) {
			return false;
		}
		boolean got_area = false;
		for (int i = 0; i + params.E_PTS + params.F_PTS + 2 < globals.NUMPOINTS && !got_area; i++) {
			Point a = globals.POINTS[i];
			Point b = globals.POINTS[i + params.E_PTS + 1];
			Point c = globals.POINTS[i + params.E_PTS + params.F_PTS+ 2];

			double ab = a.distance(b);
			double bc = b.distance(c);
			double ca = c.distance(a);

			double s = (ab + bc + ca) / 2;

			// Herons formula
			double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));

			got_area |= area >= params.AREA1;
		}

		return got_area;
	}

	/**
	 * Returns true if there exists two poins in array POINTS in globals
	 * separated by G_PTS points such that the difference in x values of a 
	 * point and the prior to it is negative.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean eleven(InputVariables globals, Parameters params) {
		if (globals.NUMPOINTS < 3 || params.G_PTS < 1 || globals.NUMPOINTS < params.G_PTS) return false;
		for(int i = 0; i < globals.POINTS.length - 1 - params.G_PTS; i++){
			if(globals.POINTS[i + params.G_PTS + 1].x - globals.POINTS[i].x < 0)
				return true;
		}
		return false;
	}

	static boolean twelve(InputVariables globals, Parameters params) {
		return false;
	}
	
	/**
	 * Returns true if there exists set(s) of three points in array POINTS in globals
	 * separated by A_PTS and B_PTS consecutive intervening points respectively
	 * that can be contained in circles of both RADIUS1 and RADIUS2.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	//https://opencurriculum.org/5492/circumscribed-and-inscribed-circles/ corollary 3.
	//https://www.geogebra.org/m/mWnvve4s
	//Calculates radius of circumscribed circle and compares to RADIUS1 and RADIUS2
	//if any angle is larger than 90 degrees the radius is half of longest side (obtuse triangle -> radius is half of longest side)
	//https://www.varsitytutors.com/gmat_math-help/calculating-the-length-of-the-side-of-an-acute-obtuse-triangle#:~:text=To%20determine%20whether%20the%20triangle,square%20of%20the%20largest%20side.&text=Since%20this%20sum%20is%20greater%2C%20the%20triangle%20is%20acute.
	static boolean thirteen(InputVariables globals, Parameters params) {
		if(globals.NUMPOINTS < 5 || params.RADIUS2 <= 0) return false;
		boolean cond1 = false, cond2 = false;
		double a, b, c, s, area, radius;
		Point p1, p2, p3;
		for(int i = 0; i < globals.POINTS.length - 2 - params.A_PTS - params.B_PTS; i++){
			p1 = globals.POINTS[i];
			p2 = globals.POINTS[i + 1 + params.A_PTS];
			p3 = globals.POINTS[i + 2 + params.A_PTS + params.B_PTS];

			a = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
			b = Math.sqrt(Math.pow(p2.x - p3.x, 2) + Math.pow(p2.y - p3.y, 2));
			c = Math.sqrt(Math.pow(p3.x - p1.x, 2) + Math.pow(p3.y - p1.y, 2));

			s = (a + b + c) / 2;
			area = Math.sqrt(s * (s - a)*(s - b)*(s - c));
			radius = (a * b * c) / (4 * area);
			//make a the longest side
			if (c > b){
				double temp = c;
				c = b;
				b = temp;
			}
			if (b > a){
				double temp = b;
				b = a;
				a = temp;
			}
			//if the triangle is obtuse circle center is middle of longest side and thus radius is half of longest side.
			if(Math.pow(b, 2) + Math.pow(c, 2) < Math.pow(a, 2)) radius = a/2;	

			if(radius > params.RADIUS1) cond1 = true;
			if(radius <= params.RADIUS2) cond2 = true;

			if (cond1 && cond2) return true;
		}
		return false;
	}

	static boolean fourteen(InputVariables globals, Parameters params) {
		return false;
	}

	/**
	 * Helper method that returns angle between u and v, or -1 if u or v coincides with the vertex.
	 * @param vertex 2D vector (double[])
	 * @param u 2D vector (double[])
	 * @param v 2D vector (double[])
	 * @return angle or -1
	 */
	static double getAngle(double[] u, double[] v, double[] vertex) {
		// Check if any of u and v coincide with the vertex
		if((u[0] == vertex[0] && u[1] == vertex[1]) 
			|| (v[0] == vertex[0] && v[1] == vertex[1])) {
			return -1;
		}
		// Otherwise, calculate angle
		double num = u[0]*v[0] + u[1]*v[1];
		double den = Math.sqrt(Math.pow(u[0], 2) + Math.pow(u[1], 2)) 
					* Math.sqrt(Math.pow(v[0], 2) + Math.pow(v[1], 2));
		return Math.acos(num/den);
	}
}
