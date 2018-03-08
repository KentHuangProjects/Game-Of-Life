package ca.bcit.comp2526.a2b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

/**
 * TurnListener. Listen to the mouse click and then start conducting turns.
 * 
 * @author Kent, Huang
 * @version 1.0
 *
 */
public class TurnListener extends MouseAdapter implements ActionListener {
    
    private static final int INTERVAL = 10;
    
    private GameFrame gameFrame; // gameFrame that this TurnListener listens to.
    private Timer tm;
    
    /**
     * Construct a TurnListener.
     * 
     * @param gameFrame
     *            The specified GameFrame object to be set.
     */
    public TurnListener(final GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        tm = new Timer(INTERVAL, this);
    }

    /**
     * Start taking turns when mouse is clicked.
     */
    public void mouseClicked(final MouseEvent event) {
        
        if (tm.isRunning()) {
            tm.stop();
        } else {
            tm.start();
        }

    }

    @Override
    /**
     * Invoked when an action occurs.
     * @param The action event. 
     */
    public void actionPerformed(ActionEvent event) {
        gameFrame.takeTurn();

    }
}
