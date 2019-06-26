package com.ddlab.rnd;

import com.ddlab.rnd.model.CGModel;
import com.ddlab.rnd.model.ReviewModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestTempController {

  @PostMapping(path = "/temp1", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<CGModel> getTemp(@RequestBody List<ReviewModel> reviewModelList) {
    List<String> names = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
    return Flux.fromIterable(reviewModelList)
        .map(
            reviewModel -> {
              System.out.println("S ::: " + reviewModel);
              return getCGModel(reviewModel);
            })
        .delayElements(Duration.ofSeconds(1));
  }

  @GetMapping(path = "/temp3/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<CGModel> getTempFromJsonString() {
    ObjectMapper mapper = new ObjectMapper();
    List<ReviewModel> modelList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      ReviewModel model = new ReviewModel();
      model.setJournalSize(i + 10);
      model.setSrcDcName("DataCenter-" + i);
      model.setSrcVmName("VM-" + i);
      modelList.add(model);
    }
    return Flux.fromIterable(modelList)
        .map(
            reviewModel -> {
              System.out.println("S ::: " + reviewModel);
              return getCGModel(reviewModel);
            })
        .delayElements(Duration.ofSeconds(1));
  }

  private CGModel getCGModel(ReviewModel reviewModel) {
    System.out.println("Review Model : " + reviewModel);
    System.out.println("Time Now : " + LocalDate.now());
    CGModel cgModel = new CGModel();
    cgModel.setCgName(reviewModel.getSrcVmName());
    cgModel.setMessage("Data Center Name : " + reviewModel.getSrcDcName());
    cgModel.setTimestamp(LocalDateTime.now().toString());
    return cgModel;
  }
}
