package pl.oponygdansk.Car;

import com.google.gson.Gson;
import pl.oponygdansk.JsonTransformer;
import spark.Spark;

import static spark.Spark.get;

/**
 * Created by magdalena on 05/11/2016.
 */
public class CarResource {

    private static final String API_CONTEXT = "/api/v1";

    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
        setupEndpoint();
    }

    private void setupEndpoint() {
        Spark.post(API_CONTEXT + "/cars", "application/json", (request, response) -> {
            Car car = new Gson().fromJson(request.body(), Car.class);
            carService.createCar(car);
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/cars", "application/json", (request, response) ->
                carService.findAll(), new JsonTransformer());

    }
}
