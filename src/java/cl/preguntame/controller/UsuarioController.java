package cl.preguntame.controller;

import cl.preguntame.model.TipoUsuario;
import cl.preguntame.model.Usuario;
import cl.preguntame.service.UsuarioService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usuario")

public class UsuarioController {

    @RequestMapping("index")
    public String index(HttpServletRequest req) {
        if (!req.getSession().getAttributeNames().hasMoreElements()) {
            return "/usuario/index";
        } else {
            return "redirect:/usuario/plataforma";
        }
    }

    @RequestMapping("logout")
    public String Logout(HttpServletRequest req) throws ServletException {
        req.getSession().invalidate();
        return "redirect:/usuario/index";

    }

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {
        TipoUsuario t = new TipoUsuario();
        t.setId(1);
        Usuario u = new Usuario(t, req.getParameter("correo"), req.getParameter("nombre"), req.getParameter("apellido"), req.getParameter("pass"));
        return String.valueOf(new UsuarioService().GuardarUsuario(u));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(HttpServletRequest req) throws IOException, InterruptedException {

        if (new UsuarioService().Login(req.getParameter("correo"), req.getParameter("pass"))) {
            req.getSession().setAttribute("correo", req.getParameter("correo"));
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("plataforma")
    public String plataforma(HttpServletRequest req, Map<String, Object> map) {

        List<Usuario> lista = new UsuarioService().BuscarUsuario((String) req.getSession().getAttribute("correo"));
        map.put("usuario", lista.get(0));
        return "usuario/plataforma";
    }

    @RequestMapping("perfil")
    public String perfil(HttpServletRequest req, Map<String, Object> map) {

        List<Usuario> lista = new UsuarioService().BuscarUsuario((String) req.getSession().getAttribute("correo"));
        map.put("usuario", lista.get(0));
        return "usuario/perfil";
    }

    @ResponseBody
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String Editar(HttpServletRequest req) {

        UsuarioService acceso = new UsuarioService();
        
        
        if (String.valueOf(acceso.BuscarUsuario(Integer.parseInt(req.getParameter("id"))).getContrasena()).equals(String.valueOf(req.getParameter("pass")))) {
            
            Usuario u = new Usuario();
            u.setId(Integer.parseInt(req.getParameter("id")));
            u.setCorreo(req.getParameter("correo"));
            u.setNombre(req.getParameter("nombre"));
            u.setApellido(req.getParameter("apellido"));
            u.setContrasena(req.getParameter("pass"));
            TipoUsuario tipo = new TipoUsuario();
            tipo.setId(1);
            u.setTipoUsuario(tipo);
            acceso.ActualizarUsuario(u);
            return "true";

        } else {
            
            return "false";
        }

    }

}
