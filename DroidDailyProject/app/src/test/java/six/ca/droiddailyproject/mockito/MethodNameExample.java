package six.ca.droiddailyproject.mockito;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-25.
 */

public class MethodNameExample implements TestRule {
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String className = description.getClassName();
                String methodName = description.getMethodName();
                System.out.println("xxl before calling: className = " + className);

                base.evaluate();

                System.out.println("xxl after : method name: " + methodName);
            }
        };
    }
}
