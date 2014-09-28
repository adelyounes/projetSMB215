//Class to see the contact details

package com.blobinfo.contact;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactDetails extends Activity {

	private TextView firstName;
	private TextView lastName;
	private TextView emailAddress;
	private TextView phoneNumber;
	private Long rowID;
	ContactDB PhonebookDB = new ContactDB(this);
	private Cursor CursorList;
	Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		b = (Button) findViewById(R.id.callb);
		b.setOnClickListener(new OnClickListener()
		{
		public void onClick(View arg0){
			String num = phoneNumber.getText().toString();
			 Intent callIntent = new Intent(Intent.ACTION_CALL);
	         callIntent.setData(Uri.parse("tel:" + num));
	         startActivity(callIntent);
		}
		});

		PhonebookDB.open();
		try
		{
			firstName=(TextView)findViewById(R.id.txtFirstName);
			lastName=(TextView)findViewById(R.id.txtLastName);
			emailAddress=(TextView)findViewById(R.id.txtEmailID);
			phoneNumber=(TextView)findViewById(R.id.txtPhone);

			String position=getIntent().getStringExtra("posit");
			rowID=Long.parseLong(position);
			CursorList=PhonebookDB.getContacts(rowID);
			firstName.setText(CursorList.getString(1)+" ");
			lastName.setText(CursorList.getString(2));
			emailAddress.setText(CursorList.getString(3));
			phoneNumber.setText(CursorList.getString(4));
		}
		catch(Exception e)
		{
			Log.e("Phonebook_TAG","Got an error while displaying the contact",e);
		}
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contact_details, menu);
		return true;
	}

	public void deleteContact(View view)
	{
		try
		{
			PhonebookDB.deleteContacts(rowID);
			finish();
			PhonebookDB.close();

			Intent contactList = new Intent(this,ContactList.class);
			startActivity(contactList);
		}
		catch(Exception e)
		{
			Log.e("Phonebook_TAG","Got an error while deleting the contact",e);
		}
	}

	public void editContact(View view)
	{
		try
		{
			finish();	
			PhonebookDB.close();

			Intent editContact = new Intent(this,EditContact.class);
			editContact.putExtra("posit",rowID.toString());
			startActivity(editContact);
		}
		catch(Exception e)
		{
			Log.e("Phonebook_TAG","Got an error while editing the contact",e);
		}

	}

	@Override
	public void onBackPressed() {

		finish();

		PhonebookDB.close();
		startActivity(new Intent(this, ContactList.class));
	}

}
