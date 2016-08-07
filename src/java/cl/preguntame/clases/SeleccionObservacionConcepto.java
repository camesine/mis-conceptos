package cl.preguntame.clases;

import cl.preguntame.model.Observacion;

public class SeleccionObservacionConcepto {
    
    String Enunciado;
    Observacion ObsCorrecta;
    Observacion Opcion1;
    Observacion Opcion2;
    Observacion Opcion3;
    Observacion Opcion4;

    
    public SeleccionObservacionConcepto(String Enunciado, Observacion ObsCorrecta, Observacion Opcion1, Observacion Opcion2, Observacion Opcion3, Observacion Opcion4) {
        this.Enunciado = Enunciado;
        this.ObsCorrecta = ObsCorrecta;
        this.Opcion1 = Opcion1;
        this.Opcion2 = Opcion2;
        this.Opcion3 = Opcion3;
        this.Opcion4 = Opcion4;
    }
    
    public SeleccionObservacionConcepto() {
        this.Enunciado = null;
        this.ObsCorrecta = null;
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

    public Observacion getObsCorrecta() {
        return ObsCorrecta;
    }

    public void setObsCorrecta(Observacion ObsCorrecta) {
        this.ObsCorrecta = ObsCorrecta;
    }

    public Observacion getOpcion1() {
        return Opcion1;
    }

    public void setOpcion1(Observacion Opcion1) {
        this.Opcion1 = Opcion1;
    }

    public Observacion getOpcion2() {
        return Opcion2;
    }

    public void setOpcion2(Observacion Opcion2) {
        this.Opcion2 = Opcion2;
    }

    public Observacion getOpcion3() {
        return Opcion3;
    }

    public void setOpcion3(Observacion Opcion3) {
        this.Opcion3 = Opcion3;
    }

    public Observacion getOpcion4() {
        return Opcion4;
    }

    public void setOpcion4(Observacion Opcion4) {
        this.Opcion4 = Opcion4;
    }
    
    
    
    
}
