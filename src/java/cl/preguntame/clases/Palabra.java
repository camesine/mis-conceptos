package cl.preguntame.clases;

public class Palabra {

    String Palabra;
    String Morfologia;

    public Palabra(String Palabra, String Morfologia) {
        this.Palabra = Palabra;
        this.Morfologia = Morfologia;
    }

    public Palabra() {
        this.Palabra = "";
        this.Morfologia = "";
    }

    public String getPalabra() {
        return Palabra;
    }

    public void setPalabra(String Palabra) {
        this.Palabra = Palabra;
    }

    public String getMorfologia() {
        return Morfologia;
    }

    public void setMorfologia(String Morfologia) {
        this.Morfologia = Morfologia;
    }

}
