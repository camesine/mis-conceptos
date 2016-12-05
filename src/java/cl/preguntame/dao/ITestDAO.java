/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAO;
import cl.preguntame.model.Test;
import java.util.List;

/**
 *
 * @author Hector
 */
public interface ITestDAO extends GenericDAO<Test, Number> {

    List<Test> BuscarContenido(int contenido);
}
