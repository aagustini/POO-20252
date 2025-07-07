package poo.ep.pucrs;

public class Circulo {
    private double centroX;
    private double centroY;

    private double raio;

    public Circulo(double umX, double umY, double umRaio) {
        this.centroX = umX;
        this.centroY = umY;

        this.raio = umRaio ;
    }

    public Circulo() {
        this(0,0,1);
    }

    public void mover(double novoX, double novoY) {
        this.centroX = novoX;
        this.centroY = novoY;        
    }

    public void zoom(double fator) {   
        raio = raio * fator;
    }

    public double area() {
        return Math.PI * raio * raio; // Math.pow(raio, 2)
    }

    @Override
    public String toString() {
        return String.format("centro: (%.2f, %.2f) raio: %.2f", 
                                centroX, centroY, raio);
    }
}