package pl.oponygdansk.Form;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import pl.oponygdansk.Car.Car;
import pl.oponygdansk.Customer.Customer;

import java.util.Date;

/**
 * Created by magdalena on 05/07/2016.
 */
public class Form {

    private String id;
    private String customerId;
    private Customer customer;
    private String carId;
    private Car car;
    private Date createdOn;

    public Form(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.customerId = dbObject.get("customerId").toString();
        this.carId = dbObject.get("carId").toString();
        this.createdOn = dbObject.getDate("createdOn");
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
