package cl.preguntame.clases;

import cl.preguntame.model.Concepto;

public class SeleccionConceptoDefinicion {
    String Enunciado;
    Concepto ConceptoCorrecto;
    Concepto Opcion1;
    Concepto Opcion2;
    Concepto Opcion3;
    Concepto Opcion4;

    public SeleccionConceptoDefinicion(String Enunciado, Concepto ConceptoCorrecto, Concepto Opcion1, Concepto Opcion2, Concepto Opcion3, Concepto Opcion4) {
        this.Enunciado = Enunciado;
        this.ConceptoCorrecto = ConceptoCorrecto;
        this.Opcion1 = Opcion1;
        this.Opcion2 = Opcion2;
        this.Opcion3 = Opcion3;
        this.Opcion4 = Opcion4;
    }
    
    public SeleccionConceptoDefinicion() {
        this.Enunciado = null;
        this.ConceptoCorrecto = null;
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

    public Concepto getConceptoCorrecto() {
        return ConceptoCorrecto;
    }

    public void setConceptoCorrecto(Concepto ConceptoCorrecto) {
        this.ConceptoCorrecto = ConceptoCorrecto;
    }

    public Concepto getOpcion1() {
        return Opcion1;
    }

    public void setOpcion1(Concepto Opcion1) {
        this.Opcion1 = Opcion1;
    }

    public Concepto getOpcion2() {
        return Opcion2;
    }

    public void setOpcion2(Concepto Opcion2) {
        this.Opcion2 = Opcion2;
    }

    public Concepto getOpcion3() {
        return Opcion3;
    }

    public void setOpcion3(Concepto Opcion3) {
        this.Opcion3 = Opcion3;
    }

    public Concepto getOpcion4() {
        return Opcion4;
    }

    public void setOpcion4(Concepto Opcion4) {
        this.Opcion4 = Opcion4;
    }
    
    
    
    
}
