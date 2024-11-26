import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private List<CriaObjeto> objetos;
    private Fundo fundo;

    public GamePanel() {
        objetos = new ArrayList<>();
    }

    public void setFundo(Fundo fundo) {
        this.fundo = fundo;
        repaint();
    }

    public void adicionarObjeto(CriaObjeto objeto) {
        objetos.add(objeto);
        repaint();
    }

    public void removeObjeto(CriaObjeto objeto) {
        objetos.remove(objeto);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fundo != null) {
            fundo.desenhar(g);
        }

        // Desenha os objetos na tela
        for (CriaObjeto objeto : objetos) {
            objeto.desenhar(g);
        }
    }
}