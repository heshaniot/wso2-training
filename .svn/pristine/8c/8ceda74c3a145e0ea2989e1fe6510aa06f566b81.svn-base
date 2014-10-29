package org.wso2.carbon.order.process;

import java.util.Map;
import java.util.HashMap;

import org.wso2.carbon.order.process.data.Item;

public class OrderProcessor {
	
	private Map<String, Item> orderList;
	
	public OrderProcessor(){
		orderList = new HashMap<String, Item>();
		
		Item item1 = new Item();
        item1.setName("Books");
		item1.setPrice(50.75);
		item1.setQuantity(1500);

		Item item2 = new Item();
        item2.setName("Pens");        
		item2.setPrice(12.50);
		item2.setQuantity(10000);

		orderList.put(item1.getName(), item1);
		orderList.put(item2.getName(), item2);
	}

    /**
     * Adds the Item information
     * @param Item Instance of the Item bean class which contains information
     * @throws Exception if an invalid input is provided
     */
	public void addOrder(Item item) throws Exception{
		if(item == null || item.getName() == null){
			throw new Exception("Invalid Item");
		}
		orderList.put(item.getName(), item);
	}

    /**
     * Delete the student having the give NIC number from the student store
     * @param itemName Student NIC number
     * @throws Exception, if an invalid NIC number is given.
     */
    public void deleteOrder(String itemName) throws Exception{
        if( itemName == null || orderList.get(itemName) == null) {
            throw new Exception("Invalid Item");
        }

        orderList.remove(itemName);
    }

    /**
     * Returns an array of Student instances.
     * @return Student array.
     */
	public Item[] getOrders(){
		Item[] orders = new Item[orderList.size()];
        orderList.values().toArray(orders);
		return orders;
	}
}