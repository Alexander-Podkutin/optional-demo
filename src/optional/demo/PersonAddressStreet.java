package optional.demo;

/**
 * Класс описывающий  улицу, в адресе персоны.
 * @author Alexander Podkutin
 *
 */
public class PersonAddressStreet {
    
    public PersonAddressStreet(String streetName) {
        this.streetName = streetName;
    }
    
    private String streetName;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
