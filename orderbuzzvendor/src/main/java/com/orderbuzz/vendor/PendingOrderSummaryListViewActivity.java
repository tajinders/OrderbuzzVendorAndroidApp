package com.orderbuzz.vendor;
import com.orderbuzz.rest.GCMPushNotificationRest;
import com.orderbuzz.rest.OrderCache;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class PendingOrderSummaryListViewActivity extends Activity{

	
		public static Context context;

		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_item_layout);
        context = getApplicationContext();

        final Button SubmitOrder = new Button(this);
		SubmitOrder.setText("SUBMIT");
		SubmitOrder.setTextColor(Color.WHITE);
		Bundle data = getIntent().getExtras();
		
		final String orderId  = data.getString("orderId");
		final String orderSeqNo = data.getString("OrderSeqNo");
		final String restId = data.getString("restId");
		
		PendingOrderSummaryListAdapter adapter= new PendingOrderSummaryListAdapter(this,OrderCache.getInstance().getOrderItems(orderId));
		ListView list =(ListView) findViewById(R.id.OCListView);

		list.addFooterView(SubmitOrder);
		list.setAdapter(adapter);
		context = this; 
		
		SubmitOrder.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			   if(SubmitOrder.getText() == "SUBMIT" ){
			   new GCMPushNotificationRest(context).execute(restId, orderSeqNo); 
			   SubmitOrder.setText("ORDER PROCESSED");
			}
		}
		});
	}
}