package com.example.easyorchids;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/*
 * This class represents the my orchids list view with all my orchids.
 * It populates the orchids from the DB
 */
public class MyOrchids extends ListFragment {
	public static final String ARG_MENU_ITEM_NUMBER = "menu_item_number";
	private ListView myOrchidsList;
	private String[] orchidsList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		
		View rootView = inflater.inflate(R.layout.myorchids_list_view, container,
				false);
		return rootView;
	}
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
//	    orchidsList =  getResources().getStringArray(R.array.my_orchids);
//	    
//	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//	      R.layout.myorchid_list_item, orchidsList);
	    
	    // Getting the Activity as the Activity is a Context to provide to the dbHelper for context
	    DBAdapter dbAdapter = new DBAdapter(getActivity());
	    
	    // Open the DB for writing
	    Log.d(Constants.TAG, "Opening DB");
	    dbAdapter.open();
	    long id;
	    
	    Log.d(Constants.TAG, "Inserting orchids");
        id = dbAdapter.insertOrchid("Pah 1", OrchidTypes.PHAELANOPSIS, "1 Sep", "1 Sep");
        id = dbAdapter.insertOrchid("Pah 2", OrchidTypes.PHAELANOPSIS, "1 Sep", "1 Sep");
	    
//	    setListAdapter();
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
