package io.fabric8.quickstarts.rest.service;

import io.fabric8.quickstarts.rest.model.Product;
import io.fabric8.quickstarts.rest.model.Order;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public interface OrderService {

    /**
     * This method is mapped to an HTTP GET of '/', relative to the URL that point to this Order resource itself.
     * <p/>
     * The method returns the Order object itself - for creating the HTTP response, this object is marshaled into XML using
     * JAXB.
     * <p/>
     * For example: if surfing to 'http://localhost:8181/cxf/crm/customerservice/orders/223' will show you the information of
     * order 223 in XML format (as defined in CustomerService's getOrder() method), you can access product 323 in that order by
     * accessing 'http://localhost:8181/cxf/crm/customerservice/orders/223/products/323'
     */
    @GET
    @Path("/")
    @Produces("application/xml")
    public Order getThisOrder();

    /**
     * This method is mapped to an HTTP GET of 'products/{productId}', relative to the URL that point to this Order resource
     * itself.
     * The value for {productId} will be passed to this message as a parameter, using the @PathParam annotation.
     * <p/>
     * The method returns an Product object - for creating the HTTP response, this object is marshaled into XML using JAXB.
     * <p/>
     * For example: accessing 'http://localhost:8181/cxf/crm/customerservice/orders/223/products/323' will first trigger the
     * CustomerService's getOrder() method to return the Order instance for order 223 and afterwards, it will use the remaining
     * part of the URI ('products/323') to map to this method and return the product details for product 323 in this order.
     */
    @GET
    @Path("products/{productId}/")
    @Produces("application/xml")
    public Product getProduct(@PathParam("productId") int productId);
}
