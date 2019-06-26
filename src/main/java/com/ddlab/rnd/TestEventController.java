package com.ddlab.rnd;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddlab.rnd.model.CGModel;

import reactor.core.publisher.Flux;

@RestController
public class TestEventController {

	@GetMapping(path = "/cg/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	APPLICATION_STREAM_JSO
//	@GetMapping(path = "/cg/event", produces = MediaType.APPLICATION_STREAM_JSON)
	public Flux<String> createAllCGs() {
		List<String> names = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
		return Flux.fromStream(names.stream()).map(d -> d).delayElements(Duration.ofSeconds(2))
				.flatMap(x -> Flux.just(x));

	}

	@GetMapping(value = "/temperatures", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> getTemperature() {

		List<String> names = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");

		return Flux.fromIterable(names).map(s -> {
			System.out.println("S ::: " + s);
			return s;
		}).delayElements(Duration.ofSeconds(1));

//        return Flux.fromIterable(names).map( s -> s).delayElements(Duration.ofSeconds(1));

//        return Flux.fromStream(Stream.generate(() -> r.nextInt(high - low) + low)
//            .map(s -> String.valueOf(s))
//            .peek((msg) -> {
//                logger.info(msg);
//            }))
//            .map(s -> Integer.valueOf(s))
//            .delayElements(Duration.ofSeconds(1));
	}

//	@GetMapping(value = "/temp", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@GetMapping(path = "/temp", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<CGModel> getTemp() {
		List<String> names = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
		return Flux.fromIterable(names).map(s -> {
			System.out.println("S ::: " + s);
			return getCGModel(s);
		}).delayElements(Duration.ofSeconds(1));
	}

	private CGModel getCGModel(String name) {
		CGModel cgModel = new CGModel();
		cgModel.setCgName(name);
		cgModel.setMessage("Message for : " + name);
		cgModel.setTimestamp("Some Junk Time");
		return cgModel;

	}

}
