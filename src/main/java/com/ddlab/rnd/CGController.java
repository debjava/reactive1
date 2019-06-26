package com.ddlab.rnd;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddlab.rnd.model.CGModel;
import com.ddlab.rnd.service.CGService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CGController {

	@Autowired
	private CGService cgService;

//	@GetMapping(path = "/cg/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	
	@GetMapping(path = "/cg/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//	@GetMapping(path = "/cg/stream", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Flux<CGModel> createAllCGs() {
	public Flux<String> createAllCGs() {
		
		List<String> names = Arrays.asList("A","B","C","D","E","F","G","H");
//		Flux.fromIterable(names).interval(Duration.ofSeconds(1L)).map(mapper -> System.out.println());
		
//		Flux.fromIterable(names).map( d -> System.out.println("d ->"+d));
		
		Object X = Flux.fromStream(names.stream()).map(d -> d).delayElements(Duration.ofSeconds(1)).flatMap(x-> Flux.just(x));
		System.out.println("Now XXX :::"+X);
		
//		return Flux.interval(Duration.ofSeconds(1)).onBackpressureDrop().map(this::generateCGModel).flatMapIterable(x -> x);

		
//		str s = Flux.fromStream(names.stream()).map(d -> d).delayElements(Duration.ofSeconds(10)).flatMap(x-> Flux.just(x));
		return Flux.fromStream(names.stream()).map(d -> d).delayElements(Duration.ofSeconds(10)).flatMap(x-> Flux.just(x));
		
		
		
	}
	
	public Mono<ResponseEntity<CGModel>> createAllCGsMono() {
		return null;
	}
	
	
	
//	@GetMapping(path = "/cg/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	public Flux<CGModel> createAllCGs(List<Product> prodList) {
//		
//		for(Product p : prodList) {
//			
//		}
//		return Flux.interval(Duration.ofSeconds(1)).onBackpressureDrop().map(this::generateCGModel).flatMapIterable(x -> x);
//		
//	}

//    @Override
//    public Flux<CGModel> findAll() {
//        //simulate data streaming every 1 second.
//        return Flux.interval(Duration.ofSeconds(1))
//                .onBackpressureDrop()
//                .map(this::generateCGModel)
//                .flatMapIterable(x -> x);
//    }
	
	private List<CGModel> checkModel(String str) {
		System.out.println("Now Str in method : "+str);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		CGModel cgModel = new CGModel();
		cgModel.setCgName("Hi-"+str);
		cgModel.setMessage("Successful/Failure CG Message");
		cgModel.setTimestamp(dtf.format(LocalDateTime.now()));
		
		return Arrays.asList(cgModel);
	}

	private List<CGModel> generateCGModel(long interval) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		CGModel cgModel = new CGModel();
		cgModel.setCgName("CG Name-1");
		cgModel.setMessage("Successful/Failure CG Message");
		cgModel.setTimestamp(dtf.format(LocalDateTime.now()));
		return Arrays.asList(cgModel);
	}

//    private List<Comment> generateComment(long interval) {
//        Comment obj = new Comment(CommentGenerator.randomAuthor(), CommentGenerator.randomMessage(), CommentGenerator.getCurrentTimeStamp());
//        return Arrays.asList(obj);
//    }

}