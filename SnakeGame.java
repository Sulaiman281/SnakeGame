import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class SnakeGame extends Application{

	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	private int borderLine = HEIGHT - 100;

	private Snake snake;
	private Food food;

	private enum Direction{
		stop,
		left,
		right,
		up,
		down,
	};
	private Direction dir;

	private boolean game_started = false;
	private int score;

	public void start(Stage stage) throws Exception {

		score = 0;
		dir = Direction.stop;
		snake = new Snake(WIDTH/2,HEIGHT/2,20);
		food = new Food();
        Canvas canvas = new Canvas(WIDTH,HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        canvas.setFocusTraversable(true);
        canvas.setOnMouseClicked(e -> game_started = true);
        canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    dir = Direction.up; break;
                    case DOWN:  dir = Direction.down; break;
                    case LEFT:  dir = Direction.left; break;
                    case RIGHT: dir = Direction.right; break;
                    case W: dir = Direction.up; break;
                    case S: dir = Direction.down; break;
                    case A: dir = Direction.left; break;
                    case D: dir = Direction.right; break;
                    case P: dir = Direction.stop; break;
                    case R: food.spawn(); break;
                }
            }
        });
        timeline.play();

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Snake Game");
        stage.show();
	}
    public void run(GraphicsContext gc){
    	gc.setFill(Color.web("#14374d"));
    	gc.fillRect(0,0,WIDTH,HEIGHT);
    	if(game_started){

    		gc.setFill(Color.web("#4ccf0a"));
    		gc.fillRect(snake.xPos(),snake.yPos(),snake.getRadius(),snake.getRadius());
	    	snake.update_tail();
	    	int index = 0;
    		for(Position p : snake.tail){
    			if(index %2 == 0)
    				gc.setFill(Color.web("#f4fc03"));
    			else 
    				gc.setFill(Color.web("#fcdb03"));
	    		gc.fillRect(p.x,p.y,snake.getRadius(),snake.getRadius());
	    		index++;
    		}
    		gc.setFill(Color.web("#4ccf0a"));
    		gc.fillRect(0,(float)borderLine,WIDTH,2);
    		gc.setFill(Color.web("#e81c0e"));
    		gc.fillOval(food.getX(),food.getY(),food.getRadius(),food.getRadius());
    		switch(dir){
    			case up: snake.up(); break;
    			case down: snake.down(); break;
    			case left: snake.left(); break;
    			case right: snake.right(); break;
    		}
    		if(snake.xPos() > WIDTH-snake.getRadius()) snake.setX(0);
    		if(snake.xPos() < 0) snake.setX(WIDTH-snake.getRadius());
    		if(snake.yPos() > borderLine-snake.getRadius()) snake.setY(0);
    		if(snake.yPos() < 0) snake.setY(borderLine-snake.getRadius());

    		if(snake.eat(food)){
    			food.spawn();
    			score++;
    			snake.grow();
    			Position t = new Position();
    			t.copy(snake.prev);
    			snake.tail.add(t);
    		}

    		gc.setFont(Font.font(18));
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.LEFT);
            gc.strokeText("Score: "+score, 30, borderLine+15);
            gc.strokeText("Food Pos: "+(int)food.getX()+","+(int)food.getY(),30,borderLine+30);
            gc.strokeText("Snake Pos: "+(int)snake.xPos()+","+(int)snake.yPos(),30,borderLine+45);

    	}else{
    		gc.setFont(Font.font(24));
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click to Start", WIDTH / 2, HEIGHT / 2);
    	}
	}

	public static void main(String... args){
		launch(args);
	}
}