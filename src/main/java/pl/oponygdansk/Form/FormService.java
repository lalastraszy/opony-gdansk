package pl.oponygdansk.Form;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by magdalena on 13/07/2016.
 */
public class FormService {

    private final DB db;
    private final DBCollection collection;

    public FormService(DB db) {
        this.db = db;
        this.collection = db.getCollection("form");
    }

    public void createForm(String body) {
        Form form = new Gson().fromJson(body, Form.class);
        collection.insert(new BasicDBObject("customerId", form.getCustomerId()).
                append("make", form.getMake()).
                append("model", form.getModel()).
                append("registrationNumber", form.getRegistrationNumber()).
                append("year", form.getYear()).
                append("tires", form.getTires()).
                append("rim", form.getRim()).
                append("balancing", form.getBalancing()).
                append("createOn", new Date()));
    }

    public List<Form> findAllForCustomer(String customerId) {
        List<Form> forms = new ArrayList<>();
        DBCursor dbObjects = collection.find(new BasicDBObject().append("customerId", customerId));
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            forms.add(new Form((BasicDBObject) dbObject));
        }
        return forms;
    }
}
