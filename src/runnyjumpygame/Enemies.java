/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import java.awt.Graphics;

import java.util.Vector;
/**
 *
 * @author logan
 */
public class Enemies {
    
    private Vector<Hostile> hostiles;
    
    Enemies(){
        hostiles = new Vector<Hostile>();
    }
    
    public void addHostile(Hostile r){
        hostiles.add(r);
    }
    
    public void draw(Graphics g){
        
        for (Hostile h : hostiles){
            h.draw(g);
        }
    }
    
    public void move(Player p){
        
        for (Hostile h : hostiles){
            h.move(p);
        }
    }
    
    public void scroll(int m){
        
        for (Hostile h : hostiles){
            h.scroll(m);
        }
    }
    
    public boolean collides(Player p){
        boolean c = false;
        
        for (Hostile h : hostiles){
            if (h.collides(p.getBounds())){
                c = true;
            }
        }
        
        return c;
    }
}
