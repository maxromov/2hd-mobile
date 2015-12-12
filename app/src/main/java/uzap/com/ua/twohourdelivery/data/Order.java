package uzap.com.ua.twohourdelivery.data;

/**
 * Created by marazmone on 12.12.2015.
 */
public class Order {
    private String time;
    private String addressFrom;
    private String addressTo;
    private String price;

    public Order() {
    }

    public Order(String time, String addressFrom, String addressTo, String price) {
        this.time = time;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
