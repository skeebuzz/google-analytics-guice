package com.skeebuzz.gag;

import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.aopalliance.intercept.MethodInterceptor;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

/**
 * Created by si on 3/13/17.
 */
public class GagModule extends AbstractModule {


    private String trackingId;
    private MethodInterceptor trackingInterceptor;

    public GagModule(String defaultTrackingId) {
        this.trackingId = defaultTrackingId;
    }

    @Override
    protected final void configure() {
        requireBinding(AnalyticsService.class);
        trackingInterceptor = new TrackingInterceptor();
        requestInjection(trackingInterceptor);
        bindInterceptor(any(), annotatedWith(TrackEvent.class), trackingInterceptor);
    }

    @Provides
    public GoogleAnalytics googleAnalytics() {
        return new GoogleAnalytics(trackingId);
    }

}
