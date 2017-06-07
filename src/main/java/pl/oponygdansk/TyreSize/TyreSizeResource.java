package pl.oponygdansk.TyreSize;

import pl.oponygdansk.JsonTransformer;
import spark.Spark;

/**
 * Created by magdalena on 24/04/2017.
 */
public class TyreSizeResource {

    private static final String API_CONTEXT = "/api/v1";

    private final TyreSizeService tyreSizeService;

    public TyreSizeResource(TyreSizeService tyreSizeService) {
        this.tyreSizeService = tyreSizeService;
        setupEndpoint();
    }

    private void setupEndpoint() {
        Spark.get(API_CONTEXT + "/tyreSizes", "application/json", (request, response) ->
                tyreSizeService.findAll(), new JsonTransformer());
    }
}
