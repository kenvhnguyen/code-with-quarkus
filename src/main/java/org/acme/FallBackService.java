package org.acme;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class FallBackService implements FallbackHandler<String> {
    @Override
    public String handle(ExecutionContext executionContext) {
        return "Sorry for the inconvenience";
    }
}
