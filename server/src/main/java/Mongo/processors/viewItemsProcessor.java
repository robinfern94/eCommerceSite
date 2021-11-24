package Mongo.processors;


import Mongo.dao.ListingDao;
import Mongo.dto.ListingDto;
import Mongo.dto.ResponseBuilder;
import Mongo.dto.ResponseDto;
import Mongo.parser.ParsedUrl;

import java.util.Date;
import com.google.gson.Gson;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class viewItemsProcessor implements Processor{

    @Override
    public ResponseDto process(String body) {
        ListingDao listingDao = ListingDao.getInstance();
        Gson gson = new Gson();
        ResponseBuilder respBlder =  new ResponseBuilder();
        respBlder.setDate(new Date());
        respBlder.setSuccess(true);
        respBlder.setItems(listingDao.getItems());
        return respBlder.build();
    }
}
