package Mongo.processors;


import Mongo.dto.ResponseDto;
import Mongo.parser.ParsedUrl;

public interface Processor {

  public ResponseDto process(String body);

}
