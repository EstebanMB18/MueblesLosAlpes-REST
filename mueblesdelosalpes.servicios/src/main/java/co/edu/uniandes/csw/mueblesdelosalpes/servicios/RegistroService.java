package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioRegistroMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Registro")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistroService {

    @EJB
    private IServicioRegistroMockLocal registroEjb;

    @GET
    @Path("clientes/")
    public List<Usuario> getClientes() {
        return registroEjb.darClientes();
    }

    @POST
    @Path("clientes/")
    public Response registrar(Usuario usuario) {
        try {
            registroEjb.registrar(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (OperacionInvalidaException ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build());
        }
    }

    @PUT
    @Path("clientes/{login}")
    public Usuario actualizar(@PathParam("login") String login, Usuario usuario) {
        usuario.setLogin(login);
        registroEjb.actualizarCliente(usuario);
        return usuario;
    }

    @DELETE
    @Path("clientes/{login}")
    public Response eliminar(@PathParam("login") String login) {
        try {
            registroEjb.eliminarCliente(login);
            return Response.noContent().build();
        } catch (OperacionInvalidaException ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build());
        }
    }
}
