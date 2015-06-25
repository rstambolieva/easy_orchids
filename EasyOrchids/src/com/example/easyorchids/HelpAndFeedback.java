package com.example.easyorchids;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * Handle the Help and Feedback Fragment view
 *
 */
public class HelpAndFeedback extends Fragment {

	public HelpAndFeedback() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.schedule_view, container,
				false);

		return rootView;
	}

}