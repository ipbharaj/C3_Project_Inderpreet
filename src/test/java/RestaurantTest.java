import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    @BeforeEach
    public void InitRestaurantObject()
    {
        restaurant=  RestaurantTestUtility.CallAddRestaurant(service);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        //Arrange
        //mock the getCurrentTime of Restaurant's object to return a valid time when called
        LocalTime nowValidTime = LocalTime.parse("13:30:00");
        Restaurant mockRestauraunt = Mockito.spy(restaurant);
        Mockito.when(mockRestauraunt.getCurrentTime()).thenReturn(nowValidTime);

        //Act
        boolean isTimeValid = mockRestauraunt.isRestaurantOpen();

        //Assert
        Assertions.assertTrue(isTimeValid,"Restaurant should be open.");

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //Arrange
        //mock the getCurrentTime of Restaurant's object to return an invalid time when called
        LocalTime nowValidTime = LocalTime.parse("23:30:00");
        Restaurant mockRestauraunt = Mockito.spy(restaurant);
        Mockito.when(mockRestauraunt.getCurrentTime()).thenReturn(nowValidTime);

        //Act
        boolean isTimeValid = mockRestauraunt.isRestaurantOpen();

        //Assert
        Assertions.assertFalse(isTimeValid,"Restaurant should be open.");

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}