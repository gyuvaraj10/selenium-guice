package runners;

import cucumber.api.junit.Cucumber;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.junit.JUnitReporter;
import org.apache.http.annotation.Obsolete;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Yuvaraj on 27/07/2017.
 */
@Obsolete
public class CucumberGalenRunner extends Cucumber {

    private RuntimeOptionsFactory runtimeOptionsFactory;
    private RuntimeOptions runtimeOptions;

    public CucumberGalenRunner(Class clazz) throws InitializationError, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        super(clazz);
        runtimeOptionsFactory = new RuntimeOptionsFactory(clazz);
        runtimeOptions = runtimeOptionsFactory.create();
        runtimeOptions.getClass();
    }

    public void run(RunNotifier notifier) {
        super.run(notifier);

    }

    public void getFormatter() throws Exception{
        Field field = this.getClass().getDeclaredField("jUnitReporter");
        field.setAccessible(true);
        JUnitReporter jUnitReporter = (JUnitReporter)field.get("jUnitReporter");
//        jUnitReporter.uri();

    }

}
