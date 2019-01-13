package six.ca.droiddailyproject.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-25.
 */

public class RuleDemoTest {
    @Rule
    public MethodNameExample methodNameExample = new MethodNameExample();

    @Before
    public void setup(){
        System.out.println("xxl setup...");
    }

    @Test
    public void assertAdd(){
        Assert.assertEquals(4, 3 + 1);
    }

    @Test
    public void assertMultiple(){
        Assert.assertEquals(6, 2 * 3);
    }
}
