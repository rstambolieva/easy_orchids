package com.example.easyorchids;

import android.util.Log;

/**
 * 
 * This class represents an orchid model and contains the orchid we
 * will save in the database and show in the user interface.
 *
 */
public class Orchid {
	private long id;
	private String orchidName;
	private String lastWatering;
	private String lastFertilizing;
	private String isOutside;

	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getOrchidName() {
		return orchidName;
	}
	public void setOrchidName(String orchidName) {
		this.orchidName = orchidName;
	}
	public String getLastWatering() {
		return lastWatering;
	}
	public void setLastWatering(String lastWatering) {
		this.lastWatering = lastWatering;
	}
	public String getLastFertilizing() {
		return lastFertilizing;
	}
	public void setLastFertilizing(String lastFertilizing) {
		this.lastFertilizing = lastFertilizing;
	}
	public String getIsOutside() {
		return isOutside;
	}
	public void setIsOutside(String isOutside) {
		if(isOutside.equals("yes")|| isOutside.equals("no")){
			this.isOutside = isOutside;
		}
		else{
			this.isOutside = isOutside;
			Log.d(Constants.TAG, "Invalid outside state, returning null");
		}
	}
	
	// To be used in the array adapter in the listview
	@Override
	  public String toString() {
	    return orchidName;
	  }
	
}
