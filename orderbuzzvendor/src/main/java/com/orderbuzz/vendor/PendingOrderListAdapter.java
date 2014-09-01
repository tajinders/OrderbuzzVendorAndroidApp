package com.orderbuzz.vendor;
import java.util.List;
import com.orderbuzz.domain.Order;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PendingOrderListAdapter extends ArrayAdapter<Order>{
	List <Order> myOrders;
	private LayoutInflater vi;

	public PendingOrderListAdapter(Context context, List<Order> myOrders) {
		super(context,0, myOrders);
		this.myOrders = myOrders;
		vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View itemView = convertView ;
		if(itemView == null)
		{
			itemView = vi.inflate(R.layout.order_view, parent,false);
		}

		Order orders = myOrders.get(position);
		TextView makeText = (TextView)itemView.findViewById(R.id.Orderid);
		makeText.setText("OrderId:"+ (position+1));
		TextView Token = (TextView)itemView.findViewById(R.id.SecretKey);
		Token.setText("OrderKey:"+orders.getOrderKey());

		return itemView;
	}
}