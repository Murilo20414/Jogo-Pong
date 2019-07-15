package EstruturaJogo;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

public class Ball {

    private int xBall;
    private int yBall;
    private int dirX = 2;
    private int dirY = 2;
    private int sizeBall = 10;

    public Ball() {
        this.xBall = MainJogo.WIDTH / 2;
        this.yBall = MainJogo.HEIGHT / 2;
    }

    public void tick() {
        xBall += dirX;
        yBall += dirY;
        if (xBall > (MainJogo.WIDTH - sizeBall - 20)) {//Mudo minha direcao caso a bola atinja as extremidades x
            dirX *= -1;
        }

        if (xBall < 0) { //Toda vez que a bola passar pelo player executa este if
            yBall = MainJogo.HEIGHT / 2;
            xBall = MainJogo.WIDTH / 2;
            MainJogo.player.setLifePlayer(MainJogo.player.getLifePlayer() - 1);

        }

        if (MainJogo.player.getLifePlayer() == 0) {
            if (MainJogo.bestScore < MainJogo.score) { //Logica basica para definir um best score
                MainJogo.bestScore = MainJogo.score;
            }
            MainJogo.score = 0;
            int op = JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?");

            if (op == JOptionPane.OK_OPTION) {
                MainJogo.player.setLifePlayer(3);
            } else {
                JOptionPane.showMessageDialog(null, "Obrigado por jogar PONG!!");
                System.exit(0);
            }
        }

        if (yBall > (MainJogo.HEIGHT - sizeBall - 37) || yBall < 0) { //Mudo minha direcao caso a bola atinja as extremidades y
            dirY *= -1;
        }

        if (xBall <= (MainJogo.player.getxPlayer() + MainJogo.player.getWidthPlayer())) { //Logica por tr�s da colisao player - ball

            if ((yBall + sizeBall >= MainJogo.player.getyPlayer())
                    && (yBall <= MainJogo.player.getyPlayer() + MainJogo.player.getHeightPlayer())) {
                MainJogo.score += 1;
                dirX *= -1;
            }

        }

    }

    public void render(Graphics2D g) {//Desenha uma bola
        g.setColor(Color.white);
        g.fillOval(xBall, yBall, sizeBall, sizeBall);
    }
}
