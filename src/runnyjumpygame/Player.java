/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.image.BufferedImage;
/**
 *
 * @author logan
 */
public class Player extends AnimatedSprite{
    
    //This int tells us how far we're goig to move each frame when we're moving
    private int speed, jumpSpeed, jumpHeight, airTime;
    
    private enum JumpState { JUMPING, FALLING, GROUND }
    
    private enum MoveState { LEFT, RIGHT, STATIONARY }
    
    JumpState jumpState;
    
    MoveState moveState;
    
    public Player(BufferedImage image, int x, int y, int width, int height,
            int frames) {
        
        super(image, x, y, width, height, frames);
        
        speed = 3;
        jumpSpeed = 9;
        jumpHeight = 25;
        airTime = 0;
        jumpState = JumpState.FALLING;
        moveState = MoveState.STATIONARY;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    //This handles all our movement, including checking collision to make sure
    //we don't phase through any objects. We pass in the level, which is a
    //collection of all the 
    public void move(Level l){
        
        /*This is a test movement scheme that allows free movement in 3D space.
        if (Board.Direction.LEFT.isPressed()){
            x -= speed;
        }
        
        if (Board.Direction.RIGHT.isPressed()){
            x += speed;
        }
        
        if (Board.Direction.UP. isPressed()){
            y -= speed;
        }
        
        if (Board.Direction.DOWN.isPressed()){
            y += speed;
        }*/
        
        switch (jumpState){
            
            case FALLING:
                
                if (l.collides(getBounds())){
                    jumpState = JumpState.GROUND;
                    break;
                }
                
                this.y += jumpSpeed;
                
                break;
                
            case JUMPING:
                                
                if (airTime < jumpHeight && Board.Direction.UP.isPressed()){
                    
                    y -= jumpSpeed;
                    airTime++;
                    
                } else {
                    jumpState = JumpState.FALLING;
                    airTime = 0;
                }          
                
                break;
                
            case GROUND:
                              
                if (Board.Direction.UP.isPressed()){
                    
                    jumpState = JumpState.JUMPING;
                }
                
                if (!l.collides(getBounds())){
                    jumpState = JumpState.FALLING;
                }
                
                break;
            
        }            
                
        //This moves the player left and right. If we're in our play area (the 
        //on screen that the player sprite can move in) and we press left or 
        //right we move the player, if we're at the edge of the play area we
        //scroll the level instead.
        if (Board.Direction.LEFT.isPressed() 
                && !getBounds().intersects(l.outLeft())){
            
            //if (jumpState == JumpState.JUMPING
            //        && l.collides(getBounds())){
            //    x += speed;
            //}
            
            x -= speed;
        }
        
        if (Board.Direction.LEFT.isPressed() 
                && getBounds().intersects(l.outLeft())){
            l.scroll(speed);
        }
        
        if (Board.Direction.RIGHT.isPressed() 
                && !getBounds().intersects(l.outRight())){
            
            //if (jumpState == JumpState.JUMPING
            //        && l.collides(getBounds())){
            //    x -= speed;
            //}
                
            x += speed;
        }
        
        if (Board.Direction.RIGHT.isPressed() 
                && getBounds().intersects(l.outRight())){
            l.scroll(-speed);
        }
    }
}