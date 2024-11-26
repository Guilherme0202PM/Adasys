import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JogoNave {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();

        Sensores sensores = new Sensores(gameWindow);
        Fundo fundo = new Fundo(600, 600, "fundo.png");
        gameWindow.setFundo(fundo); // Define o fundo no GamePanel



        Nave nave = new Nave(100, 250, 100, 100,"nave.png", sensores, gameWindow);
        nave.adicionarListener();

        gameWindow.adicionarObjeto(nave);

        // Variáveis para geração de asteroides
        int totalRodadas = 10; // Total de rodadas
        final int[] rodadaAtual = {1}; // Rodada inicial
        final int[] asteroidesChegaram = {0}; // Contador de asteroides que chegaram na borda
        final int[] asteroidesGerados = {0}; // Contador de asteroides gerados na rodada atual
        int[] posicoesY = {100, 250, 400};
        Random random = new Random();

        // Rótulo para exibir o número de asteroides gerados e rodadas
        JLabel contadorLabel = new JLabel("Rodada: 1, Asteroides gerados: 0");
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contadorLabel.setForeground(Color.RED);
        gameWindow.addComponentToGamePanel(contadorLabel);

        // Rótulo para exibir o número de vidas
        JLabel vidasLabel = new JLabel("Vidas: 3");
        vidasLabel.setFont(new Font("Arial", Font.BOLD, 18));
        vidasLabel.setForeground(Color.GREEN);
        gameWindow.addComponentToGamePanel(vidasLabel);


        // Timer para gerenciar rodadas
        Timer geradorAsteroides = new Timer(2000, null);
        geradorAsteroides.addActionListener(e -> {
            if (rodadaAtual[0] <= totalRodadas) {
                // Verifica se todos os asteroides da rodada atual chegaram à borda
                if (asteroidesChegaram[0] >= rodadaAtual[0]) {
                    rodadaAtual[0]++;
                    asteroidesChegaram[0] = 0;
                    asteroidesGerados[0] = 0;
                    if (rodadaAtual[0] > totalRodadas) {
                        ((Timer) e.getSource()).stop(); // Para o timer ao final das rodadas
                        return;
                    }
                }

                // Gera os asteroides da rodada atual
                if (asteroidesGerados[0] < rodadaAtual[0]) {
                    int posicaoY = posicoesY[random.nextInt(posicoesY.length)];
                    Asteroide asteroide = new Asteroide(800, posicaoY, 80, 80,"asteroide.png", sensores, gameWindow);


                    // Adiciona o asteroide à janela
                    gameWindow.adicionarObjeto(asteroide);
                    asteroidesGerados[0]++;

                    // Define o movimento do asteroide
                    new Thread(() -> {
                        while (asteroide.getX() > -100) {
                            asteroide.setX(asteroide.getX() - 20);

                            // Verifica colisão com a nave
                            if (asteroide.getRect().intersects(nave.getRect())) {
                                nave.reduzirVida(); // Reduz uma vida da nave
                                vidasLabel.setText("Vidas: " + nave.getVidaNave()); // Atualiza o rótulo de vidas

                                if (nave.getVidaNave() <= 0) {
                                    JOptionPane.showMessageDialog(gameWindow, "Game Over!");
                                    System.exit(0); // Finaliza o jogo
                                }
                                break; // Sai do loop ao detectar colisão
                            }

                            try {
                                Thread.sleep(50); // Controla a velocidade do movimento
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            gameWindow.repaint();
                        }

                        // Incrementa o contador de asteroides que chegaram à borda
                        asteroidesChegaram[0]++;
                        gameWindow.removeObjeto(asteroide);
                    }).start();
                }
            }

            // Atualiza o contador no rótulo
            contadorLabel.setText("Rodada: " + rodadaAtual[0] + ", Asteroides gerados: " + asteroidesGerados[0]);
        });
        geradorAsteroides.start();

        // Inicia o loop do jogo
        Timer timer = new Timer(16, e1 -> gameWindow.repaint());
        timer.start();
    }
}