package cl.preguntame.clases;

import cl.preguntame.model.Caracteristica;
import cl.preguntame.model.Concepto;
import cl.preguntame.model.Definicion;
import cl.preguntame.model.Observacion;
import cl.preguntame.model.Relacion;
import cl.preguntame.service.CaracteristicaService;
import cl.preguntame.service.ConceptoService;
import cl.preguntame.service.DefinicionService;
import cl.preguntame.service.ObservacionService;
import cl.preguntame.service.RelacionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generador {

    int Contenido_id;

    public Generador(int Contenido_id) {
        this.Contenido_id = Contenido_id;
    }

    public Generador() {
        this.Contenido_id = 0;
    }

    public ArrayList SeleccionDefinicionConcepto() {

        ConceptoService AccesoConcepto = new ConceptoService();
        List<Concepto> ListaConceptos = AccesoConcepto.BuscarConceptoContenido(Contenido_id);

        DefinicionService AccesoDefinicion = new DefinicionService();
        List<Definicion> ListaDefiniciones = AccesoDefinicion.BuscarDefinicionContenido(Contenido_id);

        CaracteristicaService AccesoCaracteristica = new CaracteristicaService();
        List<Caracteristica> ListaCaracteristicas = AccesoCaracteristica.BuscarCaracteristicaContenido(Contenido_id);

        ObservacionService AccesoObservacion = new ObservacionService();
        List<Observacion> ListaObservaciones = AccesoObservacion.BuscarObservacionContenido(Contenido_id);

        RelacionService AccesoRelacion = new RelacionService();
        List<Relacion> ListaRelaciones = AccesoRelacion.BuscarRelacionContenido(Contenido_id);

        ArrayList<SeleccionDefinicionConcepto> seleccionDefinicionConceptos = new ArrayList<>();



            for (int j = 0; j < ListaDefiniciones.size(); j++) {

                

                    SeleccionDefinicionConcepto pregunta = new SeleccionDefinicionConcepto();
                    pregunta.setEnunciado("Cual es la definicion de " + ListaDefiniciones.get(j).getConcepto().getNombre() + ": ");
                    pregunta.setDefCorrecta(ListaDefiniciones.get(j));

                    Definicion[] opciones = new Definicion[4];
                    opciones[0] = pregunta.getDefCorrecta();
                    Random r = new Random();

                    int aleatorio = 0;
                    boolean sw = false;

                    for (int x = 1; x < 4; x++) {

                        do {
                            aleatorio = (int) (r.nextDouble() * (ListaDefiniciones.size()) + 0);
                            sw = false;
                            for (int y = 0; y < x; y++) {
                                //cuando hay pocos
                                if (ListaDefiniciones.size() < 4) {
                                    break;
                                }
                                //cuando hay uno con 2 y pocos
                                if (ListaDefiniciones.size() <= 4 && ListaDefiniciones.get(aleatorio).getConcepto().getId().equals(pregunta.getDefCorrecta().getConcepto().getId())) {
                                    break;
                                }
                                //que no se repitan
                                if (ListaDefiniciones.get(aleatorio).getId().equals(opciones[y].getId()) || ListaDefiniciones.get(aleatorio).getConcepto().getId().equals(pregunta.getDefCorrecta().getConcepto().getId())) {
                                    sw = true;
                                }

                            }
                        } while (sw);

                        opciones[x] = ListaDefiniciones.get(aleatorio);
                    }

                    for (int x = opciones.length - 1; x > 0; x--) {
                        int rand = (int) (Math.random() * (x + 1));
                        Definicion temp = opciones[x];
                        opciones[x] = opciones[rand];
                        opciones[rand] = temp;
                    }

                    pregunta.setOpcion1(opciones[0]);
                    pregunta.setOpcion2(opciones[1]);
                    pregunta.setOpcion3(opciones[2]);
                    pregunta.setOpcion4(opciones[3]);

                    seleccionDefinicionConceptos.add(pregunta);

                
            }

        
        return seleccionDefinicionConceptos;
    }

    public ArrayList SeleccionConceptoDefinicion() {

        ConceptoService AccesoConcepto = new ConceptoService();
        List<Concepto> ListaConceptos = AccesoConcepto.BuscarConceptoContenido(Contenido_id);

        DefinicionService AccesoDefinicion = new DefinicionService();
        List<Definicion> ListaDefiniciones = AccesoDefinicion.BuscarDefinicionContenido(Contenido_id);

        ArrayList<SeleccionConceptoDefinicion> seleccionConceptoDefinicion = new ArrayList<>();

        for (int i = 0; i < ListaDefiniciones.size(); i++) {

            SeleccionConceptoDefinicion pregunta = new SeleccionConceptoDefinicion();
            pregunta.setEnunciado("Cual es el concepto de la definicion " + ListaDefiniciones.get(i).getDetalle() + ": ");
            pregunta.setConceptoCorrecto(ListaDefiniciones.get(i).getConcepto());

            Concepto[] opciones = new Concepto[4];
            opciones[0] = pregunta.getConceptoCorrecto();

            Random r = new Random();

            int aleatorio = 0;
            boolean sw = false;

            for (int x = 1; x < 4; x++) {
                //
                do {
                    aleatorio = (int) (r.nextDouble() * (ListaConceptos.size()) + 0);
                    sw = false;
                    for (int y = 0; y < x; y++) {
                        //cuando hay pocos para que se pueda repetir break
                        if (ListaConceptos.size() < 4) {
                            break;
                        }
                  
                        //que no se repitan
                        if (ListaConceptos.get(aleatorio).getId().equals(opciones[y].getId()) || ListaConceptos.get(aleatorio).getId().equals(pregunta.getConceptoCorrecto().getId())) {
                            sw = true;
                        }

                    }
                } while (sw);

                opciones[x] = ListaConceptos.get(aleatorio);
                //
            }

            for (int x = opciones.length - 1; x > 0; x--) {
                int rand = (int) (Math.random() * (x + 1));
                Concepto temp = opciones[x];
                opciones[x] = opciones[rand];
                opciones[rand] = temp;
            }

            pregunta.setOpcion1(opciones[0]);
            pregunta.setOpcion2(opciones[1]);
            pregunta.setOpcion3(opciones[2]);
            pregunta.setOpcion4(opciones[3]);
            
            seleccionConceptoDefinicion.add(pregunta);

        }
        
        return seleccionConceptoDefinicion;

    }

    public static void main(String[] args) {
        Generador e = new Generador(44);
    
       /*
        //Seleccion de concepto a partir de una definicion
        System.out.println(e.SeleccionConceptoDefinicion().size());
        ArrayList<SeleccionConceptoDefinicion> seleccionConceptosDefinicion = e.SeleccionConceptoDefinicion();
        
        for (int i = 0; i < seleccionConceptosDefinicion.size(); i++) {
         System.out.println(seleccionConceptosDefinicion.get(i).getEnunciado());
         System.out.println("");
         System.out.println("Correcto : " + seleccionConceptosDefinicion.get(i).getConceptoCorrecto().getNombre());
         System.out.println("Opcion : " + seleccionConceptosDefinicion.get(i).getOpcion1().getNombre());
         System.out.println("Opcion : " + seleccionConceptosDefinicion.get(i).getOpcion2().getNombre());
         System.out.println("Opcion : " + seleccionConceptosDefinicion.get(i).getOpcion3().getNombre());
         System.out.println("Opcion : " + seleccionConceptosDefinicion.get(i).getOpcion4().getNombre());
         System.out.println("--------------------------------------------");
         }
        
        */
        
        
        
         //Seleccion de definicion a partir de un concepto
        /*
        ArrayList<SeleccionDefinicionConcepto> seleccionDefinicionConceptos = e.SeleccionDefinicionConcepto();

         for (int i = 0; i < seleccionDefinicionConceptos.size(); i++) {
         System.out.println(seleccionDefinicionConceptos.get(i).getEnunciado());
         System.out.println("");
         System.out.println("Correcto : " + seleccionDefinicionConceptos.get(i).getDefCorrecta().getDetalle());
         System.out.println("Opcion : " + seleccionDefinicionConceptos.get(i).getOpcion1().getDetalle());
         System.out.println("Opcion : " + seleccionDefinicionConceptos.get(i).getOpcion2().getDetalle());
         System.out.println("Opcion : " + seleccionDefinicionConceptos.get(i).getOpcion3().getDetalle());
         System.out.println("Opcion : " + seleccionDefinicionConceptos.get(i).getOpcion4().getDetalle());
         System.out.println("--------------------------------------------");
         }
                */
         
    }

}
