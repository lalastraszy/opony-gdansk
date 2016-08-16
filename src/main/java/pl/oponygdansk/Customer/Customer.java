package pl.oponygdansk.Customer;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by lalastraszy on 2014-12-20.
 */
public class Customer {

    private String id;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;
    private String business;
    private String sex;
    private Date createdOn;

    public Customer(BasicDBObject dbObject){
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.firstName = dbObject.getString("firstName");
        this.secondName = dbObject.getString("secondName");
        this.email = dbObject.getString("email");
        this.phone = dbObject.getString("phone");
        this.business = dbObject.getString("business");
        this.sex = dbObject.getString("sex");
        this.createdOn = dbObject.getDate("createdOn");
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBusiness() { return business; }

    public void setBusiness(String business) { this.business = business; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public Date getCreatedOn() {
        return createdOn;
    }
}
