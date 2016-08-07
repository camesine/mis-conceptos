package cl.preguntame.clases;

public class PreguntaSeleccion {
    
    String Enunciado;
    String Respuesta;
    String Opcion1;
    String Opcion2;
    String Opcion3;
    String Opcion4;

    public PreguntaSeleccion(String Enunciado, String Respuesta, String Opcion1, String Opcion2, String Opcion3, String Opcion4) {
        this.Enunciado = Enunciado;
        this.Respuesta = Respuesta;
        this.Opcion1 = Opcion1;
        this.Opcion2 = Opcion2;
        this.Opcion3 = Opcion3;
        this.Opcion4 = Opcion4;
    }
    
    public PreguntaSeleccion() {
        this.Enunciado = null;
        this.Respuesta = null;
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

    public String getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(String Respuesta) {
        this.Respuesta = Respuesta;
    }

    public String getOpcion1() {
        return Opcion1;
    }

    public void setOpcion1(String Opcion1) {
        this.Opcion1 = Opcion1;
    }

    public String getOpcion2() {
        return Opcion2;
    }

    public void setOpcion2(String Opcion2) {
        this.Opcion2 = Opcion2;
    }

    public String getOpcion3() {
        return Opcion3;
    }

    public void setOpcion3(String Opcion3) {
        this.Opcion3 = Opcion3;
    }

    public String getOpcion4() {
        return Opcion4;
    }

    public void setOpcion4(String Opcion4) {
        this.Opcion4 = Opcion4;
    }
    
    
    
    
    
}
