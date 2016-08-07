package cl.preguntame.clases;

import cl.preguntame.model.Caracteristica;
import cl.preguntame.model.Concepto;
import cl.preguntame.model.Definicion;
import cl.preguntame.model.Observacion;
import cl.preguntame.service.CaracteristicaService;
import cl.preguntame.service.ConceptoService;
import cl.preguntame.service.DefinicionService;
import cl.preguntame.service.ObservacionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generador {

    int Contenido_id;
    ArrayList<PreguntaSeleccion> preguntasSeleccion;
            
    public Generador(int Contenido_id) {
        this.Contenido_id = Contenido_id;
        this.preguntasSeleccion = new ArrayList<>();
        
    }

    public Generador() {
        this.Contenido_id = 0;
        this.preguntasSeleccion = new ArrayList<>();
        
    }

    public ArrayList SeleccionDefinicionConcepto() {

        DefinicionService AccesoDefinicion = new DefinicionService();
        List<Definicion> ListaDefiniciones = AccesoDefinicion.BuscarDefinicionContenido(Contenido_id);

       
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

    public ArrayList SeleccionCaracteristicaConcepto() {

        

        CaracteristicaService AccesoCaracteristica = new CaracteristicaService();
        List<Caracteristica> ListaCaracteristicas = AccesoCaracteristica.BuscarCaracteristicaContenido(Contenido_id);

        ArrayList<SeleccionCaracteristicaConcepto> seleccionCaracteristicaConcepto = new ArrayList<>();

        for (int j = 0; j < ListaCaracteristicas.size(); j++) {

            SeleccionCaracteristicaConcepto pregunta = new SeleccionCaracteristicaConcepto();
            pregunta.setEnunciado("Cual es la caracteristica de " + ListaCaracteristicas.get(j).getConcepto().getNombre() + ": ");
            pregunta.setCaCorrecta(ListaCaracteristicas.get(j));

            Caracteristica[] opciones = new Caracteristica[4];
            opciones[0] = pregunta.getCaCorrecta();
            Random r = new Random();

            int aleatorio = 0;
            boolean sw = false;

            for (int x = 1; x < 4; x++) {

                do {
                    aleatorio = (int) (r.nextDouble() * (ListaCaracteristicas.size()) + 0);
                    sw = false;
                    for (int y = 0; y < x; y++) {
                        //cuando hay pocos
                        if (ListaCaracteristicas.size() < 4) {
                            break;
                        }
                        //cuando hay uno con 2 y pocos
                        if (ListaCaracteristicas.size() <= 4 && ListaCaracteristicas.get(aleatorio).getConcepto().getId().equals(pregunta.getCaCorrecta().getConcepto().getId())) {
                            break;
                        }
                        //que no se repitan
                        if (ListaCaracteristicas.get(aleatorio).getId().equals(opciones[y].getId()) || ListaCaracteristicas.get(aleatorio).getConcepto().getId().equals(pregunta.getCaCorrecta().getConcepto().getId())) {
                            sw = true;
                        }

                    }
                } while (sw);

                opciones[x] = ListaCaracteristicas.get(aleatorio);
            }

            for (int x = opciones.length - 1; x > 0; x--) {
                int rand = (int) (Math.random() * (x + 1));
                Caracteristica temp = opciones[x];
                opciones[x] = opciones[rand];
                opciones[rand] = temp;
            }

            pregunta.setOpcion1(opciones[0]);
            pregunta.setOpcion2(opciones[1]);
            pregunta.setOpcion3(opciones[2]);
            pregunta.setOpcion4(opciones[3]);

            seleccionCaracteristicaConcepto.add(pregunta);

        }

        return seleccionCaracteristicaConcepto;

    }

    public ArrayList SeleccionConceptoCaracteristica() {

        ConceptoService AccesoConcepto = new ConceptoService();
        List<Concepto> ListaConceptos = AccesoConcepto.BuscarConceptoContenido(Contenido_id);

        CaracteristicaService AccesoCaracteristica = new CaracteristicaService();
        List<Caracteristica> ListaCaracteristicas = AccesoCaracteristica.BuscarCaracteristicaContenido(Contenido_id);

        ArrayList<SeleccionConceptoCaracteristica> seleccionConceptoCaracteristica = new ArrayList<>();

        for (int i = 0; i < ListaCaracteristicas.size(); i++) {

            SeleccionConceptoCaracteristica pregunta = new SeleccionConceptoCaracteristica();
            pregunta.setEnunciado("Cual es el concepto de la caracteristica " + ListaCaracteristicas.get(i).getDetalle() + ": ");
            pregunta.setConceptoCorrecto(ListaCaracteristicas.get(i).getConcepto());

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

            seleccionConceptoCaracteristica.add(pregunta);

        }

        return seleccionConceptoCaracteristica;

    }

    public ArrayList SeleccionObservacionConcepto() {

     

        ObservacionService AccesoObservacion = new ObservacionService();
        List<Observacion> ListaObservaciones = AccesoObservacion.BuscarObservacionContenido(Contenido_id);

        ArrayList<SeleccionObservacionConcepto> seleccionObservacionConcepto = new ArrayList<>();

        for (int j = 0; j < ListaObservaciones.size(); j++) {

            SeleccionObservacionConcepto pregunta = new SeleccionObservacionConcepto();
            pregunta.setEnunciado("Cual es la observacion de " + ListaObservaciones.get(j).getConcepto().getNombre() + ": ");
            pregunta.setObsCorrecta(ListaObservaciones.get(j));

            Observacion[] opciones = new Observacion[4];
            opciones[0] = pregunta.getObsCorrecta();
            Random r = new Random();

            int aleatorio = 0;
            boolean sw = false;

            for (int x = 1; x < 4; x++) {

                do {
                    aleatorio = (int) (r.nextDouble() * (ListaObservaciones.size()) + 0);
                    sw = false;
                    for (int y = 0; y < x; y++) {
                        //cuando hay pocos
                        if (ListaObservaciones.size() < 4) {
                            break;
                        }
                        //cuando hay uno con 2 y pocos
                        if (ListaObservaciones.size() <= 4 && ListaObservaciones.get(aleatorio).getConcepto().getId().equals(pregunta.getObsCorrecta().getConcepto().getId())) {
                            break;
                        }
                        //que no se repitan
                        if (ListaObservaciones.get(aleatorio).getId().equals(opciones[y].getId()) || ListaObservaciones.get(aleatorio).getConcepto().getId().equals(pregunta.getObsCorrecta().getConcepto().getId())) {
                            sw = true;
                        }

                    }
                } while (sw);

                opciones[x] = ListaObservaciones.get(aleatorio);
            }

            for (int x = opciones.length - 1; x > 0; x--) {
                int rand = (int) (Math.random() * (x + 1));
                Observacion temp = opciones[x];
                opciones[x] = opciones[rand];
                opciones[rand] = temp;
            }

            pregunta.setOpcion1(opciones[0]);
            pregunta.setOpcion2(opciones[1]);
            pregunta.setOpcion3(opciones[2]);
            pregunta.setOpcion4(opciones[3]);

            seleccionObservacionConcepto.add(pregunta);

        }

        return seleccionObservacionConcepto;

    }

    public ArrayList SeleccionConceptoObservacion() {

        ConceptoService AccesoConcepto = new ConceptoService();
        List<Concepto> ListaConceptos = AccesoConcepto.BuscarConceptoContenido(Contenido_id);

        ObservacionService AccesoObservacion = new ObservacionService();
        List<Observacion> ListaObservaciones = AccesoObservacion.BuscarObservacionContenido(Contenido_id);

        ArrayList<SeleccionConceptoObservacion> seleccionConceptoObservaciones = new ArrayList<>();

        for (int i = 0; i < ListaObservaciones.size(); i++) {

            SeleccionConceptoObservacion pregunta = new SeleccionConceptoObservacion();
            pregunta.setEnunciado("Cual es el concepto de la observacion " + ListaObservaciones.get(i).getDetalle() + ": ");
            pregunta.setConceptoCorrecto(ListaObservaciones.get(i).getConcepto());

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

            seleccionConceptoObservaciones.add(pregunta);

        }

        return seleccionConceptoObservaciones;

    }

    public ArrayList<PreguntaSeleccion> PreguntasSeleccion() {
      
        //
        
        ArrayList<SeleccionDefinicionConcepto> seleccionDefinicionConceptos = this.SeleccionDefinicionConcepto();
        for (int i = 0; i < seleccionDefinicionConceptos.size(); i++) {
            PreguntaSeleccion pregunta = new PreguntaSeleccion();
            pregunta.setEnunciado(seleccionDefinicionConceptos.get(i).getEnunciado());
            pregunta.setRespuesta(seleccionDefinicionConceptos.get(i).getDefCorrecta().getDetalle());
            pregunta.setOpcion1(seleccionDefinicionConceptos.get(i).getOpcion1().getDetalle());
            pregunta.setOpcion2(seleccionDefinicionConceptos.get(i).getOpcion2().getDetalle());
            pregunta.setOpcion3(seleccionDefinicionConceptos.get(i).getOpcion3().getDetalle());
            pregunta.setOpcion4(seleccionDefinicionConceptos.get(i).getOpcion4().getDetalle());
            this.preguntasSeleccion.add(pregunta);   
        }
        
        //
         

        
        ArrayList<SeleccionConceptoObservacion> seleccionConceptosObservaciones = this.SeleccionConceptoObservacion();
        for (int i = 0; i < seleccionConceptosObservaciones.size(); i++) {
            PreguntaSeleccion pregunta = new PreguntaSeleccion();
            pregunta.setEnunciado(seleccionConceptosObservaciones.get(i).getEnunciado());
            pregunta.setRespuesta(seleccionConceptosObservaciones.get(i).getConceptoCorrecto().getNombre());
            pregunta.setOpcion1(seleccionConceptosObservaciones.get(i).getOpcion1().getNombre());
            pregunta.setOpcion2(seleccionConceptosObservaciones.get(i).getOpcion2().getNombre());
            pregunta.setOpcion3(seleccionConceptosObservaciones.get(i).getOpcion3().getNombre());
            pregunta.setOpcion4(seleccionConceptosObservaciones.get(i).getOpcion4().getNombre());
            this.preguntasSeleccion.add(pregunta);   
        }
        
        //
        
        ArrayList<SeleccionObservacionConcepto> seleccionObservacionConcepto = this.SeleccionObservacionConcepto();
        for (int i = 0; i < seleccionObservacionConcepto.size(); i++) {
            PreguntaSeleccion pregunta = new PreguntaSeleccion();
            pregunta.setEnunciado(seleccionObservacionConcepto.get(i).getEnunciado());
            pregunta.setRespuesta(seleccionObservacionConcepto.get(i).getObsCorrecta().getDetalle());
            pregunta.setOpcion1(seleccionObservacionConcepto.get(i).getOpcion1().getDetalle());
            pregunta.setOpcion2(seleccionObservacionConcepto.get(i).getOpcion2().getDetalle());
            pregunta.setOpcion3(seleccionObservacionConcepto.get(i).getOpcion3().getDetalle());
            pregunta.setOpcion4(seleccionObservacionConcepto.get(i).getOpcion4().getDetalle());
            this.preguntasSeleccion.add(pregunta);   
        }

        //
        
        ArrayList<SeleccionConceptoCaracteristica> seleccionConceptosCaracteristicas = this.SeleccionConceptoCaracteristica();
        for (int i = 0; i < seleccionConceptosCaracteristicas.size(); i++) {
            PreguntaSeleccion pregunta = new PreguntaSeleccion();
            pregunta.setEnunciado(seleccionConceptosCaracteristicas.get(i).getEnunciado());
            pregunta.setRespuesta(seleccionConceptosCaracteristicas.get(i).getConceptoCorrecto().getNombre());
            pregunta.setOpcion1(seleccionConceptosCaracteristicas.get(i).getOpcion1().getNombre());
            pregunta.setOpcion2(seleccionConceptosCaracteristicas.get(i).getOpcion2().getNombre());
            pregunta.setOpcion3(seleccionConceptosCaracteristicas.get(i).getOpcion3().getNombre());
            pregunta.setOpcion4(seleccionConceptosCaracteristicas.get(i).getOpcion4().getNombre());
            this.preguntasSeleccion.add(pregunta);   
        }
        
        //
        
        ArrayList<SeleccionConceptoDefinicion> seleccionConceptosDefinicion = this.SeleccionConceptoDefinicion();
        for (int i = 0; i < seleccionConceptosDefinicion.size(); i++) {
            PreguntaSeleccion pregunta = new PreguntaSeleccion();
            pregunta.setEnunciado(seleccionConceptosDefinicion.get(i).getEnunciado());
            pregunta.setRespuesta(seleccionConceptosDefinicion.get(i).getConceptoCorrecto().getNombre());
            pregunta.setOpcion1(seleccionConceptosDefinicion.get(i).getOpcion1().getNombre());
            pregunta.setOpcion2(seleccionConceptosDefinicion.get(i).getOpcion2().getNombre());
            pregunta.setOpcion3(seleccionConceptosDefinicion.get(i).getOpcion3().getNombre());
            pregunta.setOpcion4(seleccionConceptosDefinicion.get(i).getOpcion4().getNombre());
            this.preguntasSeleccion.add(pregunta);   
        }
        
        //
        
        
        
        
        //
        ArrayList<SeleccionCaracteristicaConcepto> seleccionCaracteristicaConceptos = this.SeleccionCaracteristicaConcepto();
        for (int i = 0; i < seleccionCaracteristicaConceptos.size(); i++) {
            PreguntaSeleccion pregunta = new PreguntaSeleccion();
            pregunta.setEnunciado(seleccionCaracteristicaConceptos.get(i).getEnunciado());
            pregunta.setRespuesta(seleccionCaracteristicaConceptos.get(i).getCaCorrecta().getDetalle());
            pregunta.setOpcion1(seleccionCaracteristicaConceptos.get(i).getOpcion1().getDetalle());
            pregunta.setOpcion2(seleccionCaracteristicaConceptos.get(i).getOpcion2().getDetalle());
            pregunta.setOpcion3(seleccionCaracteristicaConceptos.get(i).getOpcion3().getDetalle());
            pregunta.setOpcion4(seleccionCaracteristicaConceptos.get(i).getOpcion4().getDetalle());
            this.preguntasSeleccion.add(pregunta);   
        }
        
        
        
        return this.preguntasSeleccion;
        
    }   

   
  
}
