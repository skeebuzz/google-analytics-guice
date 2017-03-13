package com.skeebuzz.gag;

import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.brsanthu.googleanalytics.GoogleAnalyticsRequest;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by si on 3/13/17.
 */
@Singleton
public class GoogleAnalyticsService implements AnalyticsService {

    private volatile boolean running = true;

    @Inject
    private GoogleAnalytics googleAnalytics;

    @Override
    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void track(GoogleAnalyticsRequest request) {
        if (running) {
            googleAnalytics.postAsync(request);
        }
    }
}
