import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Ball extends JComponent implements ActionListener, MouseListener{
    Timer tm = new Timer(2, this); //sets time for when we want the object to be repainted, ex. every 2 miliseconds the object is repainted
    int x = 0, velX = 2; //variable x is for the position, velX is the the velocity of the object

    public static void main(String[] args) {
        JFrame frame = new JFrame("Train Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.add(new Ball());
        frame.setVisible(true);
    }

    //This class paints the object , the timer is called in this class to set the timer for when we want the object repainted.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN); //sets the color
        g.fillRect(x, 30, 50, 30); //sets the size?

        tm.start();//starts the timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x < 0 || x > 750) // if object is in this range, then move it to the left
            velX =- velX;
        x += velX; // moves the object to the right
        repaint(); //repaints the object according to the Timer class.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //invoked when a mouse button has been clicked -> pressed and released

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //invoked when a mouse button has been pressed

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //invoked when a mouse button has been released

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //invoked when the mouse has entered the area of the object

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //invoked when the mouse has exited the area of the object

    }
}

