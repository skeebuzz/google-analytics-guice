package com.skeebuzz.gag;

import com.brsanthu.googleanalytics.EventHit;
import com.brsanthu.googleanalytics.GoogleAnalyticsRequest;
import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by si on 3/13/17.
 */
class TrackingInterceptor implements MethodInterceptor {

    @Inject
    private AnalyticsService gas;

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object result = null;
        try {
            result = methodInvocation.proceed();
            TrackEvent trackEvent = readTrackingAnnotation(methodInvocation);
            gas.track(convertToGARequest(trackEvent));
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    private GoogleAnalyticsRequest convertToGARequest(TrackEvent trackEvent) {
        return new EventHit(trackEvent.category(), trackEvent.action(), trackEvent.label(), trackEvent.value());
    }

    private TrackEvent readTrackingAnnotation(MethodInvocation methodInvocation) {
        Method method = methodInvocation.getMethod();
        return method.getAnnotation(TrackEvent.class);
    }
}
