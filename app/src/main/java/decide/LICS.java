package decide;

public class LICS {

	/**
	 * Returns true if there exists at least one set of two consecutive data points that are a distance greater than
	 * the length, LENGTH1, apart.
	 * Returns false otherwise.
	 * 
	 * @param globals Instance of InputVariables
	 * @param params Instance of Parameters
	 * @return true or false
	 */
	static boolean zero(InputVariables globals, Parameters params) {
		for(int i = 0; i < globals.POINTS.length - 1; i++){
			if(Math.sqrt(Math.pow(globals.POINTS[i+1].x - globals.POINTS[i].x, 2) + Math.pow(globals.POINTS[i+1].y - globals.POINTS[i].x, 2)) > params.LENGTH1)
				return true;
		}
		return false;
	}

	static boolean one(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean two(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean three(InputVariables globals, Parameters params) {
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
		return false;
	}

	static boolean nine(InputVariables globals, Parameters params) {
		return false;
	}

	static boolean ten(InputVariables globals, Parameters params) {
		return false;
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
		double s1, s2, s3;
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

			if (a > b && a > c){
				s1 = a;
				s2 = b;
				s3 = c;
			}
			else if (b > a && b > c){
				s1 = b;
				s2 = a;
				s3 = c;
			}
			else {
				s1 = c;
				s2 = b;
				s3 = c;
			}
			//if the triangle is obtuse circle center is middle of longest side and thus radius is half of longest side.
			if(Math.pow(s2, 2) + Math.pow(s3, 2) < Math.pow(s1, b)) radius = s1/2;	

			if(radius <= params.RADIUS1) cond1 = true;
			if(radius <= params.RADIUS2) cond2 = true;

			if (cond1 && cond2) return true;
		}
		return false;
	}

	static boolean fourteen(InputVariables globals, Parameters params) {
		return false;
	}

}
