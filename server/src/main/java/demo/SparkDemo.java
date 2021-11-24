package demo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
import Mongo.dto.ListingDto;
import Mongo.processors.*;
import Mongo.dto.ResponseDto;


public class SparkDemo {

  static List<ListingDto> listings = new ArrayList<>();
  static Gson gson = new Gson();

  public static void main(String[] args) {
    port(1235);

    //webSocket
    webSocket("/ws", WebSocketHandler.class);

    get("/", ((req, res) -> {
      System.out.println("received message");
      System.out.println(req.queryMap("name").value());
      return "hello" + req.queryMap("name").value();
    }));

    post("/addListing", (req, res) -> {
      System.out.println("Success");
      String bodyString = req.body();
      System.out.println("Adding: " + bodyString);
      ListingDto newListing = gson.fromJson(bodyString, ListingDto.class);
      listings.add(newListing);
      ResponseDto output = new addItemProcessor().process(bodyString);
      //alert other clients
      WebSocketHandler.broadcast(gson.toJson(listings));
      return output;
    });

    get("/viewListings", (req,res) ->{
      System.out.println("Sending back: \n" + gson.toJson(new viewItemsProcessor().process("").items));
      return gson.toJson(new viewItemsProcessor().process("").items);
    });

    post("/deleteListing", (req,res) ->{
      System.out.println("Delete");
      boolean output = new deleteItemProcessor().process(req.body()).success;
      System.out.println("Sending back: \n" + output);
      WebSocketHandler.broadcast(gson.toJson(listings));
      return output;
    });
  }
}
