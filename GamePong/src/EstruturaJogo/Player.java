package EstruturaJogo;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {

    public int xPlayer;
    public int yPlayer;
    public int widthPlayer;
    public int heightPlayer;

    private boolean up;
    private boolean down;
    private int speed;

    protected static int lifePlayer;

    public Player() {
        this.xPlayer = 3;
        this.yPlayer = MainJogo.HEIGHT / 2; //Inicia no meio do eixo y
        this.widthPlayer = 10;
        this.heightPlayer = 50;
        this.up = false;
        this.down = false;
        this.speed = 3;
        lifePlayer = 3;
    }

    public int getLifePlayer() {
        return lifePlayer;
    }

    public void setLifePlayer(int lifePlayer) {
        this.lifePlayer = lifePlayer;
    }

    public int getxPlayer() {
        return xPlayer;
    }

    public void setxPlayer(int xPlayer) {
        this.xPlayer = xPlayer;
    }

    public int getyPlayer() {
        return yPlayer;
    }

    public void setyPlayer(int yPlayer) {
        this.yPlayer = yPlayer;
    }

    public int getWidthPlayer() {
        return widthPlayer;
    }

    public void setWidthPlayer(int widthPlayer) {
        this.widthPlayer = widthPlayer;
    }

    public int getHeightPlayer() {
        return heightPlayer;
    }

    public void setHeightPlayer(int heightPlayer) {
        this.heightPlayer = heightPlayer;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void render(Graphics2D g) {//Desenha um retangulo

        g.setColor(Color.red);
        g.fillRoundRect(xPlayer, yPlayer, widthPlayer, heightPlayer, 5, 5);
    }

    public void tick() {
        if (down && yPlayer + heightPlayer + 40 < MainJogo.HEIGHT) {
            yPlayer += speed; //Utilizo speed para definir um coeficiente, podendo definir quanto ele ira se mexer pelo eixo y 
        } else if (up && yPlayer > 0) {
            yPlayer -= speed;
        }

    }

}
