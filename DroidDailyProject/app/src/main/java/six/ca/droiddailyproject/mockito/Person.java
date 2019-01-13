package six.ca.droiddailyproject.mockito;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-17.
 */

public class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public String getPersonInfo(){
        return "name = " + name;
    }
}
