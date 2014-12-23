package pl.oponygdansk;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by lalastraszy on 2014-12-23.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
