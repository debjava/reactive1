package com.ddlab.rnd.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ddlab.rnd.model.CGModel;

import reactor.core.publisher.Flux;

@Service @Primary
public class CGServiceImpl implements CGService {

	@Override
	public CGModel createCG() {
		return null;
	}
	
	
	private List<CGModel> generateCGModel(long interval) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		CGModel cgModel = new CGModel();
		cgModel.setCgName("CG Name-1");
		cgModel.setMessage("Successful/Failure CG Message");
		cgModel.setTimestamp(dtf.format(LocalDateTime.now()));
		return Arrays.asList(cgModel);
	}
	
	
    public Flux<CGModel> findAll() {
        //simulate data streaming every 1 second.
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(this::generateCGModel)
                .flatMapIterable(x -> x);
    }

//    private List<Comment> generateComment(long interval) {
//        Comment obj = new Comment(CommentGenerator.randomAuthor(), CommentGenerator.randomMessage(), CommentGenerator.getCurrentTimeStamp());
//        return Arrays.asList(obj);
//    }
	

}
