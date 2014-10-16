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
	private TextView title;
	private TextView position;
	private TextView emailAddress;
	private TextView phoneNumber;
	private TextView phoneNumber1;
	private Long rowID;
	ContactDB PhonebookDB = new ContactDB(this);
	private Cursor CursorList;
	Button cal;
	Button sen;
	Button cal1;
	Button sen1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		cal = (Button) findViewById(R.id.callb);
		cal.setOnClickListener(new OnClickListener()
		{
		public void onClick(View arg0){
			String num = phoneNumber.getText().toString();
			 Intent callIntent = new Intent(Intent.ACTION_CALL);
	         callIntent.setData(Uri.parse("tel:" + num));
	         startActivity(callIntent);
		}
		});
		sen = (Button) findViewById(R.id.message);
		sen.setOnClickListener(new OnClickListener()
		{
		public void onClick(View arg0){
			String sendsms = phoneNumber.getText().toString();
			Intent intent = new Intent(ContactDetails.this,com.blobinfo.contact.Sendsms.class);
			 intent = new Intent(ContactDetails.this,Sendsms.class);
			 intent.putExtra("Number", sendsms);
			startActivity(intent);
		}
		});
		cal1 = (Button) findViewById(R.id.callb1);
		cal1.setOnClickListener(new OnClickListener()
		{
		public void onClick(View arg0){
			String num = phoneNumber1.getText().toString();
			 Intent callIntent = new Intent(Intent.ACTION_CALL);
	         callIntent.setData(Uri.parse("tel:" + num));
	         startActivity(callIntent);
		}
		});
		sen1 = (Button) findViewById(R.id.message2);
		sen1.setOnClickListener(new OnClickListener()
		{
		public void onClick(View arg0){
			String sendsms = phoneNumber1.getText().toString();
			Intent intent = new Intent(ContactDetails.this,com.blobinfo.contact.Sendsms.class);
			 intent = new Intent(ContactDetails.this,Sendsms.class);
			 intent.putExtra("Number", sendsms);
			startActivity(intent);
		}
		});
		

		PhonebookDB.open();
		try
		{
			firstName=(TextView)findViewById(R.id.txtFirstName);
			lastName=(TextView)findViewById(R.id.txtLastName);
			title=(TextView)findViewById(R.id.title);
			position=(TextView)findViewById(R.id.position1);
			emailAddress=(TextView)findViewById(R.id.txtEmailID);
			phoneNumber=(TextView)findViewById(R.id.txtPhone);
			phoneNumber1=(TextView)findViewById(R.id.phonenu1);

			String position2=getIntent().getStringExtra("posit");
			rowID=Long.parseLong(position2);
			CursorList=PhonebookDB.getContacts(rowID);
			firstName.setText(CursorList.getString(1)+" ");
			lastName.setText(CursorList.getString(2));
			title.setText(CursorList.getString(3));
			position.setText(CursorList.getString(4));
			emailAddress.setText(CursorList.getString(5));
			phoneNumber.setText(CursorList.getString(6));
			phoneNumber1.setText(CursorList.getString(7));
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
