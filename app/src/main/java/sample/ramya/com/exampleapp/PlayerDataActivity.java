package sample.ramya.com.exampleapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sample.ramya.com.exampleapp.commonutills.DbBitmapUtility;
import sample.ramya.com.exampleapp.commonutills.Utility;
import sample.ramya.com.exampleapp.customviews.RoundedImageView;
import sample.ramya.com.exampleapp.database.Dbhandler;
import sample.ramya.com.exampleapp.database.ModelPlayer;
import sample.ramya.com.exampleapp.database.TeamBModel;

public class PlayerDataActivity extends AppCompatActivity {

    EditText name, score, team;
    Button singlePlayerDataSubmit, getplayer;
    ImageButton Ibutton_select_profilepic;
    RoundedImageView profile_pic;
    TextView txt;
    public String[] selectAction = {"CAMERA", "GALLERY"};
    Bitmap bitmap;
    byte[] imageInByte;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    DbBitmapUtility bitmapUtility;

    Bitmap thumbnail;

    // Activity request codes
    private static final int PERMISSIONS_REQUEST_CAPTURE_IMAGE = 1;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";

    private Uri fileUri; // file url to store image/video

    /*ModelPlayer modelPlayer;*/

    String playerName, playerScore, playerTeam, profile;
    Dbhandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_data);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle b = new Bundle();
        final String teamname = getIntent().getStringExtra("TEAM_NAME");
        bitmapUtility = new DbBitmapUtility(PlayerDataActivity.this);

        SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        final String ateam = preferences.getString("TEAMA", null);
        final String Bteam = preferences.getString("TEAMB", null);


        db = new Dbhandler(PlayerDataActivity.this);

        name = (EditText) findViewById(R.id.edt_name);
        score = (EditText) findViewById(R.id.edt_score);
        team = (EditText) findViewById(R.id.edt_team);
        team.setText(teamname);
        profile_pic = (RoundedImageView) findViewById(R.id.iv_profile_pic);
        Ibutton_select_profilepic = (ImageButton) findViewById(R.id.ib_change_profile_pic);

        Ibutton_select_profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        txt = (TextView) findViewById(R.id.txt_player);
        singlePlayerDataSubmit = (Button) findViewById(R.id.btn_submit_player_information);

        getplayer = (Button) findViewById(R.id.btn_getplayers);

        singlePlayerDataSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                playerName = name.getText().toString();
                playerScore = score.getText().toString();
                playerTeam = team.getText().toString();

                if (thumbnail != null) {
                    profile = bitmapUtility.bitMapToString(thumbnail);
                }

//                Drawable drawable = profile_pic.getDrawable();
//                BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
               /* bitmap = ((BitmapDrawable) profile_pic.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                imageInByte = stream.toByteArray();
                ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);*/
                if (!playerName.equals("")) {

                    Log.d("Insert: ", "Inserting ..");

                    //Intent intent = new Intent(PlayerDataActivity.this, ScoreDetails.class);
                    if (teamname.equals(ateam)) {
                        db.addPlayerDetails(new ModelPlayer(0, playerName, playerScore, playerTeam, 0, 0, profile));

//                        callIntent();
                    } else if (teamname.equals(Bteam)) {
                        db.addPlayerDetailsTeamB(new TeamBModel(0, playerName, playerScore, playerTeam, 0, 0, profile));
//                        callIntent();

                    }
                    finish();

                } else {
                    Ibutton_select_profilepic.requestFocus();
                    name.setError("Enter Player name");
                    name.requestFocus();
                    Toast.makeText(PlayerDataActivity.this, "Enter Player name", Toast.LENGTH_SHORT).show();
                }

                // startActivity(intent);

            }
        });

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(PlayerDataActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(PlayerDataActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)

                        if (ContextCompat.checkSelfPermission(PlayerDataActivity.this,
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            // User may have declined earlier, ask Android if we should show him a reason

                            if (ActivityCompat.shouldShowRequestPermissionRationale(PlayerDataActivity.this, Manifest.permission.CAMERA)) {
                                // show an explanation to the user
                                // Good practise: don't block thread after the user sees the explanation, try again to request the permission.
                            } else {
                                // request the permission.
                                // CALLBACK_NUMBER is a integer constants
                                ActivityCompat.requestPermissions(PlayerDataActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAPTURE_IMAGE);
                                // The callback method gets the result of the request.
                            }
                        } else {

                            cameraIntent();

                        }

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


   /* public void callIntent() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("PASSED_ITEM", (CharSequence) db);
        setResult(Activity.RESULT_OK);
        finish();
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (userChoosenTask.equals("Take Photo"))
//                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                                == PackageManager.PERMISSION_DENIED) {
//                            cameraIntent();
//                        } else if (userChoosenTask.equals("Choose from Library"))
//                            galleryIntent();
//                } else {
//                    //code for deny
//                }
                break;
            case PERMISSIONS_REQUEST_CAPTURE_IMAGE:
                   cameraIntent();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        // downsizing image as it throws OutOfMemory Exception for larger
        // images
        options.inSampleSize = 8;

        thumbnail = BitmapFactory.decodeFile(fileUri.getPath(),
                options);
        thumbnail = getResizedBitmap(thumbnail, 200);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        profile_pic.setImageBitmap(thumbnail);
//        profile = bitmapUtility.bitMapToString(thumbnail);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    private void onSelectFromGalleryResult(Intent data) {
        thumbnail = null;
        if (data != null) {
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail = getResizedBitmap(thumbnail, 200);
        profile_pic.setImageBitmap(thumbnail);
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

//        profile = bitmapUtility.bitMapToString(bm);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQUEST_CAMERA);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }



}
