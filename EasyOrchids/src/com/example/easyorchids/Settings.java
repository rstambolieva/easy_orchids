package com.example.easyorchids;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * Handle the Settings Fragment view
 *
 */
public class Settings extends Fragment {

	public Settings() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.settings_view, container,
				false);

		return rootView;
	}

}