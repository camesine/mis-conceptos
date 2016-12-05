/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.preguntame.service;

import cl.preguntame.model.Test;
import java.util.List;

/**
 *
 * @author Hector
 */
public interface ITestService {
 
    public int GuardarTest(Test c);

    public void ActualizarTest(Test c);

    public void EliminarTest(Test c);

    public Test BuscarTest(int id);
    
    public List<Test> ListarTest();
    
    public List<Test> BuscarTestContenido(int contenido_id);
    
}
