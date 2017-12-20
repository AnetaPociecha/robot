package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Controller {

    @FXML
    Button button;
    @FXML
    Canvas canvas;
    @FXML
    Canvas textCanvas;

    public void handleButtonClick(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext gcText = textCanvas.getGraphicsContext2D();

        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        gcText.clearRect(0,0,textCanvas.getWidth(),textCanvas.getHeight());

        World world = new World();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(world.getA1().getX()*world.scale, world.getA1().getY()*world.scale, world.getA2().getX()*world.scale, world.getA2().getY()*world.scale);
        gc.strokeLine(world.getA2().getX()*world.scale, world.getA2().getY()*world.scale, world.getA3().getX()*world.scale, world.getA3().getY()*world.scale);
        gc.strokeLine(world.getA3().getX()*world.scale, world.getA3().getY()*world.scale, world.getA1().getX()*world.scale, world.getA1().getY()*world.scale);
        gc.strokeLine(0,0,0,canvas.getWidth());
        gc.strokeLine(0,0,canvas.getHeight(),0);
        gc.strokeLine(0,canvas.getWidth(),canvas.getHeight(),canvas.getWidth());
        gc.strokeLine(canvas.getHeight(),0,canvas.getHeight(),canvas.getWidth());
        gc.fillOval(world.getA1().getX()*world.scale-world.scale,world.getA1().getY()*world.scale-world.scale,2*world.scale,2*world.scale);
        gc.fillOval(world.getA2().getX()*world.scale-world.scale,world.getA2().getY()*world.scale-world.scale,2*world.scale,2*world.scale);
        gc.fillOval(world.getA3().getX()*world.scale-world.scale,world.getA3().getY()*world.scale-world.scale,2*world.scale,2*world.scale);
        gc.setFill(Color.RED);
        gc.fillOval(world.getRobot().getX()*world.scale-world.scale,world.getRobot().getY()*world.scale-world.scale,2*world.scale,2*world.scale);
        String screenText;
        if(world.isRobotInside()){
            screenText = "Robot is inside";
        }else{
            screenText = "Robot is outside";
        }
        gcText.setFont(new Font("Courier New", 35));
        gcText.strokeText(screenText,90,40,300);
    }
}
