/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.preguntame.service;

import cl.preguntame.dao.TestDAO;
import cl.preguntame.model.Test;
import java.util.List;

/**
 *
 * @author Hector
 */
public class TestService implements ITestService{
    TestDAO Acceso;

    public TestService() {
        this.Acceso = new TestDAO();
    }
    
    @Override
    public int GuardarTest(Test c) {
        return Acceso.Guardar(c);
    }

    @Override
    public void ActualizarTest(Test c) {
        Acceso.Actualizar(c);
    }

    @Override
    public void EliminarTest(Test c) {
        Acceso.Eliminar(c);
    }

    @Override
    public Test BuscarTest(int id) {
        return Acceso.Buscar(id);
    }
    
    @Override
    public List<Test> ListarTest() {
        return Acceso.Listar();
    }

    @Override
    public List<Test> BuscarTestContenido(int contenido_id) {
        return Acceso.BuscarContenido(contenido_id);
    }

   
}
