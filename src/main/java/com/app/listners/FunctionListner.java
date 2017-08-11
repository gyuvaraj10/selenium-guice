package com.app.listners;

import com.app.annotations.Intercept;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by Yuvaraj on 11/08/2017.
 */
public class FunctionListner implements IHook, MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Intercept intercept = methodInvocation.getMethod().getDeclaredAnnotation(Intercept.class);
        if (intercept != null) {
            beforeInterception();
            methodInvocation.proceed();
            afterInterception();
        }
        return null;
    }

    public void beforeInterception() {
        System.out.println("Before Interception");
    }

    public void afterInterception() {
        System.out.println("After Interception");
    }
}
