package com.example.easyorchids;

import android.util.Log;

/**
 * This enum specifies the orchid types with specific watering, fertilizing,
 * temperature day and night. Enum of the types of Orchids currently doesn't
 * include subtypes. For correct frequencies of watering and fertilizing the
 * season must be considered
 *
 */
public enum OrchidTypes {
	PHAELANOPSIS("Phaelanopsis", Seasons.SUMMER), DENDROBIUM("Dendrobium",
			Seasons.SUMMER), ONCIDIUM("Oncidiium", Seasons.SUMMER);

	private String name;
	private int waterFrequencyDays;
	private int fertilizeFrequencyDays;
	private Seasons season;

	/**
	 * Creates the Orchidtype with watering and fertilizing per season
	 * 
	 * @param orhidType
	 * @param season
	 */
	OrchidTypes(String orhidType, Seasons season) {
		this.name = orhidType;
		this.season = season;

		switch (season) {
		case SUMMER:
			if (orhidType.equals("Phaelanopsis")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.BYWEEKLY;
			} else if (orhidType.equals("Dendrobium")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.WEEKLY;
			} else if (orhidType.equals("Oncidium")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.WEEKLY;
			} else {
				Log.d(Constants.TAG, "Wrong Type of orchid");
			}
			break;
		case SPRING:
			if (orhidType.equals("Phaelanopsis")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.BYWEEKLY;
			} else if (orhidType.equals("Dendrobium")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.WEEKLY;
			} else if (orhidType.equals("Oncidium")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.WEEKLY;
			} else
				Log.d(Constants.TAG, "Wrong Type of orchid");
			break;
		case AUTUMN:
			if (orhidType.equals("Phaelanopsis")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.MONTHLY;
			} else if (orhidType.equals("Dendrobium")) {
				this.waterFrequencyDays = Constants.MONTHLY;
				this.fertilizeFrequencyDays = Constants.NONE;
			} else if (orhidType.equals("Oncidium")) {
				this.waterFrequencyDays = Constants.BYWEEKLY;
				this.fertilizeFrequencyDays = Constants.BYWEEKLY;
			} else
				Log.d(Constants.TAG, "Wrong Type of orchid");
			break;
		case WINTER:
			if (orhidType.equals("Phaelanopsis")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.BYWEEKLY;
			} else if (orhidType.equals("Dendrobium")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.BYWEEKLY;
			} else if (orhidType.equals("Oncidium")) {
				this.waterFrequencyDays = Constants.WEEKLY;
				this.fertilizeFrequencyDays = Constants.BYWEEKLY;
			} else
				Log.d(Constants.TAG, "Wrong Type of orchid");
			break;
		}
		// add default statement
	}
}
