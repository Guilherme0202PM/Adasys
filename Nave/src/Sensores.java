import java.awt.Rectangle;

public class Sensores {
    private GameWindow janela;

    public Sensores(GameWindow janela) {
        this.janela = janela;
    }

    // Verifica se o objeto está tocando a borda
    public boolean tocandoBorda(CriaObjeto objeto) {
        Rectangle objetoRect = objeto.getRect();
        Rectangle tela = new Rectangle(0, 0, janela.getContentPane().getWidth(), janela.getContentPane().getHeight());
        return !tela.contains(objetoRect);
    }

}
