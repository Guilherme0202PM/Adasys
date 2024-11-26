import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Nave extends CriaObjeto {
    private Sensores sensores;
    private GameWindow janela;
    private Movimento movimento;
    private int vidaNave = 3; // Vidas iniciais da nave



    public Nave(int x, int y, int largura, int altura, String nomeImagem, Sensores sensores, GameWindow janela) {
        super(x, y, largura, altura, nomeImagem); // O nome da imagem agora é nulo
        this.sensores = sensores;
        this.janela = janela;
        this.movimento = new Movimento(); // Inicializa a lógica de movimento
    }

    // Reduz uma vida da nave
    public void reduzirVida() {
        if (vidaNave > 0) {
            vidaNave--;
        }
    }

    // Retorna o número de vidas restantes
    public int getVidaNave() {
        return vidaNave;
    }

    public void adicionarListener() {
        janela.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int velocidade = 5; // Velocidade do movimento

                // Movimento baseado nas teclas pressionadas
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    movimento.movimentoY(Nave.this, velocidade); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    movimento.movimentoY(Nave.this, -velocidade); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_1) {
                    movimento.goY(Nave.this, 100); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_2) {
                    movimento.goY(Nave.this, 250); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_3) {
                    movimento.goY(Nave.this, 400); // Move para cima
                }

                // Verifica colisão com a borda da janela
                if (sensores.tocandoBorda(Nave.this)) {
                    System.out.println("A nave está tocando a borda da tela!");

                    // Ajusta a posição para evitar sair dos limites da tela
                    Rectangle tela = new Rectangle(0, 0, janela.getContentPane().getWidth(), janela.getContentPane().getHeight());
                    Rectangle naveRect = Nave.this.getRect();

                    if (naveRect.getX() < tela.getX()) {
                        Nave.this.setX(0);
                    } else if (naveRect.getMaxX() > tela.getMaxX()) {
                        Nave.this.setX((int) (tela.getMaxX() - naveRect.getWidth()));
                    }

                    if (naveRect.getY() < tela.getY()) {
                        Nave.this.setY(0);
                    } else if (naveRect.getMaxY() > tela.getMaxY()) {
                        Nave.this.setY((int) (tela.getMaxY() - naveRect.getHeight()));
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Não há ações específicas no momento ao liberar uma tecla
            }
        });
    }


    @Override
    public void desenhar(Graphics g) {
        super.desenhar(g);
    }
}