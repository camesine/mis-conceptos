package cl.preguntame.clases;

import cl.preguntame.model.Caracteristica;
import cl.preguntame.model.Concepto;
import cl.preguntame.model.Definicion;
import cl.preguntame.model.Observacion;
import cl.preguntame.service.CaracteristicaService;
import cl.preguntame.service.ConceptoService;
import cl.preguntame.service.DefinicionService;
import cl.preguntame.service.ObservacionService;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

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
            pregunta.setEnunciado("DEFINICION DE " + ListaDefiniciones.get(j).getConcepto().getNombre().toUpperCase() + ": ");
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
            pregunta.setEnunciado("IDENTIFICAR CONCEPTO: \" " + ListaDefiniciones.get(i).getDetalle() + "\"");
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
            pregunta.setEnunciado("CARACTERISTICA DE " + ListaCaracteristicas.get(j).getConcepto().getNombre().toUpperCase() + ": ");
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
            pregunta.setEnunciado("IDENTIFICAR CONCEPTO: \"" + ListaCaracteristicas.get(i).getDetalle() + "\"");
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
            pregunta.setEnunciado("IDENTIFICAR OBSERVACION DE " + ListaObservaciones.get(j).getConcepto().getNombre().toUpperCase() + ": ");
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
            pregunta.setEnunciado("IDENTIFICAR CONCEPTO: \"" + ListaObservaciones.get(i).getDetalle() + "\"");
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
            pregunta.setConceptoReferencia(seleccionDefinicionConceptos.get(i).getDefCorrecta().getConcepto().getNombre());
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
            pregunta.setConceptoReferencia(seleccionConceptosObservaciones.get(i).getConceptoCorrecto().getNombre());
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
            pregunta.setConceptoReferencia(seleccionObservacionConcepto.get(i).getObsCorrecta().getConcepto().getNombre());
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
            pregunta.setConceptoReferencia(seleccionConceptosCaracteristicas.get(i).getConceptoCorrecto().getNombre());
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
            pregunta.setConceptoReferencia(seleccionConceptosDefinicion.get(i).getConceptoCorrecto().getNombre());
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
            pregunta.setConceptoReferencia(seleccionCaracteristicaConceptos.get(i).getCaCorrecta().getConcepto().getNombre());
            this.preguntasSeleccion.add(pregunta);
        }

        Collections.shuffle(this.preguntasSeleccion);

        return this.preguntasSeleccion;

    }

    public String PreguntasTerminosPareados() {

        String Etiquetas = "";

        DefinicionService AccesoDefinicion = new DefinicionService();
        List<Definicion> ListaDefiniciones = AccesoDefinicion.BuscarDefinicionContenido(this.Contenido_id);

        CaracteristicaService AccesoCaracteristica = new CaracteristicaService();
        List<Caracteristica> ListaCaracteristicas = AccesoCaracteristica.BuscarCaracteristicaContenido(this.Contenido_id);

        ObservacionService AccesoObservacion = new ObservacionService();
        List<Observacion> ListaObservaciones = AccesoObservacion.BuscarObservacionContenido(this.Contenido_id);

        ArrayList<TerminoPareado> terminosPareados = new ArrayList<>();

        for (int i = 0; i < ListaDefiniciones.size(); i++) {
            terminosPareados.add(new TerminoPareado(ListaDefiniciones.get(i).getConcepto().getId(), ListaDefiniciones.get(i).getConcepto().getNombre(), ListaDefiniciones.get(i).getConcepto().getId(), ListaDefiniciones.get(i).getDetalle()));
        }

        for (int i = 0; i < ListaCaracteristicas.size(); i++) {
            terminosPareados.add(new TerminoPareado(ListaCaracteristicas.get(i).getConcepto().getId(), ListaCaracteristicas.get(i).getConcepto().getNombre(), ListaCaracteristicas.get(i).getConcepto().getId(), ListaCaracteristicas.get(i).getDetalle()));
        }

        for (int i = 0; i < ListaObservaciones.size(); i++) {
            terminosPareados.add(new TerminoPareado(ListaObservaciones.get(i).getConcepto().getId(), ListaObservaciones.get(i).getConcepto().getNombre(), ListaObservaciones.get(i).getConcepto().getId(), ListaObservaciones.get(i).getDetalle()));
        }

        Collections.shuffle(terminosPareados);

        do {

            ArrayList<TerminoPareado> ListaTemp = new ArrayList<>();
            ListaTemp.add(terminosPareados.get(0));

            for (int i = 0; i < terminosPareados.size(); i++) {
                boolean sw = false;
                for (int x = 0; x < ListaTemp.size(); x++) {
                    if (ListaTemp.get(x).getIdConcepto() == terminosPareados.get(i).getIdConcepto()) {
                        sw = true;
                        break;
                    }
                }
                if (!sw) {
                    ListaTemp.add(terminosPareados.get(i));
                }

            }

            for (int x = ListaTemp.size() - 1; x >= 0; x--) {

                int rand = (int) (Math.random() * (x + 1));

                int tempId = ListaTemp.get(x).getIdAlternativaConcepto();
                String tempTexto = ListaTemp.get(x).getTextoAlternativa();

                ListaTemp.get(x).setIdAlternativaConcepto(ListaTemp.get(rand).getIdAlternativaConcepto());
                ListaTemp.get(x).setTextoAlternativa(ListaTemp.get(rand).getTextoAlternativa());
                ListaTemp.get(rand).setIdAlternativaConcepto(tempId);
                ListaTemp.get(rand).setTextoAlternativa(tempTexto);
            }

            for (int i = 0; i < ListaTemp.size(); i++) {
                ListaTemp.get(i).setNombreConcepto(i + 1 + ". " + ListaTemp.get(i).getNombreConcepto());
            }

            if (ListaTemp.size() >= 4 && ListaTemp.size() <= 10) {
                Etiquetas = Etiquetas + "<div class='pregunta'><input type='hidden' class='TipoPregunta' value='TerminosPareados' /><table class='Pareados'> <tr><th style='width: 150px;' >Concepto</th><th id='ColumnaNumero'>N°</th><th >Alternativa</th></tr>";
                for (int i = 0; i < ListaTemp.size(); i++) {

                    // Etiquetas = Etiquetas + "<tr><td style='overflow: hidden; white-space: nowrap; text-overflow: ellipsis; max-width: 50px ' >" + ListaTemp.get(i).getNombreConcepto() + "</td><td class='TdKey'>" + ListaTemp.get(i).getIdConcepto() + "</td><td><input type='text' id='TxtPareado' onKeypress='if (event.keyCode < 45 || event.keyCode > 57)event.returnValue = false;' placeholder='___'  maxlength='1' size='1' /> </td> <td style='overflow: hidden; white-space: nowrap; text-overflow: ellipsis; max-width: 50px ' ><p class='tooltip' >" + ListaTemp.get(i).getTextoAlternativa() + "<span>" + ListaTemp.get(i).getTextoAlternativa() + "</span></p></td> <td class='TdKey'>" + ListaTemp.get(i).getIdAlternativaConcepto() + "</td> </tr>";
                    Etiquetas = Etiquetas + "<tr><td style='overflow: hidden; white-space: nowrap; text-overflow: ellipsis; max-width: 50px ' >" + ListaTemp.get(i).getNombreConcepto() + "</td><td class='TdKey'>" + ListaTemp.get(i).getIdConcepto() + "</td><td><input type='text' id='TxtPareado' onKeypress='if (event.keyCode < 45 || event.keyCode > 57)event.returnValue = false;' placeholder='___'  maxlength='1' size='1' /> </td> <td><p class='tooltip' style='overflow: hidden; white-space: nowrap; text-overflow: ellipsis; max-width: 980px' >" + ListaTemp.get(i).getTextoAlternativa() + "<span>" + ListaTemp.get(i).getTextoAlternativa() + "</span></p></td> <td class='TdKey'>" + ListaTemp.get(i).getIdAlternativaConcepto() + "</td> </tr>";

                }
                Etiquetas = Etiquetas + "</table></div>";

            }

            for (int i = 0; i < terminosPareados.size(); i++) {
                for (int x = 0; x < ListaTemp.size(); x++) {
                    if (ListaTemp.get(x) == terminosPareados.get(i)) {
                        terminosPareados.remove(i);
                    }
                }
            }

            ListaTemp.clear();

        } while (terminosPareados.size() != 0);

        return Etiquetas;

    }

    public String PreguntaCompletacion() throws IOException {

        ArrayList<String> enunciados = new ArrayList<>();

        DefinicionService AccesoDefinicion = new DefinicionService();
        List<Definicion> ListaDefiniciones = AccesoDefinicion.BuscarDefinicionContenido(this.Contenido_id);

        CaracteristicaService AccesoCaracteristica = new CaracteristicaService();
        List<Caracteristica> ListaCaracteristicas = AccesoCaracteristica.BuscarCaracteristicaContenido(this.Contenido_id);

        ObservacionService AccesoObservacion = new ObservacionService();
        List<Observacion> ListaObservaciones = AccesoObservacion.BuscarObservacionContenido(this.Contenido_id);

        ArrayList<String> ConceptosReferenciados = new ArrayList<>();

        for (int i = 0; i < ListaDefiniciones.size(); i++) {
            ConceptosReferenciados.add(ListaDefiniciones.get(i).getConcepto().getNombre());
            enunciados.add(ListaDefiniciones.get(i).getDetalle());
        }

        for (int i = 0; i < ListaCaracteristicas.size(); i++) {
            ConceptosReferenciados.add(ListaCaracteristicas.get(i).getConcepto().getNombre());
            enunciados.add(ListaCaracteristicas.get(i).getDetalle());
        }

        for (int i = 0; i < ListaObservaciones.size(); i++) {
            ConceptosReferenciados.add(ListaObservaciones.get(i).getConcepto().getNombre());
            enunciados.add(ListaObservaciones.get(i).getDetalle());
        }

        POSModel model = new POSModelLoader().load(new File("C:\\Users\\Hector\\Documents\\GitHub\\preguntame\\es-pos-maxent.bin"));

        POSTaggerME tagger = new POSTaggerME(model);

        String EtiquetaFinal = "";

        for (int i = 0; i < enunciados.size(); i++) {

            String EnunciadoFormat = enunciados.get(i).replace(",", "");
            EnunciadoFormat = EnunciadoFormat.replace(".", "");
            EnunciadoFormat = EnunciadoFormat.replace("(", "");
            EnunciadoFormat = EnunciadoFormat.replace(")", "");
            EnunciadoFormat = EnunciadoFormat.replace("'", "");
            EnunciadoFormat = EnunciadoFormat.replace("'", "");

            ArrayList<String> palabras = new ArrayList<>();
            ArrayList<Palabra[]> sentencias = Morfologia(EnunciadoFormat, model, tagger);

            for (int x = 0; x < sentencias.size(); x++) {
                //    System.out.println("-------------------------------------------------------------------");

                for (int j = 0; j < sentencias.get(x).length; j++) {

                    if (sentencias.get(x)[j].Morfologia.substring(0, 1).equals("n") || sentencias.get(x)[j].Morfologia.substring(0, 2).equals("aq") || sentencias.get(x)[j].Morfologia.substring(0, 2).equals("vm")) {
                        palabras.add(sentencias.get(x)[j].Palabra);
                        // System.out.println(sentencias.get(x)[j].Palabra + "   |   " + sentencias.get(x)[j].Morfologia);
                    }

                }
                //   System.out.println("");
            }
            String etiqueta = enunciados.get(i);

            HashSet<String> hashSet = new HashSet<String>(palabras);
            palabras.clear();
            palabras.addAll(hashSet);

            int CantidadPalabras = palabras.size() / 3;
            Collections.shuffle(palabras);

            for (int x = 0; x < CantidadPalabras; x++) {
                //OJO CON LOS PLURALES DENTRO DE LA MISMA ORACION
               // System.out.println(x + "--------------" + palabras.get(x));
                etiqueta = etiqueta.replace(palabras.get(x), "<input type='text' id='" + palabras.get(x) + "' placeholder='_______________________' maxlength='23' size='23' />");

            }

            etiqueta = "<div class='pregunta'><input type='hidden' class='TipoPregunta' value='Completacion' /> <p id='Enunciado'>COMPLETAR ENUNCIADO </p> <input type='hidden' class='ConceptoReferenciado' value='" + ConceptosReferenciados.get(i) + "' /> <p class='Completacion'> " + etiqueta + " </p></div> ";

            EtiquetaFinal = EtiquetaFinal + " " + etiqueta;
            //     System.out.println(etiqueta);
            //     System.out.println("");

        }

        return EtiquetaFinal;

    }

    static ArrayList<Palabra[]> Morfologia(String enunciado, POSModel model, POSTaggerME tagger) throws IOException {

        String input = enunciado;

        input = input.replaceAll("\n", " ");
        int aux = 0;

        ArrayList<Palabra[]> sentencias = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {

            for (int j = i; j < input.length(); j++) {
                if (input.substring(j, j + 1).equals(".") || j == input.length() - 1) {

                    String linea;

                    if (!input.substring(j, j + 1).equals(".")) {
                        linea = input.substring(i, j + 1).trim();

                    } else {
                        linea = input.substring(i, j).trim();

                    }

                    ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(linea));
                    String line;

                    while ((line = lineStream.read()) != null) {

                        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
                        String[] tags = tagger.tag(whitespaceTokenizerLine);

                        POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
                        linea = sample.toString();

                    }

                    String[] tokens = linea.split(" ");
                    Palabra[] ArrayPalabras = new Palabra[tokens.length];

                    for (int y = 0; y < tokens.length; y++) {
                        for (int z = 0; z < tokens[y].length(); z++) {
                            if (tokens[y].substring(z, z + 1).equals("_")) {
                                Palabra palabra = new Palabra(tokens[y].substring(0, z), tokens[y].substring(z + 1, tokens[y].length()));
                                ArrayPalabras[y] = palabra;

                            }

                        }

                    }
                    sentencias.add(ArrayPalabras);

                    aux = j;
                    break;
                }
            }
            i = aux;
        }

        return sentencias;
    }

}
