import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FloodFill {

    private static final File ARQUIVO_TEMP = new File("frame_temp.png");

    public static void pintarPilha(BufferedImage imagem, Pixel pixelInicial, Color novaCor, JPanel painel) {
        int x = pixelInicial.getPos_x();
        int y = pixelInicial.getPos_y();

        int corAlvo = imagem.getRGB(x, y);
        int corNovaInt = novaCor.getRGB();

        if (corAlvo == corNovaInt) return;

        Pilha pilha = new Pilha();
        imagem.setRGB(x, y, corNovaInt);
        pilha.empilhar(pixelInicial);

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int contadorPixels = 0;
        int intervaloRedesenhar = 100;

        while (true) {
            Pixel p = pilha.desempilhar();
            if (p == null) break;

            for (int i = 0; i < 4; i++) {
                int nx = p.getPos_x() + dx[i];
                int ny = p.getPos_y() + dy[i];

                if (nx >= 0 && nx < imagem.getWidth() && ny >= 0 && ny < imagem.getHeight()) {
                    if (imagem.getRGB(nx, ny) == corAlvo) {
                        imagem.setRGB(nx, ny, corNovaInt);
                        pilha.empilhar(new Pixel(nx, ny));

                        contadorPixels++;
                        if (contadorPixels % intervaloRedesenhar == 0) {
                            painel.repaint();
                            atualizarFrameTemporario(imagem);

                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
        }

        painel.repaint();
        removerFrameTemporario();
        salvarImagemFinal(imagem);
    }

    public static void pintarFila(BufferedImage imagem, Pixel pixelInicial, Color novaCor, JPanel painel) {
        int x = pixelInicial.getPos_x();
        int y = pixelInicial.getPos_y();

        int corAlvo = imagem.getRGB(x, y);
        int corNovaInt = novaCor.getRGB();

        if (corAlvo == corNovaInt) return;

        Fila fila = new Fila();
        imagem.setRGB(x, y, corNovaInt);

        fila.enfileirar(pixelInicial);

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int contadorPixels = 0;
        int intervaloRedesenhar = 100;

        while (true) {
            Pixel p = fila.desenfileirar();
            if (p == null) break;

            for (int i = 0; i < 4; i++) {
                int nx = p.getPos_x() + dx[i];
                int ny = p.getPos_y() + dy[i];

                if (nx >= 0 && nx < imagem.getWidth() && ny >= 0 && ny < imagem.getHeight()) {
                    if (imagem.getRGB(nx, ny) == corAlvo) {
                        imagem.setRGB(nx, ny, corNovaInt);

                        fila.enfileirar(new Pixel(nx, ny));

                        contadorPixels++;
                        if (contadorPixels % intervaloRedesenhar == 0) {
                            painel.repaint();
                            atualizarFrameTemporario(imagem);

                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
        }

        painel.repaint();
        removerFrameTemporario();
        salvarImagemFinal(imagem);
    }

    private static void atualizarFrameTemporario(BufferedImage imagem) {
        try {
            ImageIO.write(imagem, "png", ARQUIVO_TEMP);
        } catch (IOException e) {
            System.err.println("Erro ao atualizar frame temporário: " + e.getMessage());
        }
    }

    private static void removerFrameTemporario() {
        if (ARQUIVO_TEMP.exists()) {
            ARQUIVO_TEMP.delete();
        }
    }

    private static void salvarImagemFinal(BufferedImage imagem) {
        try {
            File arquivoFinal = new File("resultado_final.png");
            ImageIO.write(imagem, "png", arquivoFinal);
            System.out.println("Imagem final salva com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar imagem final: " + e.getMessage());
        }
    }
}