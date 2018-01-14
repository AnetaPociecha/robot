
package sample;

import java.lang.*;

class Antena {
    final private int A = 100;
    final private int n = 1;
    private int xA;
    private int yA;
    int getX() {return xA;}
    int getY() {return yA;}
    Antena(int xA, int yA) {
        this.xA=xA;
        this.yA=yA;
    }

    /**
     *
     * @param xR pierwsza współrzędna badanego punktu
     * @param yR druga współrzędna badanego punktu
     * @return wartość sygnału w badanym punkcie
     */

    double getSignal(int xR,int yR){
        double distance;
        distance=Math.sqrt(Math.pow(xR-xA,2)+Math.pow(yR-yA,2));
        return  A-10*n*Math.log(distance+0.1);
    }
}
