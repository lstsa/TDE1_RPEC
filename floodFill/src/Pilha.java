public class Pilha {
    private No top;

    public Pilha(){
        this.top = null;
    }

    public void empilhar(Pixel p){
        No no = new No(p);
        no.setProximo(this.top);
        this.top = no;
    }

    public Pixel desempilhar() {
        if (this.top == null) return null;
        Pixel pixel = this.top.getPixel();
        this.top = this.top.getProximo();
        return pixel;
    }
}
