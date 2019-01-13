package six.ca.droiddailyproject.mockito;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-17.
 */

@Implements(Person.class)
public class ShadowPerson {

    @Implementation
    public String getInfo(){
        return "mockInformation";
    }
}
