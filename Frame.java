import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.Font.*;

public class Frame extends JFrame{
    Image image;
    Graphics graphics;
    Box player;
    Box badGuy;
    boolean gameOver;

    Frame() {
        player = new Box(100, 300, 50, 50, Color.GREEN);
        badGuy = new Box(400, 300, 50, 50, Color.MAGENTA);
        gameOver = false;

        new JFrame("UNCHY");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        //this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(new Color(123, 50, 250));
        this.addKeyListener(new AL());
    }

    public void paint(Graphics g){
        image = createImage(this.getWidth(), this.getHeight());
        graphics = image.getGraphics();
        g.drawImage(image, 0, 0, this);

        player.draw(g);
        badGuy.draw(g);

        if(gameOver){
            g.setColor(Color.cyan);
            g.setFont(new Font("MV Boli", Font.BOLD, 50));
            g.drawString("GAME OVER!", 50, 150);
        }
    }

    public void checkCollision(){
        if(player.intersects(badGuy)){
            gameOver = true;
            System.out.println("GAME OVER");
        }
    }

    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
            checkCollision();
            repaint();
        }
    }
}
