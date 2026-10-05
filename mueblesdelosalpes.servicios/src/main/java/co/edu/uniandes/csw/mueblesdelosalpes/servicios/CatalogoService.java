package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Catalogo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogoService {

    @EJB
    private IServicioCatalogoMockLocal catalogoEjb;

    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return catalogoEjb.darMuebles();
    }

    @POST
    @Path("muebles/")
    public Response agregarMueble(Mueble mueble) {
        catalogoEjb.agregarMueble(mueble);
        return Response.status(Response.Status.CREATED).entity(mueble).build();
    }

    @PUT
    @Path("muebles/{id}")
    public Mueble actualizarMueble(@PathParam("id") long id, Mueble mueble) {
        mueble.setReferencia(id);
        catalogoEjb.actualizarMueble(mueble);
        return mueble;
    }

    @DELETE
    @Path("muebles/{id}")
    public Response eliminarMueble(@PathParam("id") long id) {
        catalogoEjb.eliminarMueble(id);
        return Response.noContent().build();
    }
}
