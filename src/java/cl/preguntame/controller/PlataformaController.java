/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.preguntame.controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@RequestMapping("/plataforma")
public class PlataformaController {

    @RequestMapping("nosotros")
    public String nosotros() {
        return "/usuario/nosotros";
    }

    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public String correo(HttpServletRequest req) {
            
        try{
            String host = "smtp.gmail.com";
            
            Properties prop = System.getProperties();
            
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.user", "hector.riquelme1169@gmail.com");
            prop.put("mail.smtp.password", "rriiqquueellmmee");
            prop.put("mail.smtp.port", 587);
            prop.put("mail.smtp.auth", "true");
            
            Session sesion = Session.getDefaultInstance(prop, null);
            MimeMessage mensaje = new MimeMessage(sesion);
            
            mensaje.setFrom( new InternetAddress());
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress("hector.riquelme1169@gmail.com"));
            mensaje.setSubject("CONTACTO MIS CONCEPTOS");
            mensaje.setText(req.getParameter("mensaje_contacto"));
            
            Transport transport = sesion.getTransport("smtp");
            transport.connect(host,"hector.riquelme1169@gmail.com","rriiqquueellmmee");
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            
            transport.close();
            
        }catch(Exception e){
            
        }
      
        return req.getParameter("mensaje_contacto") + "  -  " + req.getParameter("email_contacto");
    }

}
