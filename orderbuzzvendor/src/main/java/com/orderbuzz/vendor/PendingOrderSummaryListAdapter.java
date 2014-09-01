package com.orderbuzz.vendor;
import java.util.List;
import com.orderbuzz.domain.OrderItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PendingOrderSummaryListAdapter extends ArrayAdapter<OrderItem>{
	List <OrderItem> myOrders;
	private LayoutInflater vi;

	public PendingOrderSummaryListAdapter(Context context, List<OrderItem> myOrders) {
		super(context,0, myOrders);
		this.myOrders = myOrders;
		vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View itemView = convertView ;
		if(itemView == null)
		{
			itemView = vi.inflate(R.layout.order_item_view, parent,false);
		}

		OrderItem item = myOrders.get(position);
		
		TextView makeText = (TextView)itemView.findViewById(R.id.ItemName);
		makeText.setText(item.getProdname());
		
		TextView Token = (TextView)itemView.findViewById(R.id.Summary);
		Token.setText(item.getOrderSummary());
		
		TextView price = (TextView)itemView.findViewById(R.id.OrderPrice);
		price.setText(String.valueOf(item.getPrice()));
		
		return itemView;
	}
}