package com.example.easyorchids;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * This class represents the my orchids list with all my orchids
 * Currently it populates the data adapter from string array, but must e refactored to use a DB
 */
public class MyOrchids extends ListFragment {
	public static final String ARG_MENU_ITEM_NUMBER = "menu_item_number";
	private ListView myOrchidsList;
	private String[] orchidsList;
	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    orchidsList =  getResources().getStringArray(R.array.my_orchids);
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	      R.layout.myorchid_list_item, orchidsList);
	    
	    setListAdapter(adapter);
	  }
	
	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // TODO implement some logic 
	  } 

	
//	public MyOrchids() {
//		// Empty constructor required for fragment subclasses
//	}

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View rootView = inflater.inflate(R.layout.fragment_orchid, container,
//				false);
//		int i = getArguments().getInt(ARG_MENU_ITEM_NUMBER);
//		String orchid = getResources().getStringArray(R.array.main_menu)[i];

//		int imageId = getResources().getIdentifier(
//				orchid.toLowerCase(Locale.getDefault()), "drawable",
//				getActivity().getPackageName());
//		((ImageView) rootView.findViewById(R.id.image))
//				.setImageResource(imageId);
//		getActivity().setTitle(orchid);
//		return rootView;
//	}

}
