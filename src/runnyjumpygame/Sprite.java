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

//The sprite class is the basis of all moving objects being animated on screen
public class Sprite {
    
    //Define the image that will show the sprite and where it will
    //appear on screen
    protected BufferedImage image;
    protected int x, y;
    
    //A default constructor 
    public Sprite(){
        
        image = null;
        x = 0;
        y = 0;
    }
    
    public Sprite(BufferedImage image, int x, int y){
        
        this.image = image;
        this.x = x;
        this.y = x;
    }
    
    public void draw(Graphics g) {
        
        g.drawImage(image, x, y, null);
    }
    
}