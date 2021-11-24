package Mongo.dto;

import org.bson.types.Binary;

import java.io.File;

public class ListingDto extends BaseDto {

    public String description;
    public String date;
    public Integer price;
    public String title;
    public String email;
    public String post_id;

    public String getEmail(){
        return this.email;
    }

    public ListingDto() {
        super(null);
    }

//    public ListingDto(String id, String email) {
//        super(id);
//        this.email = email;
//    }

    public ListingDto(String id, String description, String date, Integer price, String title, String email
            , String post_id) {
        super(id);
        this.description = description;
        this.date = date;
        this.price = price;
        this.title = title;
        this.email = email;
        this.post_id = post_id;
    }


}
