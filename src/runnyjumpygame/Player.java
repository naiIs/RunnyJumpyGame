/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
/**
 *
 * @author logan
 */
public class Player extends Sprite{
    
    //This is the number of frames on the sprite sheet, and which frame we're 
   //currently displaying
    private int frames, currentFrame;
    
    //This is the delay between frames, so that sprites can be animated at a
    //different frame rate thant he base game timer.
    private int frameDelay, delayCount;
    
    //This int tells us how far we're goig to move each frame when we're moving
    private int speed, jumpSpeed, jumpHeight, airTime;
    
    private enum MoveState { JUMPING, FALLING, GROUND }
    
    MoveState moveState;
    
    public Player() {
        super();
        
        this.width = 0;
        this.height = 0;
        this.frames = 0;
        this.currentFrame = 0;
        this.frameDelay = 9;
        delayCount = 0;
        speed = 5;
        bounds = new Rectangle(0, 0, 0, 0);
    }
    
    public Player(BufferedImage image, int x, int y, int width, int height,
            int frames) {
        
        super(image, x, y, width, height);
        
        this.frames = frames;
        currentFrame = 0;
        frameDelay = 15;
        delayCount = 0;
        speed = 3;
        jumpSpeed = 6;
        jumpHeight = 44;
        airTime = 0;
        moveState = MoveState.FALLING;
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
    
    //This handles all our movement, including checking collision to make sure
    //we don't phase through any objects. We pass in the level, which is a
    //collection of all the 
    public void move(Level l){
        
        switch (moveState){
            
            case FALLING:
                
                if (l.collides(getBounds())){
                    moveState = MoveState.GROUND;
                    break;
                }
                
                this.y += jumpSpeed;
                
                break;
                
            case JUMPING:
                
                if (airTime < jumpHeight && Board.Direction.UP.isPressed()){
                    y -= jumpSpeed;
                    airTime++;
                } else {
                    moveState = MoveState.FALLING;
                    airTime = 0;
                }          
                
                break;
                
            case GROUND:
                              
                if (Board.Direction.UP.isPressed()){
                    moveState = MoveState.JUMPING;
                }
                
                if (!l.collides(getBounds())){
                    moveState = MoveState.FALLING;
                }
                
                break;
            
        }
        
        if (Board.Direction.LEFT.isPressed()){
            x -= speed;
        }
        
        if (Board.Direction.RIGHT.isPressed()){
            x += speed;
        }
    }
}