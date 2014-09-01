package com.orderbuzz.vendor;
import com.orderbuzz.domain.User;
import com.orderbuzz.rest.AuthenticationRest;
import com.orderbuzz.rest.GCMPushNotificationRest;
import com.orderbuzz.rest.OrderCache;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity{

	private EditText  username=null;
	private EditText  password=null;
	private TextView attempts;
	private Button login;
	int counter = 3;
	public static Context context;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authentication);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		attempts = (TextView)findViewById(R.id.textView5);
		attempts.setText(Integer.toString(counter));
		login = (Button)findViewById(R.id.button1);
		context = this;
	}

	public void login(View view){

		User user = new User();
		user.setUserid(username.getText().toString());
		user.setPassword(password.getText().toString());
		
		System.out.println("login form user id and password " + user.getPassword() +" - - "+ user.getUserid());
		
		new AuthenticationRest(context).execute(user); 

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onTaskCompleted(String restId) {

		if( restId != null )

		{
			Toast.makeText(getApplicationContext(), "Redirecting...", 
					Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(getApplicationContext(), OrderTabAndListView.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			Bundle bundle = new Bundle();
			bundle.putString("restId", restId);
			intent.putExtras(bundle);
			startActivity(intent);

		}

		else{
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
					Toast.LENGTH_SHORT).show();
			attempts.setBackgroundColor(Color.RED);	
			counter--;
			attempts.setText(Integer.toString(counter));
			if(counter==0){
				login.setEnabled(false);
			}

		}

	}

}