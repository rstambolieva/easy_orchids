package com.example.easyorchids;

public enum YesNoEnum {
	YES("yes"), NO("no");

	private final String value;

	YesNoEnum(String value) {
		this.value = value;
	}

	public String toString() {
		return value.toLowerCase();
	}
}
