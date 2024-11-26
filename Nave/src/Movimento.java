import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Movimento {
    private GameWindow janela;
    private double velocidadeVertical = 0; // Velocidade vertical do player
    private double gravidade = 0.5; // Força da gravidade
    private double salto = -15; // Força do salto
    private boolean noAr = true; // Indica se o player está no ar
    private boolean saltando = false; // Verifica se o salto está ativo

    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private volatile boolean running = false;

    public void movimentoX(CriaObjeto objeto, int velocidade) {
        objeto.x += velocidade;
    }

    public void movimentoY(CriaObjeto objeto, int velocidade) {
        objeto.y += velocidade;
    }

    public void goX(CriaObjeto objeto, int newX) {
        objeto.setX(newX);
    }

    public void goY(CriaObjeto objeto, int newY) {
        objeto.setY(newY);
    }

    public void goPosicao(CriaObjeto objeto, int newX, int newY) {
        objeto.setX(newX);
        objeto.setY(newY);
    }

    public void goPosicaoAleatoria(CriaObjeto objeto, GameWindow janela) {
        int larguraTela = janela.getWidth();
        int alturaTela = janela.getHeight();

        int xAleatorio = (int) (Math.random() * (larguraTela));
        int yAleatorio = (int) (Math.random() * (alturaTela));

        objeto.x = xAleatorio;
        objeto.y = yAleatorio;
    }

    public void girarDireita(CriaObjeto objeto, double graus) {
        objeto.girarDireita(graus);
    }

    public void girarEsquerda(CriaObjeto objeto, double graus) {
        objeto.girarEsquerda(graus);
    }

    public void apontarDirecao(CriaObjeto objeto, double angulo) {
        objeto.apontarDirecao(angulo);
    }
}
