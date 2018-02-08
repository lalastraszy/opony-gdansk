package pl.oponygdansk.Form;

import com.mongodb.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.bson.types.ObjectId;
import pl.oponygdansk.Car.CarService;
import pl.oponygdansk.Customer.CustomerService;
import pl.oponygdansk.Wheel.WheelService;

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
    private final WheelService wheelService;

    public FormService(DB db) {
        this.db = db;
        this.collection = db.getCollection("form");
        this.customerService = new CustomerService(db);
        this.carService = new CarService(db);
        this.wheelService = new WheelService(db);
    }

    public Form createForm(Form form) {
        BasicDBObject formDbObject = new BasicDBObject("customerId", form.getCustomerId()).
                append("carId", form.getCarId()).
                append("createOn", new Date());
        collection.insert(formDbObject);
        return new Form(formDbObject);
    }

    public Form updateForm(Form form) {
        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(form.getId()));
        BasicDBObject formDbObject = new BasicDBObject().append("customerId", form.getCustomerId()).
                append("carId", form.getCarId()).
                append("modifyOn", new Date());
        BasicDBObject updateQuery = new BasicDBObject().append("$set", formDbObject);
        collection.update(searchQuery, updateQuery);
        wheelService.updateWheels(form.getCar().getId(), form.getCar().getWheels());
        return this.find(form.getId());
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

    public Form find(String id) {
        Form form = new Form((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
        form.setCustomer(customerService.find(form.getCustomerId()));
        form.setCar(carService.find(form.getCarId()));
        return form;
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
