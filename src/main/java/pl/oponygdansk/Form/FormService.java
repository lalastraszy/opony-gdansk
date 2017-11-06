package pl.oponygdansk.Form;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import pl.oponygdansk.Car.CarService;
import pl.oponygdansk.Customer.CustomerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by magdalena on 13/07/2016.
 */
public class FormService {

    private final DB db;
    private final DBCollection collection;
    private final CustomerService customerService;
    private final CarService carService;

    public FormService(DB db) {
        this.db = db;
        this.collection = db.getCollection("form");
        this.customerService = new CustomerService(db);
        this.carService = new CarService(db);
    }

    public void createForm(String body) {
        Form form = new Gson().fromJson(body, Form.class);
        collection.insert(new BasicDBObject("customerId", form.getCustomerId()).
                append("carId", form.getCarId()).
                append("createOn", new Date()));
    }

    public List<Form> findAll() {
        List<Form> forms = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            Form form = new Form((BasicDBObject) dbObject);
            form.setCustomer(customerService.find(form.getCustomerId()));
            form.setCar(carService.find(form.getCarId()));
            forms.add(form);
        }
        return forms;
    }

//    public List<Form> findAllForCustomer(String customerId) {
//        List<Form> forms = new ArrayList<>();
//        DBCursor dbObjects = collection.find(new BasicDBObject().append("customerId", customerId));
//        while (dbObjects.hasNext()) {
//            DBObject dbObject = dbObjects.next();
//            forms.add(new Form((BasicDBObject) dbObject));
//        }
//        return forms;
//    }
}
