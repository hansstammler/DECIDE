package decide;
public class InputVariables {
    int NUMPOINTS;
    Point[] POINTS;
    Parameters PARAMETERS;
    LogicalConnectorMatrix LCM;
    boolean[] PUV;

    /**
     * This initializes some deterministic instance of InputVariables
     */
    public void initialize(){
        NUMPOINTS = 50;

        POINTS = new Point[NUMPOINTS];
        for(int i = 0; i < NUMPOINTS; i++) POINTS[i] = new Point(i, i);

        PARAMETERS = new Parameters();
        PARAMETERS.initialize();

        LCM = new LogicalConnectorMatrix('d');
        
        PUV = new boolean[15];
        for(int i = 0; i < 15; i++) PUV[i] = (i % 2 == 0);
    }
}
