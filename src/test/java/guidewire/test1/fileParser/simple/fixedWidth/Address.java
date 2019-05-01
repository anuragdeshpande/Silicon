package guidewire.test1.fileParser.simple.fixedWidth;

public class Address {
    private String street;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' + '}';
    }
}
