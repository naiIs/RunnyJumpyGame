/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;

import java.util.Vector;
/**
 *
 * @author logan
 */
//This is an object that represents the interactable portion of the screen that
//the player and enemy sprites move around. Basically it's a container for 
//the background and walls of the play space.
public class Level {
    
    private Rectangle leftBound, rightBound;
    private Vector<Platform> platforms;
    private Point origin;
    
    Level(){
        platforms = new Vector<Platform>();
        leftBound = new Rectangle( 0, 0, 100, 400);
        rightBound = new Rectangle ( 310, 0, 90, 400);
        origin = new Point(0,0);
    }
    
    public void addPlatform (Platform r){
        platforms.add(r);
    }

    public void checkCollision(Player p){
        
        
        for (Platform plat : platforms){
            p.landsOn(plat.getBounds());
        }
    }
    
    public boolean collides (Rectangle r){
        
        for (Platform sprt : platforms){
            if (sprt.collides(r)){
                return true;
            }
        }
        
        return false;
    }
    
    public Rectangle outRight(){
        return rightBound;
    }
    
    public Rectangle outLeft(){
        return leftBound;
    }
    
    public Point getOrigin(){
        return origin;
    }
    
    public void scroll(int magnitude){
        
        origin.move((int)origin.getX() + magnitude, 0);
        for (Platform sprt : platforms){
            sprt.scroll(magnitude);
        }
    }
    
    public void draw(Graphics g){
        
        for (Platform sprt : platforms){
            sprt.draw(g);
        }
    }
}
