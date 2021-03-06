package pl.oponygdansk.Customer;

import com.google.gson.Gson;
import pl.oponygdansk.JsonTransformer;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.delete;

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
        Spark.post(API_CONTEXT + "/customers", "application/json", (request, response) -> {
            Customer customer = new Gson().fromJson(request.body(), Customer.class);
            customer = customerService.createCustomer(customer);
            response.status(201);
            return customer;
        }, new JsonTransformer());

        get(API_CONTEXT + "/customers/:id", "application/json", (request, response) ->
            customerService.find(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "/customers", "application/json", (request, response) ->
            customerService.findAll(), new JsonTransformer());

        delete(API_CONTEXT + "/customers/:id", "application/json", (request, response) -> {
            String id = request.params(":id");
            if (customerService.find(id) == null) {
                response.status(404);
                return "NOT_FOUND";
            } else {
                customerService.remove(id);
                return "CUSTOMER DELETED";
            }
        }, new JsonTransformer());
    }
}
