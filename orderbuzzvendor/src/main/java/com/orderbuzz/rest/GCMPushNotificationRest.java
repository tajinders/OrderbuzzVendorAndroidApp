package com.orderbuzz.rest;
import java.util.List;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.orderbuzz.domain.Order;
import com.orderbuzz.vendor.DoneOrderListViewActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
public class GCMPushNotificationRest extends AsyncTask<String, Void, Integer> {

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private ProgressDialog mProgressDialog;
	private Context context;

	public GCMPushNotificationRest(Context context) 
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
	protected Integer doInBackground(String... urls) {

		String restId = urls[0];
		String OrderSeqNo = urls[1];
		if (restId == null || OrderSeqNo == null) 
			return null;
			String URL = "http://orderbuzz-orderbuzz.rhcloud.com/orderbuzz/order/processedorder?restid="+restId+"&orderseqno="+OrderSeqNo;
			
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
			
			restTemplate.getForObject(URL, Boolean.class);
			
		return 1;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		mProgressDialog.dismiss();

	}


}
