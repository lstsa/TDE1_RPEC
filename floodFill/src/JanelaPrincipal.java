import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JanelaPrincipal extends JFrame {
    private PainelImagem painelImagem;
    private JButton btnCarregar;

    public JanelaPrincipal(){
        setTitle("TDE1 - RPEC: Flood Fill");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        painelImagem = new PainelImagem();

        JScrollPane scroll = new JScrollPane(painelImagem);
        add(scroll, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        btnCarregar = new JButton("Carregar Imagem");
        painelBotoes.add(btnCarregar);
        add(painelBotoes, BorderLayout.NORTH);

        btnCarregar.addActionListener(e -> {
            BufferedImage imagem = GerenciadorImagem.carregarImagem();
            if (imagem != null){
                painelImagem.setImagem(imagem);
                pack();
                setLocationRelativeTo(null);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
