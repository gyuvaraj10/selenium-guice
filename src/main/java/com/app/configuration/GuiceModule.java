package com.app.configuration;

import com.app.annotations.Step;
import com.app.utils.PropertyMap;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import cucumber.api.guice.CucumberModules;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import cucumber.runtime.java.guice.InjectorSource;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;


public class GuiceModule extends AbstractModule implements InjectorSource {

    private Injector injector;

    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(DriverProvider.class).asEagerSingleton();
        for(Class<?> stepDef: getAllStepDefinitionClasses()){
            bind(stepDef).in(ScenarioScoped.class);
        }
        getAllSteps().forEach(this::bind);
        Names.bindProperties(binder(), getProperties());
        bindListener(Matchers.any(), new PageListner());
    }



    private List<Class<?>> getAllStepDefinitionClasses(){
        List<Class<?>> stepDefinitions = new ArrayList<>();
        List<Class<? extends Annotation>> cucumberAnnotations = new ArrayList<>();
        cucumberAnnotations.add(And.class);
        cucumberAnnotations.add(Given.class);
        cucumberAnnotations.add(Then.class);
        cucumberAnnotations.add(When.class);
        cucumberAnnotations.add(But.class);
        cucumberAnnotations.add(After.class);
        cucumberAnnotations.add(Before.class);
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.forPackages("com.app.tests").setScanners(new MethodAnnotationsScanner());
        Reflections reflections = new Reflections(builder);
        for(Class<? extends Annotation> annotation: cucumberAnnotations){
            Set<Method> methods = reflections.getMethodsAnnotatedWith(annotation);
            if(methods.size() > 0){
                Class<?> stepDefinitionClass = methods.iterator().next().getDeclaringClass();
                if(!stepDefinitions.contains(stepDefinitionClass)) {
                    stepDefinitions.add(stepDefinitionClass);
                }
            }
        }
        return stepDefinitions;
    }

    private List<Class<?>> getAllSteps(){
        List<Class<?>> steps = new ArrayList<>();
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.forPackages("com.app.steps").setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());
        Reflections reflections = new Reflections(builder);
        Set<Class<?>> stepClasses = reflections.getTypesAnnotatedWith(Step.class);
        for(Class<?> stepClass: stepClasses){
            steps.add(stepClass);
        }
        return steps;
    }

    private Properties getProperties() {
        return PropertyMap.getInstance().getLoadedProperties();
    }

    @Override
    public Injector getInjector() {
        if(injector == null) {
            injector = Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, this);
        }
        return injector;
    }
}
