import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PainelImagem extends JPanel{
    private BufferedImage imagem;

    public PainelImagem(){
        setPreferredSize(new Dimension(800,600));
    }

    public void setImagem(BufferedImage imagem){
        this.imagem = imagem;
        if (imagem != null){
            setPreferredSize(new Dimension(imagem.getWidth(),imagem.getHeight()));
            revalidate();
        }
        repaint();
    }

    public BufferedImage getImagem(){
        return this.imagem;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (imagem != null){
            g.drawImage(imagem, 0, 0, null);
        }
    }
}
