package sample;

import java.util.Random;
import java.lang.*;

class World{

    private Antena antena1,antena2,antena3;
    private Robot robot;

    private final int width = 400;
    private final  int height = 400;

    final int scale = 5;

    private final int gridWidth = width/scale;
    private final int gridHeight = height/scale;

    World() {

        int xR;
        int yR;
        int xA1;
        int yA1;
        int xA2;
        int yA2;
        int xA3;
        int yA3;

        //dopuszcza możliwość że x1=x2, powtarza losowanie jeśli wylosowane punkty są takie same lub się częściowo pokrywają, mogą się stykać
        //losowaniu trzeciej anteny sprawdza czy nie jest ona współliniowa z resztą anten -> zawsze będziemy mieć trojkata

        xA1=getRandomizedLocation(gridWidth);
        yA1=getRandomizedLocation(gridHeight);


        xA2=getRandomizedLocation(gridWidth);
        yA2=getRandomizedLocation(gridHeight);
        while ( isSame(xA1,yA1,xA2,yA2)|| checkDistance(xA1,yA1,xA2,yA2)){
            xA2=getRandomizedLocation(gridWidth);
            yA2=getRandomizedLocation(gridWidth);
        }


        xA3=getRandomizedLocation(gridWidth);
        yA3=getRandomizedLocation(gridHeight);
        while (isSame(xA3,yA3,xA2,yA2) || isSame(xA1,yA1,xA3,yA3) || checkDistance(xA3,yA3,xA2,yA2)|| checkDistance(xA1,yA1,xA3,yA3) || isCollinear(xA1,yA1,xA2,yA2,xA3,yA3)){
            xA3=getRandomizedLocation(gridWidth);
            yA3=getRandomizedLocation(gridHeight);

        }


        xR=getRandomizedLocation(gridWidth);
        yR=getRandomizedLocation(gridHeight);
        while (isSame(xR,yR,xA1,yA1) || isSame(xR,yR,xA2,yA2) || isSame(xR,yR,xA3,yA3) || checkDistance(xR,yR,xA1,yA1) || checkDistance(xR,yR,xA2,yA2) || checkDistance(xR,yR,xA3,yA3)){
            xR=getRandomizedLocation(gridWidth);
            yR=getRandomizedLocation(gridHeight);
        }

/*
        int xR=30;
        int yR=30;
        int xA1=50;
        int yA1=10;
        int xA2=50;
        int yA2=35;
        int xA3=1;
        int yA3=20;
*/
        System.out.println("Antena1: " + xA1 + ", " + yA1);
        System.out.println("Antena2: " + xA2 + ", " + yA2);
        System.out.println("Antena2: " + xA3 + ", " + yA3);
        System.out.println("Robocik: "+ xR + ", "+yR);

        robot = new Robot(xR,yR);
        antena1=new Antena(xA1,yA1);
        antena2=new Antena(xA2,yA2);
        antena3=new Antena(xA3,yA3);
    }

    Antena getA1(){return antena1;}
    Antena getA2(){return antena2;}
    Antena getA3(){return antena3;}

    Robot getRobot(){return robot;}

    private boolean checkDistance(int x1, int y1, int x2, int y2){
        return (Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2))<=2);
    }

    private boolean isSame(int x1, int y1, int x2, int y2){
        return (x1==x2 && y1==y2);
    }

    private boolean isCollinear(int x1, int y1, int x2, int y2, int x3, int y3){
        double a = (double)(y1-y2)/(double)(x1-x2);
        double b = (double)y1 - (double)x1*a;
        return y3>a*(double)x3+b-scale && y3<a*(double)x3+b+scale;
    }


    private int getRandomizedLocation(int bound){
        final int margin = 1;
        Random generator = new Random();
        int location = generator.nextInt(bound-margin);
        while(location==0){
            location=generator.nextInt(bound);
        }
        return location;
    }

    boolean isRobotInside() {
        return  robot.inside(antena1,antena2,antena3);
    }

}
