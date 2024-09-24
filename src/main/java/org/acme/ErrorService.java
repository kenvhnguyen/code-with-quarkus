package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ErrorService {
    public String msg() {
        System.out.println("Error message is called");
        throw new RuntimeException();
    }
}
