package com.zamzam.youtube;

import java.lang.reflect.Field;
import java.text.AttributedString;
import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;

public class mainActivity extends Activity {

	private Button button1;
	private Button button2;
	private TableLayout tableLayout;
	private MediaPlayer mp = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tableLayout = (TableLayout) this.findViewById(R.id.tableLayout1);

		Field[] fields = R.raw.class.getFields();
		for (Field f : fields)
			try {
				Button button = new Button(getApplicationContext() );
				button.setText(f.getName());
				button
						.setOnClickListener(new MyOnClickListener(f
								.getInt(null)));
				tableLayout.addView(button);
				
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

	}

	class MyOnClickListener implements OnClickListener {

		private int resourceId;

		public MyOnClickListener(int resourceId) {
			this.resourceId = resourceId;
		}

		@Override
		public void onClick(View v) {
			try {
				if (mp != null) {
					mp.release();
				}
				mp = MediaPlayer.create(getApplicationContext(), resourceId);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				if (!mp.isPlaying()) {
					mp.seekTo(0);
					mp.start();
				} else {
					mp.stop();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}