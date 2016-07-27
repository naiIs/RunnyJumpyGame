/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.io.IOException;
import java.io.File;

import java.awt.event.InputEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author logan
 */
public class Board extends JPanel
        implements ActionListener {
    
    //constants to define the height and width of the main game's JPanel
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    
    //A constant to define the timer delay in MS, essentially how long the
    //game waits to execute each frame. Setting it to 33 runs the game at 
    //30 frames per second
    private final int DELAY = 33;
    
    //define the timer object that's going to iterate the game. This is a swing
    //timer, not a java.util timer
    private Timer timer;
    
    //An image to hold our moving sprite
    BufferedImage smiley;
    
    //A bunch of temporary testing variables
    public int a = 0;
    public Sprite mySprite;
    
    public Board() {
        
        //Sets up some basic properties of the game JPanel
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
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
        
        //Create a new sprite with the image that we just loaded
        //mySprite = new Sprite(smiley, 90, 90);
        //mySprite = new Player(smiley, 190, 190);
        mySprite = new Player(smiley, 40, 40, 30, 30, 4);
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
        g.drawImage(smiley, 20, 20, 35, 35, 0, 0, 15, 15, this);
        
        mySprite.draw(g);
    }
    
    //This catches the action event that the timer fires over and over.
    //All the code that should be executed each frame is here.
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}