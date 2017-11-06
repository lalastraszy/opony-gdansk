package pl.oponygdansk;

import com.mongodb.*;
import pl.oponygdansk.Car.CarResource;
import pl.oponygdansk.Car.CarService;
import pl.oponygdansk.CarBrand.CarBrandResource;
import pl.oponygdansk.CarBrand.CarBrandService;
import pl.oponygdansk.CarModel.CarModelResource;
import pl.oponygdansk.CarModel.CarModelService;
import pl.oponygdansk.Customer.CustomerResource;
import pl.oponygdansk.Customer.CustomerService;
import pl.oponygdansk.Form.FormResource;
import pl.oponygdansk.Form.FormService;
import pl.oponygdansk.TyreBrand.TyreBrandResource;
import pl.oponygdansk.TyreBrand.TyreBrandService;
import pl.oponygdansk.TyreSize.TyreSizeResource;
import pl.oponygdansk.TyreSize.TyreSizeService;

import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by lalastraszy on 2014-10-19.
 */
public class Bootstrap {

    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        DB mongo = mongo();
        new FormResource(new FormService(mongo));
        new CustomerResource(new CustomerService(mongo));
        new CarResource(new CarService(mongo));
        new TyreBrandResource(new TyreBrandService(mongo));
        new TyreSizeResource(new TyreSizeService(mongo));
        new CarBrandResource(new CarBrandService(mongo));
        new CarModelResource(new CarModelService(mongo));
    }

    private static DB mongo() throws Exception {
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDB("oponygdansk");
        }
        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        String dbname = System.getenv("OPENSHIFT_APP_NAME");
        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        if (db.authenticate(username, password.toCharArray())) {
            return db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }
    }
}
