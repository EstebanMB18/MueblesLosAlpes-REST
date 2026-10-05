package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Vendedor;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioVendedoresMockLocal;
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

@Path("/Vendedores")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendedoresService {

    @EJB
    private IServicioVendedoresMockLocal vendedoresEjb;

    @GET
    @Path("vendedores/")
    public List<Vendedor> getVendedores() {
        return vendedoresEjb.getVendedores();
    }

    @POST
    @Path("vendedores/")
    public Response agregar(Vendedor vendedor) {
        try {
            vendedoresEjb.agregarVendedor(vendedor);
            return Response.status(Response.Status.CREATED).entity(vendedor).build();
        } catch (OperacionInvalidaException ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build());
        }
    }

    @PUT
    @Path("vendedores/{id}")
    public Vendedor actualizar(@PathParam("id") long id, Vendedor vendedor) {
        vendedor.setIdentificacion(id);
        vendedoresEjb.actualizarVendedor(vendedor);
        return vendedor;
    }

    @DELETE
    @Path("vendedores/{id}")
    public Response eliminar(@PathParam("id") long id) {
        try {
            vendedoresEjb.eliminarVendedor(id);
            return Response.noContent().build();
        } catch (OperacionInvalidaException ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build());
        }
    }
}
