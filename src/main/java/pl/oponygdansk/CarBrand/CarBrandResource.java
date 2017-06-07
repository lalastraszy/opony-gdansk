package pl.oponygdansk.CarBrand;

import pl.oponygdansk.JsonTransformer;
import spark.Spark;

/**
 * Created by magdalena on 11/05/2017.
 */
public class CarBrandResource {

    private static final String API_CONTEXT = "/api/v1";

    private final CarBrandService carBrandService;

    public CarBrandResource(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
        setupEndpoint();
    }

    private void setupEndpoint() {
        Spark.get(API_CONTEXT + "/carBrands", "application/json", (request, response) ->
                carBrandService.findAll(), new JsonTransformer());
    }
}
