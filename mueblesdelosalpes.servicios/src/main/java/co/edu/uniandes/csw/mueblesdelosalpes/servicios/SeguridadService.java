package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.AutenticacionException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioSeguridadMockLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Seguridad")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguridadService {

    @EJB
    private IServicioSeguridadMockLocal seguridadEjb;

    @POST
    @Path("login/")
    public Usuario ingresar(Credenciales credenciales) {
        try {
            return seguridadEjb.ingresar(credenciales.getLogin(), credenciales.getContrasena());
        } catch (AutenticacionException ex) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build());
        }
    }

    public static class Credenciales {
        private String login;
        private String contrasena;
        public Credenciales() { }
        public String getLogin() { return login; }
        public void setLogin(String login) { this.login = login; }
        public String getContrasena() { return contrasena; }
        public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    }
}
