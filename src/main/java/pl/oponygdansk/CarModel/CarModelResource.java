package pl.oponygdansk.CarModel;

import pl.oponygdansk.JsonTransformer;
import spark.Spark;

import static spark.Spark.get;

/**
 * Created by magdalena on 11/05/2017.
 */
public class CarModelResource {

    private static final String API_CONTEXT = "/api/v1";

    private final CarModelService carModelService;

    public CarModelResource(CarModelService carModelService) {
        this.carModelService = carModelService;
        setupEndpoint();
    }

    private void setupEndpoint() {
        Spark.get(API_CONTEXT + "/carModels/:id", "application/json", (request, response) ->
                carModelService.find(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "/carModels", "application/json", (request, response) ->
                carModelService.findAll(), new JsonTransformer());

    }
}
