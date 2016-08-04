
package cl.preguntame.clases;

import cl.preguntame.model.Definicion;

public class SeleccionDefinicionConcepto {
    
    String Enunciado;
    Definicion DefCorrecta;
    Definicion Opcion1;
    Definicion Opcion2;
    Definicion Opcion3;
    Definicion Opcion4;

    public SeleccionDefinicionConcepto(String Enunciado, Definicion DefCorrecta, Definicion Opcion1, Definicion Opcion2, Definicion Opcion3, Definicion Opcion4) {
        this.Enunciado = Enunciado;
        this.DefCorrecta = DefCorrecta;
        this.Opcion1 = Opcion1;
        this.Opcion2 = Opcion2;
        this.Opcion3 = Opcion3;
        this.Opcion4 = Opcion4;
    }
    
    public SeleccionDefinicionConcepto() {
        this.Enunciado = null;
        this.DefCorrecta = null;
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

    public Definicion getDefCorrecta() {
        return DefCorrecta;
    }

    public void setDefCorrecta(Definicion DefCorrecta) {
        this.DefCorrecta = DefCorrecta;
    }

    public Definicion getOpcion1() {
        return Opcion1;
    }

    public void setOpcion1(Definicion Opcion1) {
        this.Opcion1 = Opcion1;
    }

    public Definicion getOpcion2() {
        return Opcion2;
    }

    public void setOpcion2(Definicion Opcion2) {
        this.Opcion2 = Opcion2;
    }

    public Definicion getOpcion3() {
        return Opcion3;
    }

    public void setOpcion3(Definicion Opcion3) {
        this.Opcion3 = Opcion3;
    }

    public Definicion getOpcion4() {
        return Opcion4;
    }

    public void setOpcion4(Definicion Opcion4) {
        this.Opcion4 = Opcion4;
    }

    
}
