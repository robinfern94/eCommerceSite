package Mongo.dao;

import com.mongodb.client.MongoCollection;
import Mongo.dto.BaseDto;
import Mongo.mongo.MongoConnection;

public abstract class MongoDao<T extends BaseDto> {
    MongoCollection<T> collection;
    MongoConnection mongoConnection;

    public MongoDao(MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
    }

}
