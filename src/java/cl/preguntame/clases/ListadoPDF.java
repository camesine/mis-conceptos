/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.preguntame.clases;

import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.ZapfDingbatsList;
import java.util.ArrayList;

/**
 *
 * @author Hector
 */
public class ListadoPDF {
 
    ArrayList<ListItem> items;

    public ListadoPDF(ArrayList<ListItem> items) {
        this.items = items;
    }

    
    
    
    public List getListado(){
        
        List listado = new List();
        for(int i = 0; i < items.size(); i++){
            listado.add(items.get(i));
        }
        
        listado.setIndentationLeft(20);
        return listado;
    }
    
    
}
