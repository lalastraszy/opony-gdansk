package pl.oponygdansk;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 * Created by lalastraszy on 2014-12-23.
 */
public class CustomerResource {

    private static final String API_CONTEXT = "/api/v1";

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
        setupEndpoint();
    }

    private void setupEndpoint() {
        post(API_CONTEXT + "/customers", "application/json", (request, response) -> {
            customerService.createCustomer(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "customers/:id", "application/json", (request, response) ->
            customerService.find(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "customers", "application/json", (request, response) ->
        customerService.findAll(), new JsonTransformer());
    }
}
