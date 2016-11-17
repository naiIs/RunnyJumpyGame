/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
/**
 *
 * @author logan
 */
public class Player extends AnimatedSprite{
    
    //This int tells us how far we're goig to move each frame when we're moving
    private int speed, jumpSpeed, fallSpeed, jumpHeight, airTime;
    
    private enum JumpState { JUMPING, FALLING, GROUND }
    
    private enum MoveState { LEFT, RIGHT, STATIONARY }
    
    JumpState jumpState;
    
    MoveState moveState;
    
    public Player(BufferedImage image, int x, int y, int width, int height,
            int frames) {
        
        super(image, x, y, width, height, frames);
        
        speed = 3;
        jumpSpeed = 9;
        fallSpeed = 9;
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
    
    public boolean isJumping(){
        
        if(jumpState == JumpState.JUMPING){ return true; }
        
        return false;
    }
    
    public boolean isFalling(){
        
        if(jumpState == JumpState.FALLING){ return true; }
        
        return false;
    }
    
    public boolean isGrounded(){
        
        if(jumpState == JumpState.GROUND){ return true; }
        
        return false;
    }
    
    public boolean movingLeft(){
        
        if(moveState == MoveState.LEFT){ return true; }
        
        return false;
    }
    
    public boolean movingRight(){
        
        if(moveState == MoveState.RIGHT){ return true; }
        
        return false;
    }
    
    public boolean movingStationary(){
        
        if(moveState == MoveState.STATIONARY){ return true; }
        
        return false;
    }
    
    public boolean landsOn(Rectangle r){
        
        if(r.intersects(getBounds())){
            y = (int)r.getY() + height;
            return true;
        }        
        
        return false;
    }
    
    //This handles all our movement, including checking collision to make sure
    //we don't phase through any objects. We pass in the level, which is a
    //collection of all the 
    public void move(Level l, Enemies e){
        
        /*
        //This is a test movement scheme that allows free movement in 3D space.
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
                
                this.y += fallSpeed;
                
                if (l.collides(getBounds())){
                    jumpState = JumpState.GROUND;
                }
                
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
            e.scroll(speed);
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
            e.scroll(-speed);
        }
    }
}