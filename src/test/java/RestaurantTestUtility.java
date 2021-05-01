import java.time.LocalTime;

public class RestaurantTestUtility {
    public static Restaurant CallAddRestaurant(RestaurantService service)
    {
        Restaurant restaurant;
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;
    }


}
