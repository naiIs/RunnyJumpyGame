/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
/**
 *
 * @author logan
 */
public class Player extends Sprite{
    
    //defines how big the sprite itself is, although this doesn't reflect the
    //size of the sprite sheet.
    private int width, height;
    
    //This is the number of frames on the sprite sheet, and which frame we're 
   //currently displaying
    private int frames, currentFrame;
    
    //This is the delay between frames, so that sprites can be animated at a
    //different frame rate thant he base game timer.
    private int frameDelay, delayCount;
    
    //This enum is used to interperet input from the keyListener in Board.java
    public enum Direction { LEFT, RIGHT, UP, DOWN };
    
    //This int tells us how far we're goig to move each frame when we're moving
    private int speed;
    
    public Player() {
        super();
        
        this.width = 0;
        this.height = 0;
        this.frames = 0;
        this.currentFrame = 0;
        this.frameDelay = 9;
        delayCount = 0;
        speed = 5;
    }
    
    public Player(BufferedImage image, int x, int y, int width, int height,
            int frames) {
        
        super(image, x, y);
        
        this.width = width;
        this.height = height;
        this.frames = frames;
        currentFrame = 0;
        frameDelay = 9;
        delayCount = 0;
        speed = 5;
    }
    
    //This method covers all of our logic to draw the sprite.
    @Override
    public void draw(Graphics g){        
        
        //Here we draw a portion of the sprite sheet, placing it at x and y on
        //the screen and drawing a portion of the sprite sheet defined
        //by the current frame
        g.drawImage(image, 
                x, y, (x + width), (y + height), 
                (currentFrame * width), 0, (currentFrame * width + width), height,
                null);
        
        //this is our logic to step through frames. We count frames until the
        //frame delay is up and then move to the next frame
        delayCount++;
        if (delayCount == frameDelay){
            currentFrame++;
            delayCount = 0;
        }
        
        if (currentFrame == frames) {
           currentFrame = 0;
        }
    }
    
    public void move(Direction d){
        
        switch (d){
            
            case LEFT:
                x -= speed;
                break;
                
            case RIGHT:
                x += speed;
                break;
                
            case UP:
                y -= speed;
                break;
                
            case DOWN:
                y += speed;
                break;
                
            default:
                break;
        }
        
        /*if (d == d.LEFT){
            x--;
        }
        
        if (d == d.RIGHT){
            x++;
        }
        
        if (d == d.UP){
            y--;
        }
        
        if (d == d.DOWN){
            y++;
        }*/
    }
}