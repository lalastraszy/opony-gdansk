package pl.oponygdansk.Form;

import pl.oponygdansk.JsonTransformer;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.delete;

/**
 * Created by magdalena on 13/07/2016.
 */
public class FormResource {

    private static final String API_CONTEXT = "/api/v1";

    private final FormService formService;

    public FormResource(FormService formService) {
        this.formService = formService;
        setupEndpoint();
    }

    private void setupEndpoint() {
        Spark.post(API_CONTEXT + "/forms", "application/json", (request, response) -> {
            formService.createForm(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

//        get(API_CONTEXT + "/forms", "application/json", (request, response) ->
//                formService.findAllForCustomer(request.queryParams("customerId")), new JsonTransformer());

        get(API_CONTEXT + "/forms", "application/json", (request, response) -> {
            List<Form> forms = new ArrayList<>();
            if (request.queryParams("id") != null) {
                forms.add(formService.find(request.queryParams("id")));
            } else {
                forms = formService.findAll();
            }
            return forms;
        }, new JsonTransformer());

//        delete(API_CONTEXT + "/forms/:id", "application/json", (request, response) -> {
//            String id = request.params(":id");
//            if (formService.find(id) == null) {x
//                response.status(404);
//                return "NOT_FOUND";
//            } else {
//                formService.remove(id);
//                return "FORM DELETED";
//            }
//        }, new JsonTransformer());
    }
}
