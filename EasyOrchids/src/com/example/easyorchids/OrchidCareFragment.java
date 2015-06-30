package com.example.easyorchids;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * Handle the Orchid care Fragment view
 *
 */
public class OrchidCareFragment extends Fragment {

	public OrchidCareFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.orchid_care_view, container,
				false);

		return rootView;
	}

}