package blackhole;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Cristian
 */
public class BlackHole {

    private int posX;
    private int posY;
    private int width;
    private int height;
    private int density;

    public BlackHole(int posX, int posY) {
        this.posX = 397;
        this.posY = 147;
        this.width = 278;
        this.height = 257;
    }    
    
    public void draw(Graphics g,int r){
        Graphics2D g2d = (Graphics2D)g;
        drawCenteredCircle(g2d, this.posX, this.posY, r);
    }

    public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        
        x = x - (r / 2);
        y = y - (r / 2);
        g.fillOval(x, y, r, r);        
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

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getWidth() {
        return width;
    }
    
    public Rectangle getRect(){
        return new Rectangle(posX, posY, width, height);
    }
    
    public Rectangle getCenterRect(){
        return new Rectangle((int)getRect().getCenterX()-50, (int)getRect().getCenterY()-50, 100, 100);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
