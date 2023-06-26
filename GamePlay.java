/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;// intial game is false
    private int score = 0;
    private int totalBricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerx = 300;
    private int ballposx = 120;
    private int ballposy = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;
    public GamePlay()
    {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        Timer = new Timer(delay, this);
        Timer.start();
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.black);//set frame color to black
        g.fillRect(1,1 ,692,592);
        
        map.draw((Graphics2D) g);
        g.setColor(Color.yellow);// make a boundary
        g.fillRect(0,0, 692, 3);
        g.fillRect(0,0, 3, 592);
        g.fillRect(682,0, 3, 592);
        
        g.setColor(Color.white);// fonts appear in game
        g.setFont(new Font("adobe arabic", Font.BOLD, 50));
        g.drawString("" + score, 590, 40);
        
        g.setColor(Color.yellow);// make a slider
        g.fillRect(playerx, 550, 100, 10);
        
        g.setColor(Color.green);// make a ball
        g.fillOval(ballposx, ballposy, 20, 20);
        if(ballposy > 570)
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            
            g.setColor(Color.white);
            g.setFont(new Font("adobe arabic", Font.BOLD, 30));
            g.drawString(" GAME OVER -> Total Score : " + score, 190, 300);
            
            g.setFont(new Font("adobe arabic", Font.BOLD, 30));
            g.drawString(" Press Enter to Restart ", 190, 340);
        }
        if(totalBricks==0)// if there is no bricks that means game is over
        {
            play = false;
            ballXdir = -1;
            ballYdir = -2;
            
            g.setColor(Color.white);
            g.setFont(new Font("adobe arabic", Font.BOLD, 30));
            g.drawString(" GAME OVER -> Total Score : " + score, 190, 300);
            
            g.setFont(new Font("adobe arabic", Font.BOLD, 30));
            g.drawString(" Press Enter to Restart ", 190, 340);
        }
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Timer.start();
        if(play)// if ball intersect with slider then ball reflect
        {
            if(new Rectangle(ballposx, ballposy, 20, 20).intersects(new Rectangle(playerx, 550, 100, 10)))
            {
                ballYdir = -ballYdir;
            }
        A://for loop tag use for break the loop -> reference pointer
        for(int i=0;i<map.map.length;i++)
        {
            for(int j=0;j<map.map[0].length;j++)
            {
                if(map.map[i][j] > 0)
                {
                    int brickX = (j*map.brickWidth) + 80;
                    int brickY = (i*map.brickHeight) + 50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;
                    
                    Rectangle rect = new Rectangle(brickX,brickY,brickWidth, brickHeight);
                    Rectangle ballrect = new Rectangle(ballposx,ballposy,20,20);
                    Rectangle brickrect = rect;
                    
                    if(ballrect.intersects(brickrect))// if ball intersect with brick then ball reflect
                    {
                        map.setBricksValue(0, i, j);
                        totalBricks--;
                        score += 5;
                        if(ballposx + 19 <= brickrect.x || ballposx + 1 >= brickrect.x + brickWidth)
                        {
                            ballXdir = -ballXdir;
                        }
                        else
                        {
                            ballYdir = -ballYdir;
                        }
                        break A;
                    }
                }
            }
        }
        ballposx += ballXdir;// changes the direction of ball when ball hit boundary
        ballposy += ballYdir;
        if(ballposx < 0)//left
        {
            ballXdir = -ballXdir;
        }
        if(ballposy < 0)//top
        {
            ballYdir = -ballYdir;
        }
        if(ballposx > 670)//right
        {
            ballXdir = -ballXdir;
        }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerx>=600)
            {
                playerx = 600;
            }
            else
            {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
             if(playerx < 10)
            {
                playerx = 10;
            }
            else
            {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(play == false)
            {
                ballposx = 120;
                ballposy = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
               totalBricks = 21;
               playerx = 300;
               map = new MapGenerator(3, 7);
               repaint();
            }
        }
    }
   public void moveRight()
   {
      play = true;
      playerx += 35;
   }
   public void moveLeft()
   {
        play = true;
        playerx -= 35;
   }
    
}
