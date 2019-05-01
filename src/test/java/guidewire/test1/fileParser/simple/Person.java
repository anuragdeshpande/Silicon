package guidewire.test1.fileParser.simple;

public class Person {
    private String firstName;
    private String lastName;
    private boolean hasDog;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isHasDog() {
        return hasDog;
    }

    public void setHasDog(boolean hasDog) {
        this.hasDog = hasDog;
    }
}
