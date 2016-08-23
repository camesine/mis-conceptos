package cl.preguntame.clases;



public class TerminoPareado {

    int IdConcepto;
    String NombreConcepto;
    int IdAlternativa;
    String TextoAlternativa;

    public TerminoPareado(int IdConcepto, String NombreConcepto, int IdAlternativa, String TextoAlternativa) {
        this.IdConcepto = IdConcepto;
        this.NombreConcepto = NombreConcepto;
        this.IdAlternativa = IdAlternativa;
        this.TextoAlternativa = TextoAlternativa;
    }
    
    
    
    public TerminoPareado() {
        this.IdConcepto = 0;
        this.NombreConcepto = null;
        this.IdAlternativa = 0;
        this.TextoAlternativa = null;
    }

    public int getIdConcepto() {
        return IdConcepto;
    }

    public void setIdConcepto(int IdConcepto) {
        this.IdConcepto = IdConcepto;
    }

    public String getNombreConcepto() {
        return NombreConcepto;
    }

    public void setNombreConcepto(String NombreConcepto) {
        this.NombreConcepto = NombreConcepto;
    }

    public int getIdAlternativa() {
        return IdAlternativa;
    }

    public void setIdAlternativa(int IdAlternativa) {
        this.IdAlternativa = IdAlternativa;
    }

    public String getTextoAlternativa() {
        return TextoAlternativa;
    }

    public void setTextoAlternativa(String TextoAlternativa) {
        this.TextoAlternativa = TextoAlternativa;
    }
    
    
    
    
}
