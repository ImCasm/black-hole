/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackhole;

import grafico.ViewPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Cristian
 */
public class Astro implements Runnable {

    private int posX;
    private int posY;
    private int size;
    int speed;
    private int widthPanel;
    private int heightPanel;
    private boolean isAlive;
    private boolean toLeft;
    private boolean toRight;
    private ViewPanel panel;
    private Thread t;

    public Astro(int posX, int posY, ViewPanel panel, boolean toRight) {
        this.toRight = toRight;
        this.toLeft = !toRight;
        this.posX = posX;
        this.posY = posY;
        this.panel = panel;
        this.size = 5;
        this.isAlive = true;
        this.speed = getSpeed();
        this.widthPanel = panel.getPreferredSize().width;
        this.heightPanel = panel.getPreferredSize().height;
        t = new Thread(this);
        t.start();
    }

    private int getSpeed() {
        return (int) (Math.random() * 20) + 1;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillOval(posX, posY, size, size);
    }

    private boolean inLimitX(int x) {
        if (toRight) {
            return (posX >= widthPanel);
        }
        return (posX <= 0);
    }
    
    private void setPosRandom(){
        if(toRight)posX = 0;
        if(toLeft)posX = widthPanel;
        posY = (int) (Math.random() * heightPanel) + 1;
    
    }

    public Rectangle getRect() {
        return new Rectangle(posX, posY, size, size);
    }

    public boolean inEventHorizont() {
        return getRect().intersects(panel.bh.getRect());
    }

    private void goToBlackHole() {
        double deltaX = panel.bh.getRect().getCenterX() - posX;
        double deltaY = panel.bh.getRect().getCenterY() - posY;
        float angle = (float) Math.atan2(deltaY, deltaX);

        posX += (speed*2) * Math.cos(angle);
        posY += (speed*2) * Math.sin(angle);
        
        if (getRect().intersects(panel.bh.getCenterRect())) {
            //isAlive = false;
            setPosRandom();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {

                if (inEventHorizont()) {
                    goToBlackHole();
                } else {
                    if (toRight) {
                        posX += speed;
                    }else{
                        posX -= speed;
                    }
                    
                    if (inLimitX(posX)) {
                        setPosRandom();
                    }
                }
                
                panel.repaint();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidthPanel() {
        return widthPanel;
    }

    public void setWidthPanel(int widthPanel) {
        this.widthPanel = widthPanel;
    }

    public int getHeightPanel() {
        return heightPanel;
    }

    public void setHeightPanel(int heightPanel) {
        this.heightPanel = heightPanel;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void getAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public ViewPanel getPanel() {
        return panel;
    }

    public void setPanel(ViewPanel panel) {
        this.panel = panel;
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isToLeft() {
        return toLeft;
    }

    public void setToLeft(boolean toLeft) {
        this.toLeft = toLeft;
    }

    public boolean isToRight() {
        return toRight;
    }

    public void setToRight(boolean toRight) {
        this.toRight = toRight;
    }
    
    
}
