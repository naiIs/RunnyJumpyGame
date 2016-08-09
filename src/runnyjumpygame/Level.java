/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.Rectangle;

import java.util.Vector;
/**
 *
 * @author logan
 */
//This is an object that represents the interactable portion of the screen that
//the player and enemy sprites move around. Basically it's a container for 
//the background and walls of the play space.
public class Level {
    
    private Vector<Rectangle> floors;
    
    Level(){
        floors = new Vector<Rectangle>();
    }
    
    public void add (Rectangle r){
        floors.add(r);
    }
    
    public boolean collides (Rectangle r){
        
        for (Rectangle rect : floors){
            if (rect.intersects(r)){
                return true;
            }
        }
        
        return false;
    }
}
