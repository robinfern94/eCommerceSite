package Mongo.processors;

import com.google.gson.Gson;
import Mongo.dao.ListingDao;
import Mongo.dto.ListingDto;
import Mongo.dto.ResponseBuilder;
import Mongo.dto.ResponseDto;
import Mongo.parser.ParsedUrl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class addItemProcessor implements Processor {

    @Override
    public ResponseDto process(String body) {
        ListingDao listingDao = ListingDao.getInstance();
        Gson gson = new Gson();
        ListingDto dto = gson.fromJson(new StringReader(body), ListingDto.class);
        ListingDto insertedItem = listingDao.put(dto);
        ResponseBuilder respBlder =  new ResponseBuilder();
        respBlder.setDate(new Date());
        respBlder.setSuccess(true);
        List<ListingDto> forTheThing = new ArrayList<ListingDto>();
        forTheThing.add(insertedItem);
        respBlder.setItems(forTheThing);
        return respBlder.build();
    }

}
