package cl.preguntame.controller;

import cl.preguntame.model.Contenido;
import cl.preguntame.model.Usuario;
import cl.preguntame.service.ContenidoService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/contenido")
public class ContenidoController {

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {

        Usuario user = new Usuario();
        user.setId(Integer.parseInt(req.getParameter("usuario_id")));

        Contenido c = new Contenido();
        c.setNombre(req.getParameter("nombre"));
        c.setUsuario(user);

        return String.valueOf(new ContenidoService().GuardarContenido(c));

    }

    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public Contenido BuscarContenido(HttpServletRequest req) {

        ContenidoService cs = new ContenidoService();
        Contenido c = cs.BuscarContenido(Integer.parseInt(req.getParameter("seleccionado")));
        Contenido p = new Contenido();
        p.setId(c.getId());
        p.setNombre(c.getNombre());
        p.setTexto(c.getTexto());

        return p;
    }

    @ResponseBody
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String Editar(HttpServletRequest req) {

        Contenido c = new Contenido();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setNombre(req.getParameter("nombre"));
        c.setTexto(req.getParameter("texto"));

        Usuario u = new Usuario();
        u.setId(Integer.parseInt(req.getParameter("usuario_id")));

        c.setUsuario(u);

        ContenidoService cs = new ContenidoService();
        cs.ActualizarContenido(c);

        return "true";
    }

}
