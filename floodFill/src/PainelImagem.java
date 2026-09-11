import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PainelImagem extends JPanel{
    private BufferedImage imagem;
    private Color corPreenchimento = Color.RED;
    private boolean usandoPilha = true;
    private int contadorFrames = 0;

    public PainelImagem(){
        setPreferredSize(new Dimension(800,600));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (imagem != null) {
                    int x = e.getX();
                    int y = e.getY();

                    if (x >= 0 && x < imagem.getWidth() && y >= 0 && y < imagem.getHeight()) {

                        Pixel pixelClicado = new Pixel(x, y);

                        new Thread(() -> {
                            FloodFill.pintar(imagem, pixelClicado, Color.RED, PainelImagem.this);
                        }).start();

                    } else {
                        System.out.println("Foi clicado fora da imagem.");
                    }
                }
            }
        });
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
