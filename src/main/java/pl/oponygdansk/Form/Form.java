package pl.oponygdansk.Form;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by magdalena on 05/07/2016.
 */
public class Form {

    private String id;
    private String customerId;
    private String make;
    private String model;
    private String registrationNumber;
    private String year;
    private String tires;
    private String rim;
    private Boolean balancing;
    private Date createdOn;

    public Form(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.customerId = dbObject.get("customerId").toString();
        this.make = dbObject.getString("make");
        this.model = dbObject.getString("model");
        this.registrationNumber = dbObject.getString("registrationNumber");
        this.year = dbObject.getString("year");
        this.tires = dbObject.getString("tires");
        this.rim = dbObject.getString("rim");
        this.balancing = dbObject.getBoolean("balancing");
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

    public String getMake() { return make; }

    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public String getRegistrationNumber() { return registrationNumber; }

    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getYear() { return year; }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTires() { return tires; }

    public void setTires(String tires) { this.tires = tires; }

    public String getRim() { return rim; }

    public void setRim(String rim) { this.rim = rim; }

    public Boolean getBalancing() { return balancing; }

    public void setBalancing(Boolean balancing) { this.balancing = balancing; }

    public Date getCreatedOn() {
        return createdOn;
    }
}
