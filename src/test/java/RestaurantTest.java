import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;

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
        assertTrue(isTimeValid,"Restaurant should be open.");

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
        assertFalse(isTimeValid,"Restaurant should be open.");

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void getmenu_should_return_null_without_items_in_list() throws itemNotFoundException{
        //Act
        restaurant.removeFromMenu("Sweet corn soup");
        restaurant.removeFromMenu("Vegetable lasagne");

        List<Item> menu = restaurant.getMenu();
        //Assert
        assertNotNull(menu);
        assertEquals(0,menu.size());
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        //Arrange and Act
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);

        //Assert
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        //Arrange and Act
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");

        //Assert
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        //Act and Assert
        itemNotFoundException exp = assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
        assertEquals("French fries",exp.getMessage());

    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<<selected Menu>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void select_item_should_return_not_null_without_selecting(){
        //Act
        List<Item> selectedItem = restaurant.getSelectedMenu();
        //Assert
        assertNotNull(selectedItem);
        assertEquals(0,selectedItem.size());
    }

    @Test
    public void select_item_form_menu_and_Selected_menu_should_increase_by_1() throws itemNotFoundException {
        //Act
        int selectedMenuSize = restaurant.getSelectedMenu().size();
        restaurant.selectItemFromMenu("Sweet corn soup");

        //Assert
        assertEquals(selectedMenuSize+1,restaurant.getSelectedMenu().size());
    }

    @Test
    public void unselect_item_form_menu_and_Selected_menu_should_decrease_by_1() throws itemNotFoundException {
        //Act
        restaurant.selectItemFromMenu("Sweet corn soup");
        int selectedMenuSize = restaurant.getSelectedMenu().size();
        restaurant.unSelectItemFromMenu("Sweet corn soup");

        //Assert
        assertEquals(selectedMenuSize-1,restaurant.getSelectedMenu().size());
    }

    @Test
    public void unselect_item_form_empty_selected_menu_and_it_should_throw_exception()  {
        //Act and Assert
        itemNotFoundException exp = assertThrows(itemNotFoundException.class,
                ()-> restaurant.unSelectItemFromMenu("Sweet corn soup"));
        assertEquals("Sweet corn soup",exp.getMessage(),"exception if Item not found should contain searched Item name");
    }

    @Test
    public void select_item_form_menu_of_price_119_and_269_and_total_price_should_be_388() throws itemNotFoundException {
        //Act
        restaurant.selectItemFromMenu("Sweet corn soup");
        restaurant.selectItemFromMenu("Vegetable lasagne");

        //Assert
        assertEquals(388,restaurant.getTotalPriceSelectedItem());
    }

    @Test
    public void select_item_from_menu_which_doesnot_exist_and_expect_exception(){
        //Act and Assert
        itemNotFoundException exp = assertThrows(itemNotFoundException.class,
                ()-> restaurant.selectItemFromMenu("Sweet corn soup1"));
        assertEquals("Sweet corn soup1",exp.getMessage(),"exception if Item not found should contain searched Item name");
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<selected Menu>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}