package uzap.com.ua.twohourdelivery.data;

/**
 * Created by marazmone on 12.12.2015.
 */
public class Order {
    private int id;
    private long time;
    private String addressFrom;
    private String addressTo;
    private int pricePackage;
    private int priceDelivery;

    public Order() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public int getPricePackage() {
        return pricePackage;
    }

    public void setPricePackage(int pricePackage) {
        this.pricePackage = pricePackage;
    }

    public int getPriceDelivery() {
        return priceDelivery;
    }

    public void setPriceDelivery(int priceDelivery) {
        this.priceDelivery = priceDelivery;
    }
}
