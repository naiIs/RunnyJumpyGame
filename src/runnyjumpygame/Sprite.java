/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author logan
 */

//The sprite class is the basis of all drawn objects being animated on screen
public abstract class Sprite {
    
    //Define the image that will show the sprite and where it will
    //appear on screen
    protected BufferedImage image;
    protected int x, y;
    
    //Define the sprites bounds
    protected Rectangle bounds;
    
    //The width and height of the sprite. Note: not nessecarily the size of the 
    //sprite sheet.
    protected int width, height;
    
    
    //A default constructor 
    public Sprite(){
        
        image = null;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        bounds = new Rectangle ( 0, 0, 0, 0 );
        
    }
    
    public Sprite(BufferedImage image, int x, int y, int width, int height){
        
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle( x, y, width, height );
    }
    
    //Thid method draws the sprite
    public void draw(Graphics g) {
        
        g.drawImage(image, x, y, null);
    }
    
    //This method returns the sprite's bounds when called, updating if the sprite
    //has moved
    public Rectangle getBounds(){
        bounds.setLocation(x, y);
        return bounds;
    }
    
    //This gets a rectangle and then returns true or false if the rectangle we're
    //passing in intersects with our sprite
    public boolean collides(Rectangle r){
        getBounds();
        
        if(bounds.intersects(r)){
            return true;
        } else {
            return false;
        }
    }
    
}