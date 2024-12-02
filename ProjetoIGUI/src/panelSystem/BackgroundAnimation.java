package panelSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BackgroundAnimation extends JPanel implements Runnable {
	
    private Graphics2D g2d;
    private final Random rand = new Random();
    private long speed = 1000;
    private boolean running = true;
    
    short optionForStandard;

    public BackgroundAnimation() {
    
    	optionForStandard = 1;
    	
    	Thread animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    public void paint(Graphics g) {
    	
        super.paint(g);
        
        g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int centerX = this.getWidth();
    	int centerY = this.getHeight();
    	int limit = rand.nextInt(200);
        
        for (int i = 0; i < limit; i++) { 
        	
        	if (optionForStandard == 1) {
        		g2d.drawLine(rand.nextInt(centerX), rand.nextInt(centerY),
            			 rand.nextInt(centerX), rand.nextInt(centerY));
        		continue;
        	}
        	
        	if (optionForStandard == 2) {
        		g2d.drawOval(rand.nextInt(centerX), rand.nextInt(centerY),
              			 rand.nextInt(centerX), rand.nextInt(centerY));
        		continue;
        	}
        	
        	if (optionForStandard == 3) {
        		g2d.drawRect(rand.nextInt(centerX), rand.nextInt(centerY), centerX, centerY);
        	}
        		
        }
    }
    	
    @Override
    public void run() {
    
    	while (running) {
    		
    		repaint();

            try 
            {
                Thread.sleep(speed);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
        
    }
    
    public void setBackgroundColor(Color backgroundColor) {
    	setBackground(backgroundColor);
    }
    
    public void setAnimationColor(Color animationColor) {
    	setForeground(animationColor);
    }
    
    public void setSpeed(long speed) {
    	this.speed = speed;
    }
    
    public void configDefaultAnimation(short optionForStandard) {
    	this.optionForStandard = optionForStandard;
    }
    
    public void setRunning(boolean running) {
    	this.running = running;
    }
    public long getSpeed(){
        return this.speed;
    }
    
}