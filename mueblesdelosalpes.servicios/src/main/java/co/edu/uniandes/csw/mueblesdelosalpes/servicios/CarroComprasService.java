package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCarritoMockLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/CarroCompras")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarroComprasService {

    @EJB
    private IServicioCarritoMockLocal carroEjb;

    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return carroEjb.getInventario();
    }

    @GET
    @Path("total/")
    public ResumenCarrito getResumen() {
        return new ResumenCarrito(carroEjb.getPrecioTotalInventario(), carroEjb.getTotalUnidades());
    }

    @POST
    @Path("agregar/")
    public List<Mueble> agregarMuebles(List<Mueble> muebles) {
        for (Mueble mueble : muebles) {
            carroEjb.agregarItem(mueble);
        }
        return carroEjb.getInventario();
    }

    @PUT
    @Path("muebles/")
    public List<Mueble> reemplazarCarrito(List<Mueble> muebles) {
        carroEjb.setInventario(new ArrayList<Mueble>(muebles));
        carroEjb.recalcularInventarioTotal();
        return carroEjb.getInventario();
    }

    @DELETE
    @Path("borrar/")
    public Response eliminarMuebles(List<Mueble> muebles) {
        for (Mueble mueble : muebles) {
            carroEjb.removerItem(mueble, true);
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("limpiar/")
    public Response limpiarCarrito() {
        carroEjb.limpiarLista();
        carroEjb.recalcularInventarioTotal();
        return Response.noContent().build();
    }

    @POST
    @Path("comprar/")
    public Response comprar(Usuario usuario) {
        carroEjb.comprar(usuario);
        return Response.ok().build();
    }

    public static class ResumenCarrito {
        private double precioTotal;
        private int totalUnidades;

        public ResumenCarrito() { }
        public ResumenCarrito(double precioTotal, int totalUnidades) {
            this.precioTotal = precioTotal;
            this.totalUnidades = totalUnidades;
        }
        public double getPrecioTotal() { return precioTotal; }
        public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }
        public int getTotalUnidades() { return totalUnidades; }
        public void setTotalUnidades(int totalUnidades) { this.totalUnidades = totalUnidades; }
    }
}
