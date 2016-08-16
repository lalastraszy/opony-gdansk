package pl.oponygdansk.Customer;

import com.google.gson.Gson;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lalastraszy on 2014-12-20.
 */
public class CustomerService {

    private final DB db;
    private final DBCollection collection;

    public CustomerService(DB db) {
        this.db = db;
        this.collection = db.getCollection("customer");
    }

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            customers.add(new Customer((BasicDBObject) dbObject));
        }
        return customers;
    }

    public void createCustomer(String body) {
        Customer customer = new Gson().fromJson(body, Customer.class);
        collection.insert(new BasicDBObject("firstName", customer.getFirstName()).
                append("secondName", customer.getSecondName()).
                append("email", customer.getEmail()).
                append("phone", customer.getPhone()).
                append("business", customer.getBusiness()).
                append("sex", customer.getSex()).
                append("createOn", new Date()));
    }

    public Customer find(String id) {
        return new Customer((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    public Customer update(String id, String body) {
        Customer customer = new Gson().fromJson(body, Customer.class);
/*        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
        return this.find(todoId);*/
        return null;
    }

    public void remove(String id) {
        collection.remove(new BasicDBObject().append("_id", new ObjectId(id)));
    }

}
