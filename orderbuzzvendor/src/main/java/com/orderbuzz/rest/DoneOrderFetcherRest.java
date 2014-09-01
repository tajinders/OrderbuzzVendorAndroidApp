package com.orderbuzz.rest;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.orderbuzz.domain.Order;
import com.orderbuzz.vendor.DoneOrderListViewActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class DoneOrderFetcherRest extends AsyncTask<String, Void, List<Order>>{
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private ProgressDialog mProgressDialog;
	private Context context;

	public DoneOrderFetcherRest(Context context) 
	{
		this.context = context;
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage("Please Wait..");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(true);

	}


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog.show();
	}


	@Override
	protected List<Order> doInBackground(String... urls) {
		System.out.println(" DO Back ");
		//SystemClock.sleep(5000);
		String restId = urls[0];
		if (restId == null) 
			return null;

		String URL = "https://orderbuzz-orderbuzz.rhcloud.com/orderbuzz/order/getorders/"+restId+"/done";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		Order [] orderarray = restTemplate.getForObject(URL, Order[].class);
		if (orderarray.length == 0)
			return null;
		// converting Array to List
		List<Order> orderList = Arrays.asList(orderarray);
		//	RestaurantMenuCache.getInstance().AddRestaurantMenu(restId, prodList);
		return orderList;
	}


	@Override
	protected void onPostExecute(List<Order> orderList) {

		mProgressDialog.dismiss();

		if (orderList != null ){
			super.onPostExecute(orderList);
			((DoneOrderListViewActivity)context).onTaskCompletedd(orderList);
		}
	}

}

