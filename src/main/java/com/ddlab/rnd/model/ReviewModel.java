package com.ddlab.rnd.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewModel {
  private String srcDcName;
  private String srcVmName;
  private int journalSize = 20;

  public String getSrcDcName() {
    return srcDcName;
  }

  public void setSrcDcName(String srcDcName) {
    this.srcDcName = srcDcName;
  }

  public String getSrcVmName() {
    return srcVmName;
  }

  public void setSrcVmName(String srcVmName) {
    this.srcVmName = srcVmName;
  }

  public int getJournalSize() {
    return journalSize;
  }

  public void setJournalSize(int journalSize) {
    this.journalSize = journalSize;
  }

  public String toJSON() {
    ObjectMapper mapper = new ObjectMapper();
    String toJson = null;
    try {
      toJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return toJson;
  }

  @Override
  public String toString() {
    return "ReviewModel{"
        + "srcDcName='"
        + srcDcName
        + '\''
        + ", srcVmName='"
        + srcVmName
        + '\''
        + ", journalSize="
        + journalSize
        + '}';
  }

  public static void main(String[] args) {
    List<ReviewModel> modelList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      ReviewModel model = new ReviewModel();
      model.setJournalSize(i + 10);
      model.setSrcDcName("DataCenter-" + i);
      model.setSrcVmName("VM-" + i);
      modelList.add(model);
    }
    ObjectMapper mapper = new ObjectMapper();
    String toJson = null;
    try {
      toJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(modelList);
      System.out.println(toJson);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
