package EstruturaJogo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainJogo extends JPanel implements Runnable, KeyListener {

    //Definicoes de painel
    private JFrame frame;
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 300;

    //Definicoes de jogo
    private Thread thread;
    private boolean isRunning;
    private int FPS;

    //Definicoes de objetos player e ball
    private Ball ball;
    public static Player player;

    private BufferedImage lifeImage;

    protected static int bestScore;
    protected static int score;

    public MainJogo() {
        try {
            lifeImage = ImageIO.read(new File("res/coracao2.png")); //Utilizando imagem para representar as vidas do player
        } catch (IOException e) {
            e.printStackTrace();
        }
        bestScore = 0;
        score = 0;
        ball = new Ball();
        player = new Player();

        load();

    }

    public synchronized void start() {
        thread = new Thread(this); //Passa para Thread o objeto
        isRunning = true;
        thread.start();
    }

    public synchronized void load() {

        frame = new JFrame("Jogo Pong");
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBounds(0, 0, WIDTH + 100, HEIGHT + 100);
        this.setBackground(Color.black);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);

        frame.setLayout(null);
        frame.add(this);
        frame.addKeyListener(this);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public static void main(String[] args) {
        new MainJogo().start();
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime(); //Pega o tempo atual em nano second
        final double amountOfTicks = 60.0; //Define uma quantidade de quadros
        final double ns = 1000000000 / amountOfTicks; //Divide a quantidade de quadros por 1s

        double delta = 0;
        int frames = 0; //FPS
        double timer = System.currentTimeMillis(); //Define uma variavel para mostrar o fps
        //em um certo tempo

        while (isRunning) {

            double now = System.nanoTime(); //Pega o tempo atual do come�o do loop
            delta += (now - lastTime) / ns; //Faz o tempo atual - o inicial e divide pelo n� de quadros/s
            lastTime = (long) now; //Define o tempo inicial como o atual

            if (delta >= 1) { //Quando o delta for maior q 1 (em nano second)

                tick(); //atualiza o jogo
                repaint(); //Renderiza
                frames++; //Incrementa o frame
                delta--; //deixa o delta pr�ximo de 0
            }

            if (System.currentTimeMillis() - timer >= 1000) { //Quando bater 1s eu mostro fps
                FPS = frames;
                frames = 0; //Zero o fps para a proxima execucao
                timer += 1000; //Incremento o timer com 1 segundo para a proxima execução
            }

        }

    }

    private void tick() {
        ball.tick();
        player.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create(); //Crio um lugar para desenhar

        g2d.setColor(Color.WHITE);
        g2d.drawString(Integer.toString(FPS), 15, 15); //Mostrando o numero de quadros/s

        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + Integer.toString(score), WIDTH - 70, 30);
        g2d.drawString("Best score: " + Integer.toString(bestScore), WIDTH - 100, 50);

        ball.render(g2d);
        player.render(g2d);

        for (int i = 0; i < player.getLifePlayer(); i++) { //Mostrando as vidas em imagem
            g2d.drawImage(lifeImage, WIDTH - 10 * (i + 5), 5, 10, 10, null);
        }

        g2d.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Logica para pressionar uma tecla
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.setUp(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Logica para soltar um tecla
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.setUp(false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }
}
