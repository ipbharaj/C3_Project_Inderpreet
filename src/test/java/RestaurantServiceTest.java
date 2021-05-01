import org.junit.jupiter.api.*;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void InitRestaurantObject()
    {
        restaurant=  RestaurantTestUtility.CallAddRestaurant(service);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //Arrange
        //Act
        Restaurant foundRestaurant = service.findRestaurantByName("Amelie's cafe");
        //Assert
        assertNotNull(foundRestaurant,"Assert if foundRestaurant is not Null.");
        assertEquals("Amelie's cafe",foundRestaurant.getName(),"Assert if foundRestaurant's name is 'Amelie's cafe'");
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //Arrange
        //Act
        restaurantNotFoundException exception = assertThrows(restaurantNotFoundException.class , () ->
        {
            service.findRestaurantByName("Amelie's cafe1");
        });

        //Assert
        assertNotNull(exception,"Exception thrown by call to find restaurant should not be null");
        assertEquals("Amelie's cafe1",exception.getMessage());
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        //Arrange
        //Act
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        //Assert
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        //Act and Assert
        restaurantNotFoundException exp = assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
        assertEquals("Pantry d'or",exp.getMessage());
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}