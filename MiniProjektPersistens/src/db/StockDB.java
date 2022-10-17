package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;

public class StockDB implements StockDBIF{

	private static final String getStorageLocationIdQ = "SELECT storageLocationId FROM Stock where productId = ?";
	private static final String getQuantityQ = "SELECT quantity FROM Stock where storageLocationId = ? AND productId = ?";
	private static final String updateStockQ = "UPDATE Stock SET quantity = ? where storageLocationId = ? AND productId = ?";
    
	private PreparedStatement getStorageLocationId;
	private PreparedStatement getQuantity;
	private PreparedStatement updateStock;
    
    public StockDB() throws SQLException{
        getStorageLocationId = DBConnection.getInstance().getConnection().prepareStatement(getStorageLocationIdQ);
        getQuantity = DBConnection.getInstance().getConnection().prepareStatement(getQuantityQ);
        updateStock = DBConnection.getInstance().getConnection().prepareStatement(updateStockQ);
    }
    
    
	@Override
	public void updateStock(Order order) throws DataAccessException, SQLException {
		for (int i = 0; i < order.getOrderLines().size(); i++) {
			OrderLine ol = order.getOrderLines().get(i);
			
			int q = ol.getQuantity();
			int pId = ol.getProductId();
			
			
			//We have the serial number of the orderline's product, so now we execute a query 
            //to get the id of all the Stock tuples/rows connected to the product. We return
            // an ArrayList of these ids.
			getStorageLocationId.setInt(1, pId);
			ResultSet rs = getStorageLocationId.executeQuery();
			
			ArrayList<String> ids = new ArrayList<>();
			while (rs.next()) {
			    ids.add(rs.getString(1));
			}
			//We loop over all the ids we just found, and execute a query on all of them to get
            //their quantities, and then add the quantities to an arraylist.
			
			ArrayList<String> quantities = new ArrayList<>();
			for (int j = 0; j < ids.size(); j++) {
				getQuantity.setString(1, ids.get(j));
				getQuantity.setInt(2, pId);
				ResultSet rs2 = getQuantity.executeQuery();
				if (rs2.next()) {
				    quantities.add(rs2.getString(1));
				}
			}
			 //This next part may need to change. I wasn't sure which stock to remove quantity from,
             //so i decided that i would remove it from the one with the largest quantity. Then, if 
             //that wasn't enough, i will remove it from another one, and so on, until we have removed
             //the correct amount of quantity.
		
			//First, i will put each stock's id and quantity together in a HashMap, so we don't
            //lose track of them.
			HashMap<String, String> idAndQuantity = new HashMap<>();
			for (int l = 0; l < ids.size(); l++) {
				idAndQuantity.put(ids.get(l), quantities.get(l));
			}
			ArrayList<String> largestQuant = sortLargestToSmallest(quantities);
			
			
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
					q = 0;
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
	
	
	
	
	
	
	private void makeUpdateQuery(String id, int productId, int quant) throws SQLException {
		String updatedQuantity = String.valueOf(quant);
	    //String sql = ("select minStock from Stock where storageLocationId = " + id + " AND productId = " + productId);
		//String minStock = processQueryReturnString(sql);
		//String sql2 = ("select maxStock from Stock where storageLocationId = " + id + " AND productId =  " + productId);
		//String maxStock = processQueryReturnString(sql2);
		
		//if (quant < Integer.parseInt(minStock)) {
			//updatedQuantity = maxStock;
	//	}
		//else {
			//updatedQuantity = String.valueOf(quant);
		//}
		updateStock.setString(1, updatedQuantity);
		updateStock.setString(2, id);
		updateStock.setInt(3, productId);
		int val = updateStock.executeUpdate();
		
	}
	
	
	
	
	private ArrayList<String> sortLargestToSmallest(ArrayList<String> values){
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
	
}