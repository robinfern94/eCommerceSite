package Mongo.processors;

import com.google.gson.Gson;
import Mongo.dao.ListingDao;
import Mongo.dto.ListingDto;
import Mongo.dto.ResponseBuilder;
import Mongo.dto.ResponseDto;
import Mongo.parser.ParsedUrl;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class deleteItemProcessor implements Processor{

    @Override
    public ResponseDto process(String body) {
        ListingDao listingDao = ListingDao.getInstance();
        body = body.replace("\"","");
        String post_id = "";
        for (String part : body.substring(1, body.length() - 1).split(",")) {
            String[] key_val = part.split(":");
            if (key_val[0].equals("post_id")){
                post_id = key_val[1];
            }
        }
        System.out.println("Delete id " + post_id);

        ResponseBuilder respBlder =  new ResponseBuilder();
        respBlder.setDate(new Date());

        if (post_id != null) {
            try{
                listingDao.delete(post_id);
                respBlder.setSuccess(true);
            } catch (Exception e){
                System.out.println("SERVER ERROR: " + e + "\nCouldn't delete post_id: " + post_id);
                respBlder.setSuccess(false);
            }
        } else {
            System.out.println("Must pass a post id in the body: \n"+body);
            respBlder.setSuccess(false);
        }
        List<ListingDto> forTheThing = new ArrayList<ListingDto>();
        respBlder.setItems(forTheThing);
        return respBlder.build();
    }
}
