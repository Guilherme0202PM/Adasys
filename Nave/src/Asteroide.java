import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Asteroide extends CriaObjeto {
    private Sensores sensores;
    private GameWindow janela;
    private Movimento movimento;

    public Asteroide(int x, int y, int largura, int altura, String nomeImagem, Sensores sensores, GameWindow janela) {
        super(x, y, largura, altura, nomeImagem); // O nome da imagem agora é nulo
        this.sensores = sensores;
        this.janela = janela;
        this.movimento = new Movimento(); // Inicializa a lógica de movimento
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
                    movimento.movimentoY(Asteroide.this, velocidade); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    movimento.movimentoY(Asteroide.this, -velocidade); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_1) {
                    movimento.goY(Asteroide.this, 100); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_2) {
                    movimento.goY(Asteroide.this, 250); // Move para cima
                } else if (e.getKeyCode() == KeyEvent.VK_3) {
                    movimento.goY(Asteroide.this, 400); // Move para cima
                }

                // Verifica colisão com a borda da janela
                if (sensores.tocandoBorda(Asteroide.this)) {
                    System.out.println("A Asteroide está tocando a borda da tela!");

                    // Ajusta a posição para evitar sair dos limites da tela
                    Rectangle tela = new Rectangle(0, 0, janela.getContentPane().getWidth(), janela.getContentPane().getHeight());
                    Rectangle AsteroideRect = Asteroide.this.getRect();

                    if (AsteroideRect.getX() < tela.getX()) {
                        Asteroide.this.setX(0);
                    } else if (AsteroideRect.getMaxX() > tela.getMaxX()) {
                        Asteroide.this.setX((int) (tela.getMaxX() - AsteroideRect.getWidth()));
                    }

                    if (AsteroideRect.getY() < tela.getY()) {
                        Asteroide.this.setY(0);
                    } else if (AsteroideRect.getMaxY() > tela.getMaxY()) {
                        Asteroide.this.setY((int) (tela.getMaxY() - AsteroideRect.getHeight()));
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
