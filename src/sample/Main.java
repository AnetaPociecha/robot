package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {
    static World world = new World();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("World");
        Group root = new Group();
        Label label;
        Canvas canvas = new Canvas(world.width, world.height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        world.draw(gc);

        if(world.isRobotInside()){
            label = new Label("Robot is inside!");
        }else{
            label = new Label("Robot is outside!");
        }
        label.setFont(Font.font("Cambria", 22));
        root.getChildren().add(label);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
