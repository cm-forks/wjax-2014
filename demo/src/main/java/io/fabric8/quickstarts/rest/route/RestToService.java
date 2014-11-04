package io.fabric8.quickstarts.rest.route;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

public class RestToService extends RouteBuilder {

    @EndpointInject(ref = "cxfrs-demo")
    private Endpoint CXF_RS_ENDPOINT_SERVER_URI;

    @Override
    public void configure() {

        from(CXF_RS_ENDPOINT_SERVER_URI)
           .setExchangePattern(ExchangePattern.InOut)
           .log("What we get : ${body}")
           .choice()
                // GetCustomer
                .when().simple("${header.operationName} contains 'getCustomer'")
                  .log("*** GetCustomer service will be called ***")
                  .beanRef("customerService","getCustomer")

                // AddCustomer
                .when().simple("${header.operationName} == 'addCustomer'")
                  .log("*** AddCustomer service will be called ***")
                  .setHeader("type").constant("json")
                  .beanRef("customerService","addCustomer")

                // Update Customer
                .when().simple("${header.operationName} == 'updateCustomer'")
                .log("*** Update Customer service will be called ***")
                .beanRef("customerService","updateCustomer")

                // DeleteCustomer
                .when().simple("${header.operationName} == 'deleteCustomer'")
                .log("*** Delete Customer service will be called ***")
                .setHeader("type").constant("json")
                .beanRef("customerService","deleteCustomer")

                .otherwise()
                  .transform().constant("Operation not found");

        //                  .convertBodyTo(Object[].class)

    }

}
