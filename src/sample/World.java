package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;
import java.lang.*;

public class World{

    private Antena antena1,antena2,antena3;
    private Robot robot;
    final int width = 400;
    final  int height = 400;
    final int scale = 5;
    final int gridWidth = width/scale;
    final int gridHeight = height/scale;

    public World() {

        int xR;
        int yR;
        int xA1;
        int yA1;
        int xA2;
        int yA2;
        int xA3;
        int yA3;

        final int margin=10;
        xA1=getRandomizedLocation(gridWidth-margin);
        yA1=getRandomizedLocation(gridHeight-margin);

        xA2=getRandomizedLocation(gridWidth-margin);
        while (xA2==xA1 || Math.abs(xA2-xA1)==margin){
            xA2=getRandomizedLocation(gridWidth-margin);
        }

        yA2=getRandomizedLocation(gridHeight-margin);
        while (yA2==yA1 || Math.abs(yA2-yA1)==margin){
            yA2=getRandomizedLocation(gridWidth-margin);
        }

        xA3=getRandomizedLocation(gridWidth-margin);
        while (xA3==xA2 || xA3==xA1 || Math.abs(xA3-xA2)==margin || Math.abs(xA3-xA1)==margin){
            xA3=getRandomizedLocation(gridWidth-margin);
        }

        yA3=getRandomizedLocation(gridHeight-margin);
        while (yA3==yA2 || yA3==yA1 || Math.abs(yA3-yA2)==margin || Math.abs(yA3-yA1)==margin){
            yA3=getRandomizedLocation(gridHeight-margin);
        }

        xR=getRandomizedLocation(gridWidth-margin);
        while (xR==xA1 || xR==xA2 || xR==xA3 || Math.abs(xR-xA1)==margin || Math.abs(xR-xA2)==margin || Math.abs(xR-xA3)==margin){
            xR=getRandomizedLocation(gridWidth-margin);
        }

        yR=getRandomizedLocation(gridHeight-margin);
        while (yR==yA1 || yR==yA2 || yR==yA3 || Math.abs(yR-yA1)==margin || Math.abs(yR-yA2)==margin || Math.abs(yR-yA3)==margin){
            yR=getRandomizedLocation(gridWidth-margin);
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

    public int getRandomizedLocation(int bound){
        Random generator = new Random();
        int location = generator.nextInt(bound);
        while(location==0){
            location=generator.nextInt(bound);
        }
        return location;
    }

    public boolean isRobotInside() {
        return  robot.inside(antena1,antena2,antena3);
    }

}
