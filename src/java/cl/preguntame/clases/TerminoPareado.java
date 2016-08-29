package cl.preguntame.clases;



public class TerminoPareado {

    int IdConcepto;
    String NombreConcepto;
    int IdAlternativaConcepto;
    String TextoAlternativa;

    public TerminoPareado(int IdConcepto, String NombreConcepto, int IdAlternativaConcepto, String TextoAlternativa) {
        this.IdConcepto = IdConcepto;
        this.NombreConcepto = NombreConcepto;
        this.IdAlternativaConcepto = IdAlternativaConcepto;
        this.TextoAlternativa = TextoAlternativa;
    }
    
    
    
    public TerminoPareado() {
        this.IdConcepto = 0;
        this.NombreConcepto = null;
        this.IdAlternativaConcepto = 0;
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

    public int getIdAlternativaConcepto() {
        return IdAlternativaConcepto;
    }

    public void setIdAlternativaConcepto(int IdAlternativaConcepto) {
        this.IdAlternativaConcepto = IdAlternativaConcepto;
    }

    public String getTextoAlternativa() {
        return TextoAlternativa;
    }

    public void setTextoAlternativa(String TextoAlternativa) {
        this.TextoAlternativa = TextoAlternativa;
    }

    
    
    
}
