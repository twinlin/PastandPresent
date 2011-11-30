package com.android.PastandPresent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class PastandPresentActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.icon);

		findviews();
		setListensers();
	}

	private Button button_calc;
	private EditText field_num;
	private ImageView view_suggest;

	private <view_suggest> void findviews() {
		button_calc = (Button) findViewById(R.id.submit);
		field_num = (EditText) findViewById(R.id.pnumber);
		view_suggest = (ImageView) findViewById(R.id.suggest);
	}
	
	private void openOptionsDialog() {
		new AlertDialog.Builder(PastandPresentActivity.this)
				.setTitle(R.string.error_title)
				.setMessage(R.string.error_msg)
				.setPositiveButton(R.string.ok_label,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								field_num.setText("");
							}
						}).show();
	}

	private void openMenuDialog() {
		new AlertDialog.Builder(PastandPresentActivity.this)
				.setTitle(R.string.about_title)
				.setMessage(R.string.about_msg)
				.setPositiveButton(R.string.ok_label,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})
				.setNegativeButton(R.string.home_label,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								Uri uri = Uri
										.parse(getString(R.string.home_uri));
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						}).show();
	}

	// Listen for button clicks
	private void setListensers() {
		button_calc.setOnClickListener(calcNUM);
	}

	private Button.OnClickListener calcNUM = new Button.OnClickListener() {
		public void onClick(View v) {
			try {

				String getNum = field_num.getText().toString();
				int numLength = getNum.length();

				if (numLength < 8) {
					openOptionsDialog();

				} else {
					numLength -= 8;
					field_num.setText("");
				}

				String cutNum = getNum.substring(numLength);
				String a = cutNum.substring(1 + 2 + 3 + 4 + 5 + 6 + 7 + 8);
				String b = a.substring(1 + 2);
				int opIntNum = Integer.parseInt(b);

				switch (opIntNum) {
				case 1:
					
					view_suggest.setVisibility(R.drawable.a);
					break;
				case 2:
					view_suggest.setVisibility(R.drawable.b);
					break;
				case 3:
					view_suggest.setVisibility(R.drawable.c);
					break;
				case 4:
					view_suggest.setVisibility(R.drawable.d);
					break;
				case 5:
					view_suggest.setVisibility(R.drawable.e);
					break;
				case 6:
					view_suggest.setVisibility(R.drawable.f);
					break;
				case 7:
					view_suggest.setVisibility(R.drawable.g);
					break;
				case 8:
					view_suggest.setVisibility(R.drawable.h);
					break;
				case 9:
					view_suggest.setVisibility(R.drawable.i);
					break;

				}
			} catch (Exception obj) {
			}

		}
	};

	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int NENU_QUIT = Menu.FIRST + 1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_ABOUT, 0, "關於...").setIcon(
				android.R.drawable.ic_menu_help);
		menu.add(0, NENU_QUIT, 0, "結束").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ABOUT:
			openMenuDialog();
			break;
		case NENU_QUIT:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
