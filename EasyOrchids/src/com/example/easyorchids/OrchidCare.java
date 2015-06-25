package com.example.easyorchids;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * Handle the Orchid care Fragment view
 *
 */
public class OrchidCare extends Fragment {

	public OrchidCare() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.orchid_care_view, container,
				false);

		return rootView;
	}

}