package six.ca.droiddailyproject.mockito;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-04.
 */

public class MockitoHelloWorld {

    public int add(int a, int b) {
        return a + b;
    }

    public void print(MyString str) {
        str.print();
    }

    public double divide(double a, double b) {
        try {
            return a / b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
