package testNGListeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class RetryListener implements IAnnotationTransformer {
    /**
     * This method will be invoked by TestNG to give you a chance
     * to modify a TestNG annotation read from your test classes.
     * You can change the values you need by calling any of the
     * setters on the ITest interface.
     *
     * Note that only one of the three parameters testClass,
     * testConstructor and testMethod will be non-null.
     *
     * @param testAnnotation The annotation that was read from your
     * test class.
     * @param testClass If the annotation was found on a class, this
     * parameter represents this class (null otherwise).
     * @param testConstructor If the annotation was found on a constructor,
     * this parameter represents this constructor (null otherwise).
     * @param testMethod If the annotation was found on a method,
     * this parameter represents this method (null otherwise).
     */
    @Override
    public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = testAnnotation.getRetryAnalyzer();
        if (retry == null) {
            testAnnotation.setRetryAnalyzer(Retry.class);
        }
    }
}
