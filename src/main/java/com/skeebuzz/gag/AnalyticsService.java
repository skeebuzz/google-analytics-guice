package com.skeebuzz.gag;

import com.brsanthu.googleanalytics.GoogleAnalyticsRequest;
import com.google.inject.ImplementedBy;

/**
 * Created by si on 3/13/17.
 */
@ImplementedBy(GoogleAnalyticsService.class)
public interface AnalyticsService {

    void setRunning(boolean running);

    void track(GoogleAnalyticsRequest request);

}
