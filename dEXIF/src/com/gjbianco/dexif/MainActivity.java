package com.gjbianco.dexif;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private static final int SELECT_PHOTO = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String imagePath = null; // user will select an image
		// this contains the path to it
		ImageView imgView;
		imgView = (ImageView) findViewById(R.id.image_display_main);

		if(imagePath != null) {
			// from phone path
			// in this case, should be the userselected file
			imgView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
		} else {
			// from resources
			// in this case, should be a temporary image or "blank" image
			//TODO
			//imgView.setImageResource(R.drawable.blankSelectorImage);
		}

		final Button selectPictureButton = (Button) findViewById(R.id.image_selector_main);
		selectPictureButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// on pressing select picture button, launch picture selection
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch(requestCode) {
		case SELECT_PHOTO:
			if(resultCode == Activity.RESULT_OK) {
				if(requestCode == SELECT_PHOTO) {
					Uri selectedImageUri = data.getData();
					InputStream imageStream = null;
					try {
						imageStream = getContentResolver().openInputStream(selectedImageUri);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
					
					// TODO: change image displayed to this bitmap
				}
			}
		}
	}
}
