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
public class Hostile extends AnimatedSprite{
    
    private int speed;
    
    public Hostile(BufferedImage image, int x, int y,
            int width, int height, int frames){
        
        super(image, x, y, width, height, frames);
        
        speed = 1;
        
    }
    
    public void scroll(int m){
        x += m;
    }
    
    public void move(Player p){
        
        if (y < p.getY()){
            y += speed;
        }
        
        if (y > p.getY()){
            y -= speed;
        }
        
        if (x < p.getX()){
            x += speed;
        }
        
        if (x > p.getX()){
            x -= speed;
        }
    }
}
