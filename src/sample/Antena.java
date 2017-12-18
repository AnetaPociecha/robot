
package sample;

import java.lang.*;

public class Antena {
    final private int A = 100;
    final private int n = 1;
    private int xA;
    private int yA;
    public int getX() {return xA;}
    public int getY() {return yA;}
    public Antena(int xA, int yA) {
        this.xA=xA;
        this.yA=yA;
    }

    double getSignal(int xR,int yR){
        double distance;
        distance=Math.sqrt(Math.pow(xR-xA,2)+Math.pow(yR-yA,2));
        double RSS = A-10*n*Math.log(distance+0.1);
        return RSS;
    }
}
