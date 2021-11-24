package demo;

public class ListingDto {

    public final String title;
    public final String description;
    public final String date;
    public final Integer price;
    public final String email;

    public ListingDto(String title, String description, String date, Integer price, String email){
        this.title = title;
        this.description = description;
        this.date = date;
        this.price = price;
        this.email = email;
    }

}
