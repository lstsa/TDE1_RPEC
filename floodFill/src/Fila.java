public class Fila {
    No first;
    No last;

    public Fila() {
        first = null;
        last = null;
    }

    public void enfileirar(Pixel p){
        No n = new No(p);
        if(first == null){
            first = last = n;
            return;
        }
        last.setProximo(n);
        last = n;
    }

    public Pixel desenfileirar(){
        if (first == null) return null;
        Pixel pixel = first.getPixel();
        first = first.getProximo();
        if(first == null) last = null;
        return pixel;
    }
}
