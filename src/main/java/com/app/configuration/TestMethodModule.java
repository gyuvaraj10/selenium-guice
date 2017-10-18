package com.app.configuration;

import com.app.scopes.TestMethodScope;
import com.app.scopes.TestMethodScoped;
import com.google.inject.AbstractModule;

/**
 * Created by Yuvaraj on 20/08/2017.
 */
public class TestMethodModule extends AbstractModule {

    TestMethodScope testMethodScope;

    public TestMethodModule() {
        this.testMethodScope = new TestMethodScope();
    }
    @Override
    protected void configure() {
        bindScope(TestMethodScoped.class, testMethodScope);
        bind(TestMethodScope.class).toInstance(testMethodScope);
    }

    public void enter() {
        testMethodScope.enterScope();
    }

    public void exit() {
        testMethodScope.exitScope();
    }
}
