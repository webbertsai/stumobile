package edu.stu.mobile.ui;

import android.content.Context;

public class Lodeing extends android.app.ProgressDialog {
	int time = 0;

	public Lodeing(Context context) {
		super(context);
	}

	public void show() {
		super.show();
		new Thread() {
			public void run() {
				try {
					sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Lodeing.this.dismiss();
			};
		}.start();

	}

	public void setTimeDismiss(int time) {
		this.time = time;
	}
}
