package decide;

import java.util.Random;

public class LogicalConnectorMatrix {
    
    enum LogicalConnector{
        NOTUSED,
        ANDD,
        ORR
    }

    LogicalConnector[][] logicalConnectorMatrix;
    
    /**
     * Constructor for LCM. Initialises each cell to NOTUSED
	 */
    public LogicalConnectorMatrix(){
        logicalConnectorMatrix = new LogicalConnector[15][15];
        for (int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                logicalConnectorMatrix[i][j] = LogicalConnector.NOTUSED;
            }
        }
    }
    /**
     * Constructor for LCM that accepts a flag char.
     * Flag changes generated LCM to random or some deterministic or NOTUSED.
     * @param flag 'r' for random. 'd' for deterministic. Any other char results in NOTUSED matrix.
     */
    public LogicalConnectorMatrix(char flag){
        logicalConnectorMatrix = new LogicalConnector[15][15];
        Random rand = new Random();
        int x;
        for (int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if (i == j) setLogicalConnector(i, j, LogicalConnector.ANDD);
                else{
                    if(flag == 'r') x = rand.nextInt(3);        //Random
                    else if (flag == 'd') x = (i*(j+1))%3;             //Deterministic, just some matrix
                    else x = 1;
                    switch (x){
                        case 0:
                            setLogicalConnector(i, j, LogicalConnector.ANDD);
                            break;
                        case 1:
                            setLogicalConnector(i, j, LogicalConnector.NOTUSED);
                            break;
                        case 2:
                            setLogicalConnector(i, j, LogicalConnector.ORR);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Changes the indices (x,y) and (y,x) in the LCM as the matrix is symmetrical.
     * Cells are set to logical connector lc
     * @param x integer from 0 to 14
     * @param y integer from 0 to 14
     * @param lc Logical connector, either ANDD, ORR or UNUSED
     */
    public void setLogicalConnector(int x, int y, LogicalConnector lc){
        logicalConnectorMatrix[x][y] = lc;
        logicalConnectorMatrix[y][x] = lc;
    }

    /**
     * Prints the LCM matrix, only used for debugging
     */
    public void print(){
        System.out.println("LIC    |0      |1      |2      |3      |4      |5      |6      |7      |8      |9      |10     |11     |12     |13     |14     ");
        System.out.println("_______________________________________________________________________________________________________________________________");
        for (int i = 0; i < 15; i++){
            System.out.print(i + "      ");
            if(i < 10) System.out.print(" ");
            for(int j = 0; j < 15; j++){
                switch (logicalConnectorMatrix[i][j]){
                    case NOTUSED:
                        System.out.print("NOTUSED");
                        break;
                    case ANDD:
                        System.out.print("ANDD   ");
                        break;
                    case ORR:
                        System.out.print("ORR    ");
                        break;
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    /**
     * Returns the logical connector that is in the cell (x,y) of the matrix. 
     * @param x x-axis
     * @param y y-axis
     * @return Logical connector, either ANDD, ORR or UNUSED
     */
    public LogicalConnector get(int x, int y){
        return logicalConnectorMatrix[x][y];
    }
    
    public LogicalConnector[] getRow(int x){
        return logicalConnectorMatrix[x];
    }
    



}
