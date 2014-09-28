package com.blobinfo.contact;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddContact extends Activity {

	private EditText firstName;
	private EditText lastName;
	private EditText emailAddress;
	private EditText phoneNumber;
	final ContactDB PhonebookDB = new ContactDB(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);

		firstName=(EditText)findViewById(R.id.firstName);
		lastName=(EditText)findViewById(R.id.lastName);
		emailAddress=(EditText)findViewById(R.id.emailID);
		phoneNumber=(EditText)findViewById(R.id.phone);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_contact, menu);
		return true;
	}

	//Adding contact to DB
	public void addToDB(View view)
	{
		try
		{
			long intNewID = 0;

			String strFirstName = firstName.getText().toString();
			String strLastName = lastName.getText().toString();
			String strEmailID = emailAddress.getText().toString();
			String strMobile01 = phoneNumber.getText().toString();

			PhonebookDB.open();
			intNewID = PhonebookDB.insertContacts(strFirstName, strLastName,strEmailID, strMobile01);
			PhonebookDB.close();
			finish();

			Intent contactList = new Intent(this,ContactList.class);
			startActivity(contactList);
		}
		catch(Exception e)
		{
			Log.e("Phonebook_TAG","Got an error while adding the contact",e);
		}
	}

}
