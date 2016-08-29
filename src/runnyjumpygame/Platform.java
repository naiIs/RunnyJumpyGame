/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author logan
 */
public class Platform extends Sprite{
    
    public Platform(BufferedImage image, int x, int y, int width, int height){
        super(image, x, y, width, height);
    }
    
    public void scroll(int m){
        x += m;
    }    
    
    /*public boolean bumpTop(Rectangle r){
        if (r.intersects(getBounds()) ){
            return true;
        }
        
        return false;
    }*/
    
    /*public boolean bumpLeft(Rectangle r){
        
        if(r.getX() + r.getWidth() >= x 
                && (r.getY() + r.getHeight() >= y && r.getY() <= y + height)){
            
            return true;
            
        }
        
        return false;
    }
    
    public boolean bumpRight(Rectangle r){
        
        if(r.getX() <= x + width
                && (r.getY() <= y + height && r.getY() + r.getHeight() >= y)){
            return true;
        }
        
        return false;
    }
    
    public boolean bumpTop(Rectangle r){
        
        if (r.getY() + r.getHeight() >= y
                && (r.getX() <= x + width && r.getX() + r.getWidth() >= x)){
            
            return true;
        }
        
        return false;
    }
    
    public boolean bumpBot(Rectangle r){
        
        if (r.getY() <= y + height
                && (r.getX() <= x + width && r.getX() + r.getWidth() >= x)){
            return true;
        }
        
        return false;
    }*/
}
