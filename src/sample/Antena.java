package sample;

import java.lang.*;

public class Antena {
    final private int A = 100;
    final private int n = 1;
    final int xA;
    int yA;

    public Antena(int xA, int yA) {
        this.xA=xA;
        this.yA=yA;
    }

    double getSignal(int xR,int yR){
        double distance;
        distance=Math.sqrt(Math.pow(xR-xA,2)+Math.pow(yR-yA,2));
        double RSS = A-10*n*Math.log(distance);
        return RSS;
    }
}
