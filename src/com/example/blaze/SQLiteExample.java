package com.example.blaze;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlGetInfo, sqlDelete, sqlModify, update, view;
	EditText hotness, name, sqlRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		name = (EditText) findViewById(R.id.etName);
		hotness = (EditText) findViewById(R.id.etHotness);
		update = (Button) findViewById(R.id.bUpdate);
		view = (Button) findViewById(R.id.bView);

		sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);

		update.setOnClickListener(this);
		view.setOnClickListener(this);

		sqlModify.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bUpdate:

			boolean didItWork = true;
			try {
				String ourName = name.getText().toString();
				String ourHotness = hotness.getText().toString();

				HotOrNot entry = new HotOrNot(this);
				entry.open();
				entry.createEntry(ourName, ourHotness);
				entry.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}

			break;
		case R.id.bView:
			Intent i = new Intent("com.example.blaze.SQLVIEW");
			startActivity(i);
			break;
		case R.id.bGetInfo:
			didItWork = true;
			try {
				String s = sqlRow.getText().toString();
				long l = Long.parseLong(s);
				HotOrNot hon = new HotOrNot(this);
				hon.open();
				String returnedName = hon.getName(l);
				String returnedHotness = hon.getHotness(l);
				hon.close();

				name.setText(returnedName);
				hotness.setText(returnedHotness);
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}

			break;
		case R.id.bSQLmodify:
			didItWork = true;
			try {
				String mName = name.getText().toString();
				String mHotness = hotness.getText().toString();

				String sRow = sqlRow.getText().toString();
				long lRow = Long.parseLong(sRow);

				HotOrNot ex = new HotOrNot(this);
				ex.open();
				ex.updateEntry(lRow, mName, mHotness);
				ex.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}

			break;
		case R.id.bSQLdelete:
			didItWork = true;
			try {
				String sRow1 = sqlRow.getText().toString();
				long lRow1 = Long.parseLong(sRow1);
				HotOrNot ex1 = new HotOrNot(this);

				ex1.open();
				ex1.deleteEntry(lRow1);
				ex1.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck yea");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		}
	}

}
