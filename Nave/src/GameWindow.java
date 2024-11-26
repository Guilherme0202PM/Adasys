import javax.swing.*;

public class GameWindow extends JFrame {
    private GamePanel gamePanel;

    public GameWindow() {
        gamePanel = new GamePanel();
        add(gamePanel);
        setTitle("Meu Jogo");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void adicionarObjeto(CriaObjeto objeto) {
        gamePanel.adicionarObjeto(objeto);
    }

    public void removeObjeto(CriaObjeto objeto) {
        gamePanel.removeObjeto(objeto);
    }

    public void addComponentToGamePanel(JComponent component) {
        gamePanel.add(component);
    }

    public int getLarguraTela() {
        return getWidth();
    }

    public int getAlturaTela() {
        return getHeight();
    }

    public void setFundo(Fundo fundo) {
        gamePanel.setFundo(fundo);
    }

}