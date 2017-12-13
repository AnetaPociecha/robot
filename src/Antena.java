import java.lang.*;


public class Antena {
    final private int A = 100;
    final private int n = 1;
    int x;
    int y;

    double getSignal(int xR,int yR){
        double distance;
        distance=Math.sqrt(Math.pow(xR-x,2)+Math.pow(yR-y,2));
        double RSS = A-10*n*Math.log(distance);
        return RSS;
    }
}
