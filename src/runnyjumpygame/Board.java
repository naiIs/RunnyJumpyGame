/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.io.IOException;
import java.io.File;

import java.util.Vector;

import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author logan
 */

//The main class of our game. We're extending a JPanel because it's a "generic
//space where stuff happend" and implementing ActionListener because we're
//using a timer event to fire and catch action events repeatedly to itterate
//the game loop
public class Board extends JPanel
        implements ActionListener {
    
    //constants to define the height and width of the main game's JPanel
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    
    //A constant to define the timer delay in MS, essentially how long the
    //game waits to execute each frame. Setting it to 33 runs the game at 
    //30 frames per second
    private final int DELAY = 18;
    
    //define the timer object that's going to iterate the game. This is a swing
    //timer, not a java.util timer
    private Timer timer;
    
    //An image to hold our moving sprite
    BufferedImage smiley;
    
    //A bunch of temporary testing variables
    public int a = 0;
    public Player mySprite;
    
    public Rectangle screenBottom;
    
    public Level level;
    
    public enum Direction { 
        LEFT    (KeyEvent.VK_LEFT), 
        RIGHT   (KeyEvent.VK_RIGHT), 
        UP      (KeyEvent.VK_UP), 
        DOWN    (KeyEvent.VK_DOWN); 
        
        private int key;
        private boolean pressed;
        
        Direction(int k){
            key = k;
            pressed = false;
        }
        
        public boolean isPressed(){
            return pressed;
        }
        
        public void press(){
            pressed = true;
        }
        
        public void release() {
            pressed = false;
        }
    };
    
    public Board() {
        
        //Sets up some basic properties of the game JPanel
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        //This creates our TAdapter object and implements it as Board's
        //prerred key listener, which gets input from the keyboard
        addKeyListener(new TAdapter());
        
        //calls a method to initialize the game to its basic state
        initGame();
    }
    
    //This method initializes the game to its basic state
    private void initGame() {
        
        //Create a timer object that's going to cause the game's logic to
        //fire multiple times a second. It fires every DELAY milliseconds
        timer = new Timer(DELAY, this);
        timer.start();
        
        //load an image from the game's root directory into our saved 
        //buffered image
        try {
            
            smiley = ImageIO.read(new File("smiley.png"));            
        } catch (IOException e) {
            
        }
        
        level = new Level();
        
        //Create a new sprite with the image that we just loaded
        //mySprite = new Sprite(smiley, 90, 90);
        //mySprite = new Player(smiley, 190, 190);
        mySprite = new Player(smiley, 40, 40, 30, 30, 4);
        
        screenBottom = new Rectangle(0, 350, B_WIDTH, 1);
        
        level.add(screenBottom);
        
        System.out.println("lolstuff");
    }
    
    //This paints the images, first by calling the super class's paintComponent
    //method to paint all the default stuff, then by calling our doDrawing
    //method to paint all the stuff that we want painted
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);
    }
    
    //this is a function where we have our graphics object draw all of our
    //custom content.
    private void doDrawing(Graphics g) {
        
        mySprite.draw(g);
    }
    
    //This catches the action event that the timer fires over and over.
    //All the code that should be executed each frame is here.
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //movePlayer();
        mySprite.move(level);
            
        checkCollision();
        
        repaint();
    }
    
    public void checkCollision(){
        
        if (level.collides(mySprite.getBounds())){
            System.out.println("intersects");
        }
        //if (mySprite.collides(screenBottom)){
        //    System.out.println("intersects");
        //}
    }
        
    private void movePlayer(){
        
        int x = 0, y = 0;
        
        if((!mySprite.collides(screenBottom)) && (!Direction.UP.isPressed())){
            y++;
        }
        
        if(mySprite.collides(screenBottom)){
            y = 0;
        }
        
        if (Direction.UP.isPressed()){
            //mySprite.jump();
        }
        
        if (Direction.LEFT.isPressed()){
            x--;
        }
        
        if (Direction.RIGHT.isPressed()){
            x++;
        }
        
        //mySprite.move(x, y);
    }
    
    //This is a sub class that records our key presses. We're using this sub-
    //class because we only care about keyPressed and keyReleased, not
    //keyTyped
    private class TAdapter extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_RIGHT){
                Direction.RIGHT.press();
            }
            
            if (key == KeyEvent.VK_LEFT){
                Direction.LEFT.press();
            }
            
            if (key == KeyEvent.VK_UP){
                Direction.UP.press();
            }
            
            if (key == KeyEvent.VK_DOWN){
                Direction.DOWN.press();
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_RIGHT){
                Direction.RIGHT.release();
            }
            
            if (key == KeyEvent.VK_LEFT){
                Direction.LEFT.release();
            }
            
            if (key == KeyEvent.VK_UP){
                Direction.UP.release();
            }
            
            if (key == KeyEvent.VK_DOWN){
                Direction.DOWN.release();
            }
        }
    }
}