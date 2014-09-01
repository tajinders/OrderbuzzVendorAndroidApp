package com.orderbuzz.vendor;
import java.util.List;

import com.orderbuzz.domain.Order;
import com.orderbuzz.rest.OrderCache;
import com.orderbuzz.rest.PendingOrderFetcherRest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PendingOrderListViewActivity extends Activity{
	List<Order> myOrders;
	String restId;
	@Override
	protected void onResume()
	{
		super.onResume();
		Bundle data = getIntent().getExtras();
		restId = data.getString("restId");
		//String restId = "1";
		new PendingOrderFetcherRest(this).execute(restId); 

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_layout);
		registerClickCallback();
	}

	public void onTaskCompletedd(List<Order> orderList) {

		myOrders = orderList;
		PendingOrderListAdapter adapter= new PendingOrderListAdapter(this,orderList);
		ListView list = (ListView) findViewById(R.id.OCListView);
		list.setAdapter(adapter);
		
	}

	private void registerClickCallback()
	{
		ListView list = (ListView) findViewById(R.id.OCListView);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			public void onItemClick(AdapterView <?> parent , View viewClicked, int position, long id)
			{
				Order myOrder = myOrders.get(position);
				Intent intent = new Intent(getApplicationContext(), PendingOrderSummaryListViewActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

				Bundle bundle = new Bundle();
				bundle.putString("orderId", myOrder.getOrderId());
				bundle.putString("OrderSeqNo", myOrder.getOrderSequenceNo());
				bundle.putString("restId",restId);

				OrderCache.getInstance().AddOrderItemList(myOrder.getOrderId(), myOrder.getOrderItem());
				intent.putExtras(bundle);
				startActivity(intent);   

			}});

	}

}
