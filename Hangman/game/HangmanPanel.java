/*
* CREATED BY NAHUEL TELLECHEA FREIRE
*/

package game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class HangmanPanel extends JPanel {
    
    private int limb = -1; 

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        
        // Draw the base wider
        g.fillRect(30, 150, 120, 5);
        
        // Draw the vertical support taller
        g.fillRect(90, 50, 5, 100);
        
        // Draw the crossbar longer
        g.fillRect(80, 50, 90, 5);
        
        // Draw the rope
        g.drawLine(145, 50, 145, 70);

        // Call the 'person' method to draw the figure based on the value of limb
        if (limb >= 0 && limb <= 6) {
            person(g, limb);
        }
    }

    // Method that draws a figure based on the value of limb
    public void person(Graphics graphics, int limb) {
        switch(limb) {
            case 0:
                graphics.drawOval(135, 70, 20, 20); // Head (outline)
                break;
            case 1:
                graphics.drawOval(135, 70, 20, 20); // Head (outline)
                graphics.drawLine(145, 90, 145, 125); // Thinner body
                break;
            case 2:
                graphics.drawOval(135, 70, 20, 20); // Head (outline)
                graphics.drawLine(145, 90, 145, 125); // Thinner body
                graphics.drawLine(145, 95, 130, 110); // Thinner left arm
                break;
            case 3:
                graphics.drawOval(135, 70, 20, 20); // Head (outline)
                graphics.drawLine(145, 90, 145, 125); // Thinner body
                graphics.drawLine(145, 95, 130, 110); // Thinner left arm
                graphics.drawLine(145, 95, 160, 110); // Thinner right arm
                break;
            case 4:
                graphics.drawOval(135, 70, 20, 20); // Head (outline)
                graphics.drawLine(145, 90, 145, 125); // Thinner body
                graphics.drawLine(145, 95, 130, 110); // Thinner left arm
                graphics.drawLine(145, 95, 160, 110); // Thinner right arm
                graphics.drawLine(145, 125, 155, 145); // Thinner right leg
                break;
            case 5:
                graphics.drawOval(135, 70, 20, 20); // Head (outline)
                graphics.drawLine(145, 90, 145, 125); // Thinner body
                graphics.drawLine(145, 95, 130, 110); // Thinner left arm
                graphics.drawLine(145, 95, 160, 110); // Thinner right arm
                graphics.drawLine(145, 125, 155, 145); // Thinner right leg
                graphics.drawLine(145, 125, 135, 145); // Thinner left leg
                break;
            case 6:
                graphics.drawOval(135, 70, 20, 20); // Head (outline)
                graphics.drawLine(145, 90, 145, 125); // Thinner body
                graphics.drawLine(145, 95, 130, 110); // Thinner left arm
                graphics.drawLine(145, 95, 160, 110); // Thinner right arm
                graphics.drawLine(145, 125, 155, 145); // Thinner right leg
                graphics.drawLine(145, 125, 135, 145); // Thinner left leg
                graphics.drawLine(125, 92, 165, 92); // Line crossing the neck
                break;
        }
    }

    // Method to set the value of limb
    public void setLimb(int limb) {
        this.limb = limb;
        repaint(); // Redraw the panel after changing the value
    }
}