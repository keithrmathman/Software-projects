package com.example.admin.positivibesnew;

import com.example.admin.positivibesnew.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.widget.AdapterView;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;
FullscreenActivity MyAndroidAppActivity;
    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private  Spinner spinner1;
    private Button PhotoSubmit1;
    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    AnimationDrawable emojiAnime;


    final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button1= (Button) v;
            ((Button) v).setText("It's A GO!");


            emojiAnime.start();
emojiAnime.setEnterFadeDuration(4000);
           Intent i = new Intent(FullscreenActivity.this,nextSlide.class );
          //  Intent i = new Intent(FullscreenActivity.this,nextSlide.class);
            startActivity(i);
        }


    };

   // Spinner dropdown = (Spinner) findViewById(R.id.spinner1);

   // final Button AnimeButton = (Button)  findViewById(R.id.button);

    //animationButton.setOnClickListener(new OnClickListener){


    public void ClickButton(View v){
        Button button = (Button) v;
        final Button animationButton = (Button) findViewById(R.id.button);
        MotionEvent event = null;
        ((Button) v).setText("Are You Ready");
        animationButton.setOnClickListener(listener);
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                           // delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
       // findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        ImageView imageview = (ImageView) findViewById (R.id.emojiView);
        imageview.setBackgroundResource(R.drawable.emoji_animation);
        emojiAnime = (AnimationDrawable) imageview.getBackground();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
       // delayedHide(100);
    }

    public void addItemstoSpinner1() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });}
           // View.OnTouchListener mDelayHideTouchListener;

            public void addListenerOnButton() {

                spinner1 = (Spinner) findViewById(R.id.spinner1);

                PhotoSubmit1 = (Button) findViewById(R.id.PhotoSubmit);

                PhotoSubmit1.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FullscreenActivity.this, "OnClickListener: " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()), Toast.LENGTH_SHORT).show();
                    }
                });


                /**
                 * Touch listener to use for in-layout UI controls to delay hiding the
                 * system UI. This is to prevent the jarring behavior of controls going away
                 * while interacting with activity UI.
                 */
                View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (AUTO_HIDE) {
                            //   delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                        return false;
                    }


                };

                Handler mHideHandler = new Handler();
                Runnable mHideRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mSystemUiHider.hide();
                    }
                };
            }

            /**
             * Schedules a call to hide() in [delay] milliseconds, canceling any
             * previously scheduled calls.
             */
            //private void delayedHide(int delayMillis) {
            //          Object mHideRunnable;
            //        mHideHandler.removeCallbacks(mHideRunnable);
            // mHideHandler.postDelayed(mHideRunnable, delayMillis);
            //}
            public void onTouchDropDown(View v) {
                Button DropButton = (Button) v;
                PhotoSubmit1.setText("Selected!");
                selectImage();
            }



        public void selectImage(){
            Button buttonRef = null;

            buttonRef.findViewById(R.id.PhotoSubmit);
            if (buttonRef.findViewById(R.id.PhotoSubmit).equals("Camera"))
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 1);
            }
            else if (buttonRef.findViewById(R.id.PhotoSubmit).equals("Gallery"))
            {
                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);

            }
            else if (buttonRef.findViewById(R.id.PhotoSubmit).equals("Cancel")) {

            }
        }

  //  intent.putExtra("MyUriKey", .toString);
    ImageView viewImage;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImage = data.getData();
        data.putExtra("MyUriKey", selectedImage.toString());
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    viewImage.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

               // Uri selectedImage = data.getData();
                data.putExtra("MyUriKey", selectedImage.toString());
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w(" image from gallery.", picturePath+"");
                viewImage.setImageBitmap(thumbnail);
            }

        }
    }




    public boolean onClickEvent(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_BUTTON_PRESS) {
                    emojiAnime.start();
                    return true;
                }
                return false;
            }}





