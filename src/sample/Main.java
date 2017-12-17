package sample;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class Main extends Application {
    static World world = new World();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("World");

        Group root = new Group();
        Canvas canvas = new Canvas(world.width, world.height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        world.draw(gc);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        String screenText="";
        if(world.isRobotInside()){
            screenText = "Robot is inside";
        }else{
            screenText = "Robot is outside";
        }
        gc.strokeText(screenText,30,30,300);
    }

    public static void main(String[] args) {

        boolean check = world.isRobotInside(); //działa lepiej dla współrzędnych>10
        if(check) System.out.println("inside");
        else System.out.println("outside");

        launch(args);

    }

}
