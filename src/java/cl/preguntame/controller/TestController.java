/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.preguntame.controller;

import cl.preguntame.model.Contenido;
import cl.preguntame.model.Test;
import cl.preguntame.service.TestService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Hector
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {
        Test test = new Test();
        test.setResultados(String.valueOf(req.getParameter("resultados")));

        Date fecha = new Date();

        test.setFecha(fecha.toString());

        Contenido contenido = new Contenido();
        contenido.setId(Integer.parseInt(req.getParameter("contenido_id")));

        test.setContenido(contenido);

        TestService acceso = new TestService();
        acceso.GuardarTest(test);

        return String.valueOf("ok");

    }

    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public List<Test> Buscar(HttpServletRequest req) {

        TestService acceso = new TestService();
        List<Test> lista = acceso.BuscarTestContenido(Integer.parseInt(req.getParameter("seleccionado")));
        List<Test> retorno = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            Test c = new Test();
            c.setId(lista.get(i).getId());
            c.setResultados(lista.get(i).getResultados());
            c.setFecha(lista.get(i).getFecha());
            retorno.add(c);

        }

        return retorno;

    }
    

}
