package com.example.open_telemetry;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Tracer tracer;

    public HelloController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping("/hello")
    public String hello() {
        Span span = tracer.spanBuilder("hello-span").startSpan();
        try (Scope scope = span.makeCurrent()) {
            // Do something
            return "Hello, world!";
        } finally {
            span.end();
        }
    }
}