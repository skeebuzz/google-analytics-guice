package com.skeebuzz.gag;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by si on 3/13/17.
 */
public class SimpleTest {

    private AnalyticsService analyticsServiceMock;
    private Injector injector;

    public static class Dummy {
        @TrackEvent(category = "test-category", action="test-action", label = "test-label", value = 5)
        public void tracked() {
        }

        public void notTracked() {
        }
    }

    @Before
    public void setUp() {
        analyticsServiceMock = mock(AnalyticsService.class);
        Module module = Modules.override(new GagModule("UA-test-123")).with(new TestModule(analyticsServiceMock));
        injector = Guice.createInjector(module);
    }

    @Test
    public void testInjection() throws Exception {
        injector.getProvider(AnalyticsService.class);
    }

    @Test
    public void testIntercept() throws Exception {
        Dummy dummy = injector.getInstance(Dummy.class);
        dummy.tracked();
        verify(analyticsServiceMock, times(1)).track(ArgumentMatchers.any());
        dummy.notTracked();
        // still just called once
        verify(analyticsServiceMock, times(1)).track(ArgumentMatchers.any());
    }



    private class TestModule extends AbstractModule {

        private final AnalyticsService fakeAnalyticsService;

        public TestModule(AnalyticsService fakeAnalyticsService) {
            this.fakeAnalyticsService = fakeAnalyticsService;
        }

        @Override
        protected void configure() {
            bind(AnalyticsService.class).toInstance(fakeAnalyticsService);
        }
    }

}
