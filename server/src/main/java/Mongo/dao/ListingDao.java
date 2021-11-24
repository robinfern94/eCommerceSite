package Mongo.dao;

import Mongo.dto.ListingDto;
import Mongo.mongo.MongoConnection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListingDao extends MongoDao<ListingDto>
        implements DataAccessObject<ListingDto> {

    // use lazy loading for the singleton
    private static ListingDao instance;

    ListingDao(MongoConnection connection) {
        super(connection);
        collection = mongoConnection.getCollection("MyCollection", ListingDto.class);
    }

    public static ListingDao getInstance() {
        if (instance == null) {
            instance = new ListingDao(new MongoConnection());
        }
        return instance;
    }

    @Override
    public ListingDto put(ListingDto item) {
        // fill this out

        collection.insertOne(item);
        return item;
    }

    @Override
    public List<ListingDto> getItems() {
        // use .into
        // fill this out
        List<ListingDto> docs = collection.find().into(new ArrayList<>());
        Collections.sort(docs, comparing(ListingDto::getEmail));
        return docs;
    }

    @Override
    public ListingDto getItem(String id) {
        return collection.find(eq("id", id)).first();
    }

    @Override
    public void delete(String id) {
        // fill this out;
        System.out.println("The post_id is "+id);
        collection.deleteOne(new Document("post_id", id));
    }
}
