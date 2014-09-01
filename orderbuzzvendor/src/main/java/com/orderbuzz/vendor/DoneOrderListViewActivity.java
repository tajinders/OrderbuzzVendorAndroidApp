package com.orderbuzz.vendor;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.orderbuzz.domain.Order;
import com.orderbuzz.rest.DoneOrderFetcherRest;

public class DoneOrderListViewActivity extends Activity{
	@Override
	protected void onResume()
	{
		super.onResume();
		Bundle data = getIntent().getExtras();
		final String restId  = data.getString("restId");
		//String restId = "1";
		new DoneOrderFetcherRest(this).execute(restId); 

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_layout);
	}

	public void onTaskCompletedd(List<Order> orderList) {

		if (orderList != null ){
			DoneOrderListAdapter adapter= new DoneOrderListAdapter(this,orderList);
			ListView list =(ListView) findViewById(R.id.OCListView);
			list.setAdapter(adapter);
		}

	}


}
