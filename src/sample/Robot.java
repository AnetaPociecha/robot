package sample;

public class Robot {

    int xR;
    int yR;

    public Robot(int xR, int yR) {
        this.xR=xR;
        this.yR=yR;
    }

    double readSignal(Antena antena){
        return antena.getSignal(xR,yR);
    }

    public void moveRight() {
        xR++;
    }
    public void moveLeft() {
        if(xR!=0) xR--;
    }
    public void moveForward() {
        yR++;
    }
    public void moveBack() {
        if(yR!=0) yR--;
    }
}
