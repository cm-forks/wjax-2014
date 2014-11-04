package io.fabric8.quickstarts.rest.bean;

import com.wordnik.swagger.annotations.ApiParam;
import io.fabric8.quickstarts.rest.model.Customer;
import io.fabric8.quickstarts.rest.model.Order;
import io.fabric8.quickstarts.rest.service.CustomerService;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class CustomerServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    long currentId = 123;
    Map<Long, Customer> customers = new HashMap<Long, Customer>();
    Map<Long, Order> orders = new HashMap<Long, Order>();

    public CustomerServiceImpl() {
        init();
    }

    public Customer getCustomer(String id) {
        LOG.info("Invoking getCustomer, Customer id is: {}", id);
        long idNumber = Long.parseLong(id);
        Customer c = customers.get(idNumber);
        return c;
    }

    public Response addCustomer(@Header("type") String type, Customer customer) {
        LOG.info("Invoking addCustomer, Customer name is: {}", customer.getName());
        customer.setId(++currentId);

        customers.put(customer.getId(), customer);
        if (type.equals("json")) {
            return Response.ok().type("application/json").entity(customer).build();
        } else {
            return Response.ok().type("application/xml").entity(customer).build();
        }
    }

    public Response updateCustomer(Customer customer) {
        LOG.info("Invoking updateCustomer, Customer name is: {}", customer.getName());
        Customer c = customers.get(customer.getId());
        Response r;
        if (c != null) {
            customers.put(customer.getId(), customer);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    public Response deleteCustomer(@Header("type") String type, String id){
        Customer c = customers.get(id);
        customers.remove(id);
        if (type.equals("json")) {
            return Response.ok().type("application/json").entity(c).build();
        } else {
            return Response.ok().type("application/xml").entity(c).build();
        }
    }

    public Order getOrder(String orderId) {
        LOG.info("Invoking getOrder, Order id is: {}", orderId);
        long idNumber = Long.parseLong(orderId);
        Order c = orders.get(idNumber);
        return c;
    }

    /**
     * The init method is used by the constructor to insert a Customer and Order object into the local data map
     * for testing purposes.
     */
    final void init() {
        Customer c = new Customer();
        c.setName("John");
        c.setId(123);
        customers.put(c.getId(), c);

        Order o = new Order();
        o.setDescription("order 223");
        o.setId(223);
        orders.put(o.getId(), o);
    }
}
