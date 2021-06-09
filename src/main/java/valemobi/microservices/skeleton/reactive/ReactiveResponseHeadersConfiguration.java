package valemobi.microservices.skeleton.reactive;

import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class ReactiveResponseHeadersConfiguration implements WebFilter {

    @Autowired
    Tracer tracer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        exchange.getResponse()
                .getHeaders()
                .addAll(additionalHeaders(exchange));
        return chain.filter(exchange);
    }

    private MultiValueMap<String, String> additionalHeaders(ServerWebExchange exchange) {

        MultiValueMap<String, String> mhm = new LinkedMultiValueMap<String, String>();
        mhm.add("Method", exchange.getRequest().getMethodValue());
        mhm.add("Context-Path", exchange.getRequest().getURI().toString());
        mhm.add("Trace-id", tracer.currentSpan().context().traceIdString());

        return mhm;
    }
}
