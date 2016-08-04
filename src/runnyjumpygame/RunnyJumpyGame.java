/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnyjumpygame;

import javax.swing.JFrame;

import java.awt.EventQueue;
        
/**
 *
 * @author logan
 */
public class RunnyJumpyGame extends JFrame {

    //A constructor for RJG that sets up the default behavior of the frame
    public RunnyJumpyGame() {
        
        add(new Board());
        
        //We don't want the user to resize things since that can mess with
        //the game.
        setResizable(false);
        
        //Pack makes the size of the frame equal to the size of the components
        //contained in the frame
        pack();
        
        //Sets the title of the JFrame
        setTitle("Runny Jumpy Game");
        
        //Makes the window pop up in the middle of the screen by default,
        //instead of up in the left corner
        setLocationRelativeTo(null);
        
        //Sets the program to close when the X is hit, not become hidden
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Here we're creating a new instance of the game and setting it to visible
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run(){
                JFrame rjg = new RunnyJumpyGame();
                rjg.setVisible(true);
            }
        });
    }
    
}
