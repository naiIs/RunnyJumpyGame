/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.Rectangle;
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
    
    private Vector<Platform> platforms;
    
    Level(){
        platforms = new Vector<Platform>();
    }
    
    public void add (Platform r){
        platforms.add(r);
    }
    
    public boolean collides (Rectangle r){
        
        for (Platform sprt : platforms){
            if (sprt.collides(r)){
                return true;
            }
        }
        
        return false;
    }
    
    public void draw(Graphics g){
        
        for (Platform sprt : platforms){
            sprt.draw(g);
        }
    }
}
