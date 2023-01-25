import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Score extends Rectangle{
    static int GAME_WIDTH;
    static int GAME_HEIGTH;
    int player1;
    int player2;
    Score(int GAME_WIDTH, int GAME_HEIGTH){
        Score.GAME_HEIGTH = GAME_HEIGTH;
        Score.GAME_WIDTH = GAME_WIDTH;
    }
    public void draw(Graphics g){
        g.setColor((Color.cyan));
        g.setFont(new Font("Consolas", Font.BOLD, 40));

        g.drawLine(GAME_WIDTH/2,0, GAME_WIDTH/2, GAME_HEIGTH);
        //g.drawOval(GAME_WIDTH/8,GAME_HEIGTH/8, GAME_WIDTH/8, GAME_HEIGTH/8);

        g.drawString("PLAYER 1:", (GAME_WIDTH/2)-300, 50);
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
        g.drawString("PLAYER 2:", (GAME_WIDTH/2)+20, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+230, 50);
    }
}
