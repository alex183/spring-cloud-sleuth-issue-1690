package valemobi.microservices.skeleton.reactive;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Log
@RequestMapping(path = "/nuggets")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class NuggetsController {
    private final WebClient defaultWebClient;

    @GetMapping
    public Mono<ResponseEntity<Nuggets>> getNuggets() {
        WebClient webClient = defaultWebClient.mutate()
                .baseUrl("https://api.agify.io/")
                .build();
        Mono<Nuggets> nuggetsMono = webClient.get()
                .uri("?name=bella")
                .retrieve()
                .bodyToMono(Nuggets.class);
        log.info("just testing log");

        return nuggetsMono
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
