package org.wso2.carbon.order.process.data;

public class Item {
	private String name;
	private Double price;
	private int quantity;

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
     public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price){
    	this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   

}