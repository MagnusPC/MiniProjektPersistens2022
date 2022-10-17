package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;

public class StockDB implements StockDBIF{

	
	@Override
	public void updateStock(Order order) throws DataAccessException {
		for (int i = 0; i < order.getOrderLines().size(); i++) {
			OrderLine ol = order.getOrderLines().get(i);
			
			int q = ol.getQuantity();
			int pId = ol.getProductId();
			
			String sqlQuery2 = ("select storageLocationId from Stock where productId = " + pId);
			ArrayList<String> ids = processQueryReturnArrayList(sqlQuery2);
			
			ArrayList<String> quantities = new ArrayList<>();
			for (int j = 0; j < ids.size(); j++) {
				String sqlQuery3 = ("select quantity from Stock where storageLocationId = " + ids.get(j) + " AND productId = " + pId);
				quantities.add(processQueryReturnString(sqlQuery3));
			}
		
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
	
	
	
	
	
	
	private void makeUpdateQuery(String id, int productId, int quant) {
		String updatedQuantity = "";
		String sql = ("select minStock from Stock where storageLocationId = " + id + " AND productId = " + productId);
		String minStock = processQueryReturnString(sql);
		String sql2 = ("select maxStock from Stock where storageLocationId = " + id + " AND productId =  " + productId);
		String maxStock = processQueryReturnString(sql2);
		
		//if (quant < Integer.parseInt(minStock)) {
			//updatedQuantity = maxStock;
	//	}
		//else {
			//updatedQuantity = String.valueOf(quant);
		//}
		
		String finalQuery = ("update Stock set quantity = " + updatedQuantity + " where storageLocationId = " + id + " AND productId = " + productId);
		
		processQueryUpdate(finalQuery);
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
	
	
	private void processQueryUpdate(String query) {
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		}
		catch(Exception e) {
		    ;
		}
	}
	
	
	
	private String processQueryReturnString(String query) {
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
	
	
	
	private ArrayList<String> processQueryReturnArrayList(String query) {
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
