public class No {
    private final Pixel pixel;
    private No proximo;

    public No(Pixel pixel) {
        this.pixel = pixel;
        this.proximo = null;
    }

    public Pixel getPixel(){
        return this.pixel;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getProximo(){
        return this.proximo;
    }


}
