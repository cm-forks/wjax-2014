package io.fabric8.quickstarts.rest.bean;

import io.fabric8.quickstarts.rest.model.Order;
import io.fabric8.quickstarts.rest.model.Product;
import io.fabric8.quickstarts.rest.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

public class OrderServiceImpl {

    private Order o;
    private Product p;
    private Map<Long, Product> products = new HashMap<Long, Product>();
    private Map<Long, Order> orders = new HashMap<Long, Order>();

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    public void OrderSrviceImpl() {
        init();
    }

    public Order getThisOrder() {
        return o;
    }

    public Product getProduct(int productId) {
        LOG.info("----invoking getProduct with id: " + productId);
        Product p = products.get(new Long(productId));
        return p;
    }

    public void init() {
        p = new Product();
        p.setId(323);
        p.setDescription("product 323");
        products.put(p.getId(), p);

        o = new Order();
        o.setDescription("order 223");
        o.setId(223);
        orders.put(o.getId(), o);
    }

}
