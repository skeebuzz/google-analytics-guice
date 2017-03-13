# google-analytics-guice
Java library to easily hit Google Analytics using annotations and guice.

Just load the GagModule, giving it your Google Analytics tracking ID, and then annotate any methods with @TrackEvent to fire off a Google Analytics <a href="https://developers.google.com/analytics/devguides/collection/protocol/v1/devguide#event">event.</a>

For example:

@TrackEvent(category = "user", action="signup")
public void registerUser(User user) { }

Only fires the event if no exception is thrown.

Uses the <a href="https://github.com/brsanthu/google-analytics-java">google-analytics-java</a> library to hit Google.

SimpleTest contains a simple example.

