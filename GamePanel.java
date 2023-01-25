import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{ //this class will run on a thread
    static final int GAME_WIDTH = 1000; //'static' shares this variable with all GamePanel instances, meaning you don't have to keep remaking this variable. final prevents the value from changing and makes it run faster.
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555)); //ping pong tables are 5ft x 9ft. game height will adjust accordingly. 5/9 = 0.5555, cannot return 0.
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image img;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Box ball;
    Score score;
    boolean isOver = false;
    GameOver gameOver;
    Frame frame;

    GamePanel() {
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);//when you press keys they will be 'focused'.
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall(){
        random = new Random();
        ball = new Box((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER); //randomly sets the ball on the y-axis

    }public void newPaddle(){
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT, 1);//PUTS THIS PADDLE IN THE MIDDLE OF THE FRAME - (X-AXIS)
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT, 2);//PUTS THIS PADDLE IN THE MIDDLE OF THE FRAME - (X-AXIS)

    }public void gameOver(){


    }public void paint(Graphics g){
        img = createImage(getWidth(), getHeight());
        graphics = img.getGraphics();
        draw(graphics);
        g.drawImage(img, 0, 0, this);
    }public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }public void move(){//makes the paddles move smoothly. iterate at the end of each loop in run().
        paddle1.move();
        paddle2.move();
        ball.move();
    }public void checkCollision(){
        //bounces the ball off the top and bottom window edges
        if(ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);// bounces the ball in the opposite y direction (reverse y velocity)
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //bounces ball off paddles
        if(ball.intersects(paddle1)) { //if object1(ball) collides with object2(paddle) then reverse the velocity off the ball(move it in the opposite direction)
            ball.xVelocity = Math.abs(ball.xVelocity);
            //increase velocity off the ball ones it bounces off the paddle
            ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;//optional
            else
                ball.yVelocity--;//increases the upwards Y velocity if yVelocity is negative
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)) { //if object1(ball) collides with object2(paddle) then reverse the velocity off the ball(move it in the opposite direction)
            ball.xVelocity = Math.abs(ball.xVelocity);
            //increase velocity off the ball ones it bounces off the paddle
            ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;//optional
            else
                ball.yVelocity--;//increases the upwards Y velocity if yVelocity is negative
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //stops paddles at window edges
        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) //for moving down
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) //for moving down
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        //give a player 1 point and creates new paddle and ball
        if(ball.x <= 0){
            score.player2++;
            newPaddle();
            newBall();
            System.out.println("PLAYER 2: "+score.player2);
        }if(ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddle();
            newBall();
            System.out.println("PLAYER 1: "+score.player1);
        }

    }
    public void run() {
        boolean isOver = false;
        //basic game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks; //nano seconds
        double delta = 0;
        //adjust how you want:
        while (score.player1 != 7 || score.player2 != 7) {
            long now = System.nanoTime();  //returns whatever the systems nano time is
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) { //if delta val is >=1 u gon move all ur components, check for collisions, and then repeat everything and -1 from delta.
                move();
                checkCollision();
                repaint();
                delta--;
                //System.out.println("TEST");
            }
            if(score.player1 == 7){
                JOptionPane.showMessageDialog(null, "GAME OVER: PLAYER 1 WINS");
                break;
            }
            if(score.player2 == 7){
                JOptionPane.showMessageDialog(null, "GAME OVER: PLAYER 2 WINS");
                break;
            }

        }
    }public class AL extends KeyAdapter{ //short for actionListener()
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

    }
}
