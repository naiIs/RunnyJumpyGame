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
//space where stuff happens" and implementing ActionListener because we're
//using a timer event to fire and catch action events repeatedly to itterate
//the game loop
public class Board extends JPanel
        implements ActionListener {
    
    //constants to define the height and width of the main game's JPanel
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    
    //A constant to define the timer delay in MS, essentially how long the
    //game waits to execute each frame.
    private final int DELAY = 10;
    
    //define the timer object that's going to iterate the game. This is a swing
    //timer, not a java.util timer
    private Timer timer;
    
    //The player object, which is moveable and interactable
    public Player player;
    
    //The level object, which is a collection of platforms that determine collision
    public Level level;
    
    //The Direction enum helps us know what state our keyboard is in.
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
        KeyAdapter t = new TAdapter();
        addKeyListener((KeyListener)t);
        
        //calls a method to initialize the game to its basic state
        initGame();
    }
    
    //This method initializes the game to its basic state
    private void initGame() {
        
        //Create a timer object that's going to cause the game's logic to
        //fire multiple times a second. It fires every DELAY milliseconds
        timer = new Timer(DELAY, this);
        timer.start();
        
        //Call loadLevel to populate the playspace
        loadLevel();
        
        //call loadPlayer to add our controllable sprite to the play space
        loadPlayer();
    }
    
    //This method loads an image and then creates a player object
    private void loadPlayer(){
                
        try {
            
            BufferedImage p = ImageIO.read(new File("dude.png"));
            
            player = new Player(p, 40, 40, 11, 45, 4);
        } catch (IOException e) {        }
    }
    
    //This method loads our level and populates our level object.
    private void loadLevel(){
                
        level = new Level();
        
        try {
            
            BufferedImage p = ImageIO.read(new File("bottom.png"));
            
            level.add(new Platform(p, 0, 375, B_WIDTH, 25));
            level.add(new Platform(p, 200, 200, B_WIDTH, 25));
        } catch (IOException e) {        }
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
        
        level.draw(g);
        
        player.draw(g);
    }
    
    //This catches the action event that the timer fires over and over.
    //All the code that should be executed each frame is here.
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Each game cycle we call the move method of our player, which reads
        //input from the keyboard and checks if we've colided with anything
        player.move(level);
            
        repaint();
    }
    
    //This is a sub class that records our key presses. We're using this sub-
    //class and not having Board implement KeyListener because we only care about
    //keyPressed and keyReleased, not keyTyped
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
            
            /*if (key == KeyEvent.VK_LEFT){
                ((Player)mySprite).move(Player.Direction.LEFT);
            }
            
            if (key == KeyEvent.VK_RIGHT){
                ((Player)mySprite).move(Player.Direction.RIGHT);
            }
            
            if (key == KeyEvent.VK_UP){
                ((Player)mySprite).move(Player.Direction.UP);
            }
            
            if (key == KeyEvent.VK_DOWN){
                ((Player)mySprite).move(Player.Direction.DOWN);
            }*/
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