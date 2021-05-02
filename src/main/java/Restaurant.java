import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<>();
    private List<Item> selectedMenu = new ArrayList<>();
    
    

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        if(closingTime.compareTo(getCurrentTime()) >= 0 && openingTime.compareTo(getCurrentTime()) <=0)
            return true;
        else
            return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
            return menu;
    }

    public List<Item> getSelectedMenu() {
            return selectedMenu;
    }
    private Item findItemByName(String itemName) throws itemNotFoundException{
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    private Item findItemByNameSelectedMenu(String itemName) throws itemNotFoundException{
        for(Item item: selectedMenu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public void selectItemFromMenu(String itemName) throws itemNotFoundException{
        Item selectedItem = findItemByName(itemName);
        selectedMenu.add(selectedItem);
    }

    public void unSelectItemFromMenu(String itemName) throws itemNotFoundException{
        Item selectedItemToBeRemoved = findItemByNameSelectedMenu(itemName);
        selectedMenu.remove(selectedItemToBeRemoved);
    }

    public int getTotalPriceSelectedItem(){
        int total=0;
        for (Item item: selectedMenu) {
            total += item.getPrice();
        }
        return  total;
    }
}
