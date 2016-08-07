package cl.preguntame.clases;

import cl.preguntame.model.Caracteristica;

public class SeleccionCaracteristicaConcepto {

    String Enunciado;
    Caracteristica CaCorrecta;
    Caracteristica Opcion1;
    Caracteristica Opcion2;
    Caracteristica Opcion3;
    Caracteristica Opcion4;

    public SeleccionCaracteristicaConcepto(String Enunciado, Caracteristica CaCorrecta, Caracteristica Opcion1, Caracteristica Opcion2, Caracteristica Opcion3, Caracteristica Opcion4) {
        this.Enunciado = Enunciado;
        this.CaCorrecta = CaCorrecta;
        this.Opcion1 = Opcion1;
        this.Opcion2 = Opcion2;
        this.Opcion3 = Opcion3;
        this.Opcion4 = Opcion4;
    }
    
    public SeleccionCaracteristicaConcepto() {
        this.Enunciado = null;
        this.CaCorrecta = null;
        this.Opcion1 = null;
        this.Opcion2 = null;
        this.Opcion3 = null;
        this.Opcion4 = null;
    }

    public String getEnunciado() {
        return Enunciado;
    }

    public void setEnunciado(String Enunciado) {
        this.Enunciado = Enunciado;
    }

    public Caracteristica getCaCorrecta() {
        return CaCorrecta;
    }

    public void setCaCorrecta(Caracteristica CaCorrecta) {
        this.CaCorrecta = CaCorrecta;
    }

    public Caracteristica getOpcion1() {
        return Opcion1;
    }

    public void setOpcion1(Caracteristica Opcion1) {
        this.Opcion1 = Opcion1;
    }

    public Caracteristica getOpcion2() {
        return Opcion2;
    }

    public void setOpcion2(Caracteristica Opcion2) {
        this.Opcion2 = Opcion2;
    }

    public Caracteristica getOpcion3() {
        return Opcion3;
    }

    public void setOpcion3(Caracteristica Opcion3) {
        this.Opcion3 = Opcion3;
    }

    public Caracteristica getOpcion4() {
        return Opcion4;
    }

    public void setOpcion4(Caracteristica Opcion4) {
        this.Opcion4 = Opcion4;
    }

    
    
    
    
    
    
    
    
    
    
}
