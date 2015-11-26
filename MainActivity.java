/*
 * Copyright (c) 2015-2015 by Shanghai shootbox Information Technology Co., Ltd.
 * Create: 2015/11/12 2:37:44
 * Project: T_ShootBox_Net
 * File: MainActivity.java
 * Class: MainActivity
 * Module: app
 * Author: yangyankai
 * Version: 1.0
 */

package com.shootbox.t_shootbox_net;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button= (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
//				startActivity(new Intent(getApplicationContext(), UpFileTest.class));

				new Thread(new Runnable() {
					@Override
					public void run()
					{
						ConnectWeb c=new ConnectWeb();

					Log.e("ceshi",	c.uploadFile(getApplicationContext() )  );
					}
				}).start();

			}
		});
	}
}
