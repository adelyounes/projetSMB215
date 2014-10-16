package com.blobinfo.contact;



import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Sendsms extends Activity { 
	Button sendBtn;
	EditText txtphoneNo;
	EditText txtMessage;
	String tag = "Events";
	String value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendsms);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			 value = extras.getString("Number");
		}
		
		
		
		sendBtn = (Button) findViewById(R.id.btnSendSMS);
		//txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
		
		txtMessage = (EditText) findViewById(R.id.editTextSMS);
		
		sendBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				sendSMSMessage(); } });
		Log.d(tag, "error in create");
		
	}
	
	protected void sendSMSMessage() { 
		Log.i("Send SMS", "");
		//String phoneNo = txtphoneNo.getText().toString();
		String message = txtMessage.getText().toString();
		try { SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(value, null, message, null, null);
		Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
		}
		
		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			} }
	@Override
	
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(tag, "Error in menu");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sendsms, menu);
		return true;
		
		
	}
	
}