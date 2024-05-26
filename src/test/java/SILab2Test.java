import org.example.Item;
import org.example.SILab2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class SILab2Test {
    @Test
    public void everyBranchTest() {
        RuntimeException e;
        ArrayList<Item> list = new ArrayList<>();

        // 1
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(e.getMessage().contains("allItems list can't be null!"));

        // 2
        list.add(new Item("Item 1", null, 80, 0));
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 50));
        assertTrue(e.getMessage().contains("No barcode!"));

        // 3
        list.clear();
        list.add(new Item("Item 1", "0053", 100, 0));
        list.add(new Item("Item 2", "0573", 200, 0));
        list.add(new Item("Item 3", "THAT", 150, 0.5f));
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 100));
        assertTrue(e.getMessage().contains("Invalid character in item barcode!"));

        list.clear();
        list.add(new Item("Item 1", "2354", 80, 0.1f));
        list.add(new Item("Item 2", "6091", 120, 0.3f));
        list.add(new Item("Item 3", "34231", 200, 0));

        // 4
        assertTrue(SILab2.checkCart(list, 600));

        // 5
        assertFalse(SILab2.checkCart(list, 200));
    }

    @Test
    public void multipleConditionTest() {
        ArrayList<Item> list = new ArrayList<>();

        // item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'


        list.add(new Item("Item", "1234564", 500, 0.2f));
        assertTrue(SILab2.checkCart(list, 1000));


        list.clear();
        list.add(new Item("Item", "1234562", 500, 0));
        assertTrue(SILab2.checkCart(list, 1000));


        list.clear();
        list.add(new Item("Item", "1234012", 500, 0.3f));
        assertTrue(SILab2.checkCart(list, 1000));


        list.clear();
        list.add(new Item("Item", "0123", 80, 0.5f));
        assertTrue(SILab2.checkCart(list, 1000));
    }
}