package cl.preguntame.model;


import java.util.HashSet;
import java.util.Set;



public class Contenido implements java.io.Serializable {

    private Integer id;
    private Usuario usuario;
    private String nombre;
    private String texto;
    private Set conceptos = new HashSet(0);

    public Contenido() {
    }

    public Contenido(Usuario usuario, String nombre, String texto) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.texto = texto;
    }

    public Contenido(Usuario usuario, String nombre, String texto, Set conceptos) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.texto = texto;
        this.conceptos = conceptos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Set getConceptos() {
        return this.conceptos;
    }

    public void setConceptos(Set conceptos) {
        this.conceptos = conceptos;
    }

}
