package edu.good9016csumb.projectreview.Helperobjects;

/**
 * Created by alyssiagoodwin on 5/9/17.
 */

public class Transactions {


    public String type;
    public String user;
    public String title;
    public String pickup;
    public String returndate;
    public int reservation;

    public Transactions()
    {
        type ="";
        user ="";
        title ="";
        pickup="";
        returndate="";
        reservation=0;
    }

   public Transactions(String type, String user,String title,String pickup,String returndate, String reser)
    {
        this.type = type;
        this.title = title;
        this.pickup = pickup;
        this.returndate = returndate;
        reservation = Integer.parseInt(reser);
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(String res) {
        reservation = Integer.parseInt(res);
    }

    public String toString() {
        return "Book{" +
                "type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", title=" + title + '\'' +
                ", pickup='" + pickup + '\'' +
                ", returndate='" + returndate + '\'' +
                ", reservation='" + reservation +
                '}';
    }


}
