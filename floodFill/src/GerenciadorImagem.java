import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GerenciadorImagem {

    public static BufferedImage carregarImagem(){
        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("Selecione a imagem para pintar");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo Imagem", "jpg", "png", "jpeg");
        chooser.setFileFilter(filter);

        int resultado = chooser.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION){
            File arquivo = chooser.getSelectedFile();

            try {
                BufferedImage imagem = ImageIO.read(arquivo);
                return imagem;
            } catch (IOException e) {
                System.err.println("Erro ao carregar imagem: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo selecionado");
        }

        return null;
    };

    public static void salvarImagem(BufferedImage imagem, String caminho){
        try {
            File arquivoSaida = new File(caminho);
            ImageIO.write(imagem, "png", arquivoSaida);
            System.out.println("Imagem salva em: " + arquivoSaida);
        } catch (IOException ex) {
            System.err.println("Erro ao salvar imagem: " + ex.getMessage());
        }
    }
}
