package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;

public class StockDB implements StockDBIF{

	
	
	public void updateStock(Order order) {
		//First, we get the order's orderlines. We will do the rest of the method on all of them.
		for (int i = 0; i < order.getOrderLines().size(); i++) {
			OrderLine ol = order.getOrderLines().get(i);
			
			// Get the orderline's product serial number and quantity
			int q = ol.getQuantity();
			int pId = ol.getProductId();
			
			/*We have the serial number of the orderline's product, so now we execute a query 
			 * to get the id of all the Stock tuples/rows connected to the product. We return
			 * an ArrayList of these ids.
			 */
			String sqlQuery2 = ("select storageLocationId from Stock where productId = " + pId);
			ArrayList<String> ids = processQueryReturnArrayList(sqlQuery2);
			
			/*
			 * We loop over all the ids we just found, and execute a query on all of them to get
			 * their quantities, and then add the quantities to an arraylist.
			 */
			ArrayList<String> quantities = new ArrayList<>();
			for (int j = 0; j < ids.size(); j++) {
				String sqlQuery3 = ("select quantity from Stock where storageLocationId = " + ids.get(j) + " AND productId = " + pId);
				quantities.add(processQueryReturnString(sqlQuery3));
			}
			
			/*
			 * This next part may need to change. I wasn't sure which stock to remove quantity from,
			 * so i decided that i would remove it from the one with the largest quantity. Then, if 
			 * that wasn't enough, i will remove it from another one, and so on, until we have removed
			 * the correct amount of quantity.
			 */
			
			/*
			 * First, i will put each stock's id and quantity together in a HashMap, so we don't
			 * lose track of them.
			 */
			HashMap<String, String> idAndQuantity = new HashMap<>();
			for (int l = 0; l < ids.size(); l++) {
				idAndQuantity.put(ids.get(l), quantities.get(l));
			}
			//Now we will sort the quantities, from largest to smallest
			ArrayList<String> largestQuant = sortLargestToSmallest(quantities);
			
			/*
			 * Now we will start with the first stock, check whether or not it's quantity exceeds
			 * the product quantity, and act accordingly. Notice that we will loop though all
			 * the stocks until the product quantity has been deducted from the stock quantities.
			 * We are here reliant on the fact that there is enough stock quantity in total to
			 * accomodate the product quantity
			 */
			
			for (int t = 0; q > 0; t++) {
				String currentQuant = largestQuant.get(t);
				int quant = Integer.parseInt(currentQuant);
				
				
				if (q < quant || q == quant) {
					String id = "";
					for (String key : idAndQuantity.keySet()) {
						if (idAndQuantity.get(key).equals(currentQuant)) {
							id = key;
						}
					}
					quant -= q;
					
					makeUpdateQuery(id, pId, quant);
				}
				
				if (q > quant) {
					String id = "";
					for (String key : idAndQuantity.keySet()) {
						if (idAndQuantity.get(key).equals(currentQuant)) {
							id = key;
				        }
						
			        }
					q -= quant;
					quant = 0;
					idAndQuantity.replace(id, "0");
					
					makeUpdateQuery(id, pId, quant);
					
			}
		
		}
	
	}
  }
	
	
	
	
	
	
	public void makeUpdateQuery(String id, int productId, int quant) {
		String updatedQuantity = "";
		String sql = ("select minStock from Stock where id = " + id);
		String minStock = processQueryReturnString(sql);
		String sql2 = ("select maxStock from Stock where id = " + id);
		String maxStock = processQueryReturnString(sql2);
		
		if (quant < Integer.parseInt(minStock)) {
			updatedQuantity = maxStock;
		}
		else {
			updatedQuantity = String.valueOf(quant);
		}
		
		String finalQuery = ("update Stock set quantity = " + updatedQuantity + " where storageLocationId = " + id + " AND productId = " + productId);
		
		processQueryUpdate(finalQuery);
	}
	
	
	
	
	public ArrayList<String> sortLargestToSmallest(ArrayList<String> values){
		ArrayList<String> sorted = new ArrayList<>();
		ArrayList<String> unsorted = values;
		String q = "0";
		while (unsorted.size() > 0) {
			for (String num : unsorted) {
				if (Integer.parseInt(num) > Integer.parseInt(q)) {
					q = num;
				}
			}
			sorted.add(q);
			unsorted.remove(q);
			q = "0";
		}
		return sorted;
	}
	
	
	public void processQueryUpdate(String query) {
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String processQueryReturnString(String query) {
		String string = "";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				string += rs.getString(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return string;
	}
	
	
	
	public ArrayList<String> processQueryReturnArrayList(String query) {
		ArrayList<String> strings = new ArrayList<>();
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				strings.add(rs.getString(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return strings;
	}
}
