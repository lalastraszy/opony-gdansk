package pl.oponygdansk.TyreBrand;

import pl.oponygdansk.JsonTransformer;
import spark.Spark;

/**
 * Created by magdalena on 10/04/2017.
 */
public class TyreBrandResource {

    private static final String API_CONTEXT = "/api/v1";

    private final TyreBrandService tyreBrandService;

    public TyreBrandResource(TyreBrandService tyreBrandService) {
        this.tyreBrandService = tyreBrandService;
        setupEndpoint();
    }

    private void setupEndpoint() {
        Spark.get(API_CONTEXT + "/tyreBrands", "application/json", (request, response) ->
            tyreBrandService.findAll(), new JsonTransformer());
    }
}
