package Mongo.dao;

import Mongo.dto.BaseDto;
import Mongo.dto.ListingDto;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public interface DataAccessObject<T extends BaseDto> {

    static DataAccessObject getInstance() {
        throw new RuntimeException("not implemented");
    }

    T put(T item);

    List<T> getItems();

    T getItem(String id);

    void delete(String id);

}
