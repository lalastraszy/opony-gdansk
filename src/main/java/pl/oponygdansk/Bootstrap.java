package pl.oponygdansk;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

/**
 * Created by lalastraszy on 2014-10-19.
 */
public class Bootstrap {

        public static void main(String[] args) {
            get("/", (request, response) -> "Hello World");
        }
}
