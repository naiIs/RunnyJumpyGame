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
public class AnimatedSprite extends Sprite{
    
    //frames tells us the number of frames in our sprite sheet, currentFrame
    //tells us which frame we're currently rendering
    private int frames, currentFrame;
    
    //frameDelay is the delay before we move on to the next frame, that means 
    //that the sprite animation speed doesn't nessecarily need to match the
    //game loop speed. delayCount helps us count up to the frameDelay
    private int frameDelay, delayCount;
    
    public AnimatedSprite(BufferedImage image, int x, int y, 
            int width, int height, int frames){
        
        super (image, x, y, width, height);
        
        this.frames = frames;
        currentFrame = 0;
        frameDelay = 15;
        delayCount = 0;       
    }
    
    //Rather than the static draw method of Sprite here we're going to animate
    //the sprite by iterating throguh it's sprite sheet.
    @Override
    public void draw(Graphics g){
        
        //Here we draw a portion of the sprite sheet, placing it at x and y on
        //the screen and drawing a portion of the sprite sheet defined
        //by the current frame
        g.drawImage(image, 
                x, y, (x + width), (y + height),
                (currentFrame * width), 0, (currentFrame * width + width), height,
                null);
        
        //this is our logic to step through frames. We count frames until the
        //frame delay is up and then move to the next frame
        delayCount++;
        if (delayCount == frameDelay){
            currentFrame++;
            delayCount = 0;
        }
        
        if (currentFrame == frames) {
           currentFrame = 0;
        }
    }
}
