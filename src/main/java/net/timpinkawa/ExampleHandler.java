package net.timpinkawa;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;

public class ExampleHandler implements RequestHandler<ScheduledEvent, Void> {
    @Override
    public Void handleRequest(ScheduledEvent input, Context context) {
        context.getLogger().log("input: " + input);

        return null;
    }
}
