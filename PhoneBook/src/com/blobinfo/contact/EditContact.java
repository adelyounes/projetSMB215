package com.blobinfo.contact;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditContact extends Activity {

	private EditText edtFirstName;
	private EditText edtLastName;
	private EditText edtTitle;
	private EditText edtPosition;
	private EditText edtEmailAddress;
	private EditText edtPhoneNumber;
	private EditText edtPhoneNumber1;
	private Long rowID;
	ContactDB PhonebookDB = new ContactDB(this);
	private Cursor CursorList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		PhonebookDB.open();

		edtFirstName=(EditText)findViewById(R.id.edtFirstName);
		edtLastName=(EditText)findViewById(R.id.edtLastName);
		edtTitle=(EditText)findViewById(R.id.edtitle);
		edtPosition=(EditText)findViewById(R.id.edposition);
		edtEmailAddress=(EditText)findViewById(R.id.edtEmailID);
		edtPhoneNumber=(EditText)findViewById(R.id.edtPhone);
		edtPhoneNumber1=(EditText)findViewById(R.id.editphone1);
		String position=getIntent().getStringExtra("posit");
		rowID=Long.parseLong(position);
		CursorList=PhonebookDB.getContacts(rowID);
		edtFirstName.setText(CursorList.getString(1));
		edtLastName.setText(CursorList.getString(2));
		edtTitle.setText(CursorList.getString(3));
		edtPosition.setText(CursorList.getString(4));
		edtEmailAddress.setText(CursorList.getString(5));
		edtPhoneNumber.setText(CursorList.getString(6));
		edtPhoneNumber1.setText(CursorList.getString(7));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit_contact, menu);
		return true;
	}
	
	//Save the edited the content
	public void saveContact(View view)
	{
		String strFirstName = edtFirstName.getText().toString();
		String strLastName = edtLastName.getText().toString();
		String strTitle = edtTitle.getText().toString();
		String strPosition = edtPosition.getText().toString();
		String strEmailID = edtEmailAddress.getText().toString();
		String strMobile01 = edtPhoneNumber.getText().toString();
		String strMobile02 = edtPhoneNumber1.getText().toString();
		PhonebookDB.updateContacts(rowID, strFirstName, strLastName,strTitle,strPosition, strEmailID, strMobile01,strMobile02);
		PhonebookDB.close();
		finish();

		Intent contactDetails = new Intent(this,ContactDetails.class);
		contactDetails.putExtra("posit",rowID.toString());
		startActivity(contactDetails);
	}

	@Override
	public void onBackPressed() {
		try
		{
			finish();
			PhonebookDB.close();
			Intent contactDetails = new Intent(this, ContactDetails.class);
			contactDetails.putExtra("posit",rowID.toString());
			startActivity(contactDetails);
		}
		catch(Exception e)
		{
		}
	}
}


