package edu.stu.mobile;

import edu.stu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StumobileActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);

	}

	public void NewStu(View v) {
		startActivity(new Intent(StumobileActivity.this, edu.stu.mobile.NewStudentsMain.class));
	}

}