package edu.stu.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class main extends Activity {

	// Declaration
	TextView About;
	GridView FunctionMenu;

	private void findViews() {
		About = (TextView) findViewById(R.id.About);
		FunctionMenu = (GridView) findViewById(R.id.FunctionMenu);
	}

	private void init() {
		About.setText(getString(R.string.stu_name) + " | " + getString(R.string.stu_zipcode) + getString(R.string.stu_address) + " | TEL:" + getString(R.string.stu_phnoe));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		init();
	}

}
