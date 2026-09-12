import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JanelaPrincipal extends JFrame {
    private PainelImagem painelImagem;
    private JButton btnCarregar;
    private JButton btnEscolherCor;
    private JRadioButton rbPilha;
    private JRadioButton rbFila;

    public JanelaPrincipal(){
        setTitle("TDE1 - RPEC: Flood Fill");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        painelImagem = new PainelImagem();

        JScrollPane scroll = new JScrollPane(painelImagem);
        add(scroll, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        btnCarregar = new JButton("Carregar Imagem");
        btnEscolherCor = new JButton("Escolher Cor");
        rbPilha = new JRadioButton("Pilha (DFS)", true); // Selecionado por padrão
        rbFila = new JRadioButton("Fila (BFS)");
        ButtonGroup grupoModo = new ButtonGroup();
        grupoModo.add(rbPilha);
        grupoModo.add(rbFila);
        painelBotoes.add(btnCarregar);
        painelBotoes.add(btnEscolherCor);
        painelBotoes.add(rbPilha);
        painelBotoes.add(rbFila);
        add(painelBotoes, BorderLayout.NORTH);

        btnCarregar.addActionListener(e -> {
            BufferedImage imagem = GerenciadorImagem.carregarImagem();
            if (imagem != null){
                painelImagem.setImagem(imagem);
                pack();
                setLocationRelativeTo(null);
            }
        });

        btnEscolherCor.addActionListener(e -> {
            Color novaCor = JColorChooser.showDialog(
                    JanelaPrincipal.this,
                    "Selecione a Cor de Preenchimento",
                    Color.RED
            );

            if (novaCor != null) {
                painelImagem.setCorPreenchimento(novaCor);
            }
        });

        rbPilha.addActionListener(e -> painelImagem.setUsandoPilha(true));
        rbFila.addActionListener(e -> painelImagem.setUsandoPilha(false));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaPrincipal());
    }
}
