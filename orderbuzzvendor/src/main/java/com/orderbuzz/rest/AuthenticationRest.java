package com.orderbuzz.rest;

import java.util.List;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.orderbuzz.domain.Order;
import com.orderbuzz.domain.User;
import com.orderbuzz.vendor.LoginActivity;
import com.orderbuzz.vendor.PendingOrderListViewActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AuthenticationRest extends AsyncTask<User, Void, String>{

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private ProgressDialog mProgressDialog;
	private Context context;

	public AuthenticationRest(Context context) 
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
	protected String doInBackground(User... params) {

		User user = params[0];
		user.setRestid("x");
		if (user == null)
			return null;

		System.out.println("rest form user id and password " + user.getPassword() +" - - "+ user.getUserid());
		String URL = "http://orderbuzz-orderbuzz.rhcloud.com/orderbuzz/order/vendorlogin";
		//String URL = "http://192.168.2.18:8080/orderking/order/vendorlogin";
		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		// Add the Jackson and String message converters
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String restId = restTemplate.postForObject(URL, user, String.class);
		
		System.out.println("restId in rest " + restId);
		return restId;
		
	}



	@Override
	protected void onPostExecute(String restId) {
		mProgressDialog.dismiss();
		super.onPostExecute(restId);
		((LoginActivity)context).onTaskCompleted(restId);
	}

}
