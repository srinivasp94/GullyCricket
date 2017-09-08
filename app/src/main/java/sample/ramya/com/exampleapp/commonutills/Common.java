package sample.ramya.com.exampleapp.commonutills;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.mime.TypedFile;
import sample.ramya.com.exampleapp.R;
import sample.ramya.com.exampleapp.application.TATX;
import sample.ramya.com.exampleapp.constants.Constants;


public class Common {
    static final String LOG = Common.class.getSimpleName();
    public static final String INTERNET_UNABLEABLE = "Not connected to the internet. Please check your connection and try again.";
    public static final int TOAST_TIME = 2000;

    public static void customToast(final Context context, final String msg, final int millisec) {


        Activity activity = (Activity) context;

        Log.i("? - activity : " + activity);

        if (activity == null) {
            return;
        }

        (activity).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast toast = Toast.makeText(context, msg, millisec);
                toast.show();

            }
        });

    }

    public static boolean haveInternet(Context ctx) {
        try {
            NetworkInfo info = ((ConnectivityManager) ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();

            if (info == null || !info.isConnected()) {
                return false;
            }
        } catch (Exception e) {
            android.util.Log.d("err", e.toString());
        }
        return true;
    }

    public static void getAlertDialog(final Activity context, String title,
                                      String Message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(Message)
                .setCancelable(false)
                .setPositiveButton("Invite",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity_login_registration
                                context.finish();
                            }
                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public static void showAlertDialog(final Activity context, String title,
                                       String Message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(Message)
                .setCancelable(false)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity_login_registration
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        intent.putExtra("EXIT", true);
                        if (context.getIntent().getBooleanExtra("EXIT", false)) {
                            context.finish();
                        }
                        android.os.Process.killProcess(android.os.Process.myPid());

                    }
                })
                .setPositiveButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public static void getAlertDialogTripCancel(final Context context, String title,
                                                String Message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set title
        alertDialogBuilder.setTitle(title);
        // set dialog message
        alertDialogBuilder
                .setMessage(Message)
                .setCancelable(false)
                .setPositiveButton("GO ONLINE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.show();
        TextView messageText = (TextView) alertDialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        // show it
        alertDialog.show();

    }

    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static String retrieveContactRecord(Context activity, String phoneNo) {
        String contactId;
        String contactName = "";
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNo));
            String[] projection = new String[]{ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup.PHOTO_URI};
            String selection = null;
            String[] selectionArgs = null;
            String sortOrder = ContactsContract.PhoneLookup.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
            ContentResolver cr = activity.getContentResolver();
            if (cr != null) {
                Cursor resultCur = cr.query(uri, projection, selection, selectionArgs, sortOrder);
                if (resultCur != null) {
                    while (resultCur.moveToNext()) {
                        contactId = resultCur.getString(resultCur.getColumnIndex(ContactsContract.PhoneLookup._ID));
                        contactName = resultCur.getString(resultCur.getColumnIndexOrThrow(ContactsContract.PhoneLookup.DISPLAY_NAME));
                        String photoUri = resultCur.getString(resultCur.getColumnIndexOrThrow(ContactsContract.PhoneLookup.PHOTO_URI));
//                        Log.e("Info","Contact Id : "+contactId);
//                        Log.e("Info","Contact Display Name : "+contactName);
//                        Log.d("SUBBU", photoUri);
                        byte[] data = resultCur.getBlob(resultCur.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_URI));
                        if (data != null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                            Log.d("SUBBU", bitmap + "");
                        }
                        return contactName;

                    }
                    //resultCur.close();
                }
            }
        } catch (Exception sfg) {
            // Log.e("Error", "Error in loadContactRecord : "+sfg.toString());
        }
        return contactName;
    }//fn retrieveContactRecord

    public static long getContactIDFromNumber(String contactNumber, Context context) {
        String UriContactNumber = Uri.encode(contactNumber);
        long phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, UriContactNumber),
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while (contactLookupCursor.moveToNext()) {
            phoneContactID = contactLookupCursor.getLong(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();

        return phoneContactID;
    }

    /*date format YYYY-MM-DD to dd-mm-yyyy*/
    public static String convertDate_yyyy_MM_dd_TO_dd_MM_yyyy(String dt)
    {

        String d = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");

        try
        {
            Date date = formatter.parse(dt);
            d = formatter1.format(date);
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }

        return d;

    }

    /*date format  dd-mm-yyyy to YYYY-MM-DD */
    public static String dateformateDDMMYYYY(String dt) {
        String d = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dt);
            d = formatter1.format(date);


        } catch (Exception e) {
            // TODO: handle exception
        }
        return d;
    }

    /*date format  dd-mm-yyyy to YYYY-MM-DD */
    public static String dateformateByTmeZone(String dt) {
        String d = "";
        TimeZone TZ1 = TimeZone.getDefault();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss a");
        try {
            Date date = formatter1.parse(dt);
            d = formatter2.format(date);
            Log.i(": " + d);
            Date date1 = formatter2.parse(d);
            TimeZone TZ = TimeZone.getTimeZone(TZ1.getID());
            formatter2.setTimeZone(TZ);
            d = formatter2.format(date1);

            Log.i(":: " + d + "TZ1 " + TZ1.getDisplayName() + "TZ " + TZ.getDisplayName());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return d;
    }

    public static void showGPSDisabledAlertToUser(final Activity context, String title) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                context.startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public static String toTitleCase(String input) {
        input = input.toLowerCase();
        char c = input.charAt(0);
        String s = new String("" + c);
        String f = s.toUpperCase();
        return f + input.substring(1);
    }

    public static String getAddressString(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address address = addresses.get(0);

                StringBuilder sb = new StringBuilder("");
                String Feature = address.getFeatureName();
                String ThroughFrare = address.getSubThoroughfare();
                String Sub_Admin = address.getSubAdminArea();
                String Locality = address.getLocality();
                String Admin = address.getAdminArea();
                String Country = address.getCountryName();
                /*if (Feature != null) {
                    sb.append(Feature);
                }
                if (ThroughFrare != null) {
                    sb.append(","+ThroughFrare);
                }
                if (Sub_Admin != null) {
                    sb.append(","+Sub_Admin);
                }*/
                if (Locality != null) {
                    sb.append(Locality);
                }
                if (Admin != null) {
                    sb.append(", " + Admin);
                }
                if (Country != null) {
                    sb.append(", " + Country);
                }
                strAdd = sb.toString();
                android.util.Log.w("My Current", "" + sb.toString());
            } else {
                android.util.Log.w("My Current", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            android.util.Log.w("My Current", "Canont get Address!");
        }
        return strAdd;
    }

    public static String getCompleteAddressString(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(",");
                }
                strAdd = strReturnedAddress.toString();
                android.util.Log.w("My Current", "" + strReturnedAddress.toString());
            } else {
                android.util.Log.w("My Current", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            android.util.Log.w("My Current", "Canont get Address!");
        }
        return strAdd;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static String dateformater(String dt) {
        String d = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd:MMMM:yyyy");
        try {
            Date date = formatter.parse(dt);
            d = formatter1.format(date);


        } catch (Exception e) {
            // TODO: handle exception
        }
        return d;
    }

    public static Object stringToObject(String str) {
        try {
            return new ObjectInputStream(new Base64InputStream(
                    new ByteArrayInputStream(str.getBytes()), Base64.NO_PADDING
                    | Base64.NO_WRAP)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //eg String=17.12354,86.35466;  to double[] a={17.12354,86.35466};

    public static List<Double> stringToDubleArry(String latlngString) {
        List<Double> latlng = new ArrayList<>();

        String[] sourceArray = latlngString.split(",");

        for (int i = 0; i < sourceArray.length; i++) {
            String lat = sourceArray[i];
            latlng.add(Double.parseDouble(lat));
        }

        return latlng;
    }

    public static String[] spliteString(String stringForSplit, String splitFrom) {
        String[] array = stringForSplit.split(splitFrom);

        return array;
    }

    public static String constructLatLng(Location latlng) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(latlng.getLatitude()));
        stringBuilder.append(',');
        stringBuilder.append(String.valueOf(latlng.getLongitude()));
        String latlngString = stringBuilder.toString();

        return latlngString;
    }


    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(Color.parseColor("#ff6c2c"));
        canvas.drawCircle(finalBitmap.getWidth() / 2 + 0.7f,
                finalBitmap.getHeight() / 2 + 0.7f,
                finalBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }



    public static String dateformater_mon(String dt) {

        SimpleDateFormat currentFormat = new SimpleDateFormat("dd-MM-yyyy");

        SimpleDateFormat requiredFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = currentFormat.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return requiredFormat.format(date);


    }

    public static void customToast(Context context, String message) {
        customToast(context, message, Toast.LENGTH_LONG);
    }

    public static ProgressDialog showProgressDialog(Context context) {

        final ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Please Wait.....");

        progressDialog.setCancelable(false);

        if (!((Activity) context).isFinishing()) {

            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    progressDialog.show();

                }
            });
        }


        return progressDialog;


    }

    public static void dismissProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    public static void refreshActivity(Activity activity) {

        Intent intent = activity.getIntent();
        activity.finish();
        activity.startActivity(intent);

    }

    public static String getTwoDecimalRoundValueString(double value) {
        return new DecimalFormat("#.##").format(value);
    }


    public static <T> T getSpecificDataObject(Object object, Class<T> classOfT) {

        String jsonString = new Gson().toJson(object);

        return new Gson().fromJson(jsonString, classOfT);

    }

    public static String getStringFromResources(int stringId) {
        return TATX.getInstance().getApplicationContext().getResources().getString(stringId);
    }

    public static Gson getCustomGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static void setRoundedCroppedBackgroundImage(Context context, String profilePicUrl, final ImageView ivProfilePic, final int radius) {


        if (profilePicUrl == null || TextUtils.isEmpty(profilePicUrl)) {

            Log.i("Invalid profilePicUrl : " + profilePicUrl);

            return;

        }

        Picasso.with(context).load(profilePicUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

//                ivProfilePic.setImageBitmap(getRoundedCroppedBitmap(bitmap,150));
                ivProfilePic.setImageBitmap(getRoundedCroppedBitmap(bitmap, radius));

                Log.i("onBitmapLoaded");


            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.i("onBitmapFailed");

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.i("onPrepareLoad");

            }
        });


    }

    public static String getSuffixSARString(String string, String currency) {
        return string + " "+currency;
    }

    public static String getSuffixSARString(double doubleValue, String currency) {
        return getSuffixSARString(String.valueOf(doubleValue),currency);
    }


    public static void saveUserIdIntoSP(int userid) {

        getDefaultSP(TATX.getInstance().getApplicationContext()).edit().putInt(Constants.SharedPreferencesKeys.USERID, userid).commit();

    }

    public static void saveLoginStatusIntoSP(boolean status) {

        getDefaultSP(TATX.getInstance().getApplicationContext()).edit().putBoolean(Constants.SharedPreferencesKeys.LOGIN_STATUS, status).commit();

    }

    public static TypedFile getTypedFile(File file) {
        Log.i("file : " + file);
        return (file != null) ? new TypedFile("multipart/form-data", file) : null;
    }




    /**
     * Compares two version strings.
     * <p>
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     * The result is a positive integer if str1 is _numerically_ greater than str2.
     * The result is zero if the strings are _numerically_ equal.
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     */
    public static int versionCompare(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.length - vals2.length);
    }



    public static float getDimensionResourceValue(int resourceId) {
        return TATX.getInstance().getResources().getDimension(resourceId);
    }

    public static int getColorFromResource(int resourceId) {
        return TATX.getInstance().getResources().getColor(resourceId);
    }




    public static class Log {
        public static void i(String string) {

            android.util.Log.i("SKP", string);


        }
    }


    public static SharedPreferences getDefaultSP(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context);

    }


    public static int getUserIdFromSP(Context context) {

        return getDefaultSP(context).getInt(Constants.SharedPreferencesKeys.USERID, 0);

    }


    public static void showContentView(Activity activity, boolean showStatus) {

        int visibleStatus = showStatus ? View.VISIBLE : View.GONE;

        activity.findViewById(android.R.id.content).setVisibility(visibleStatus);


    }


    public static void appendLog(String text) {
        File logFile = new File("sdcard/tatx_log.file");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean isValidMobileNumber(String phoneNumber) {

        if (phoneNumber.matches("123456789") || phoneNumber.matches("1234567890")
                || phoneNumber.matches("0123456789") || phoneNumber.matches("0000000000")
                || phoneNumber.matches("1111111111") || phoneNumber.matches("2222222222")
                || phoneNumber.matches("3333333333") || phoneNumber.matches("4444444444")
                || phoneNumber.matches("5555555555") || phoneNumber.matches("6666666666")
                || phoneNumber.matches("7777777777") || phoneNumber.matches("8888888888")
                || phoneNumber.matches("9999999999") || phoneNumber.matches("0000000000")) {
            return false;
        }
        return true;
    }


    public static void setDefaultLanguage(Context activity) {
        String local = Locale.getDefault().getLanguage();
        Locale locale = new Locale(local);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config,
                activity.getResources().getDisplayMetrics());
    }

    public static void setLanguage(Context context, String language) {
        Locale locale1 = new Locale(language);
        Locale.setDefault(locale1);
        Configuration config1 = new Configuration();
        config1.locale = locale1;
        context.getResources().updateConfiguration(config1,
                context.getResources().getDisplayMetrics());

    }




    public static Bitmap byteToBitMap(byte[] _image) {
        //convert byte to bitmap take from contact class


        ByteArrayInputStream imageStream = new ByteArrayInputStream(_image);

        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        return theImage;
    }

    public static int getAge(int _year, int _month, int _day) {

        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d, a;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(_year, _month, _day);
        a = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        if (a < 0)
            throw new IllegalArgumentException("Age < 0");
        return a;
    }

    public static String getStringResourceText(int resourceId) {
        return TATX.getInstance().getResources().getString(resourceId);
    }


    public static File saveBitmap(Bitmap bitmap, String path) {
        File file = null;
        if (bitmap != null) {
            file = new File(path);
            try {
                FileOutputStream outputStream = null;
                try {
//                    outputStream = new FileOutputStream(path); //here is set your file path where you want to save or also here you can set file object directly
                    outputStream = new FileOutputStream(file); //here is set your file path where you want to save or also here you can set file object directly

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream); // bitmap is your Bitmap instance, if you want to compress it you can compress reduce percentage
                    // PNG is a lossless format, the compression factor (100) is ignored
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void removeZeroPrefix(final Context context, final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // android.util.Log.d("beforeTextChanged","No need to enter1 "+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // android.util.Log.d("beforeTextChanged","No need to enter 2 "+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // android.util.Log.d("beforeTextChanged","No need to enter 3 "+s.toString());
                if (s.toString().startsWith("0")) {
                    //      android.util.Log.d("beforeTextChanged","No need to enter 4 "+s.toString());
                    editText.setText("");
                    Common.customToast(context, "No need to enter 0");
                }
            }
        });

    }

    public static void restrictSpaceInPasswordField(final Context context, final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                android.util.Log.d("beforeTextChanged", "No need to enter1 " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                android.util.Log.d("beforeTextChanged", "No need to enter 2 " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                android.util.Log.d("beforeTextChanged", "Space not allowed. " + s.toString());
                if (s.toString().contains(" ")) {
                    //      android.util.Log.d("beforeTextChanged","No need to enter 4 "+s.toString());
                    editText.setText(s.toString().replaceAll("\\s", ""));
                    editText.setSelection(s.toString().replaceAll("\\s", "").length());
                    Common.customToast(context, "Space not allowed.");
                }
            }
        });

    }

    public static String ignoreZeroPrefix(final EditText editText) {

        String text = editText.getText().toString().trim();

        return (NumberUtils.isDigits(text) && text.startsWith("0")) ? text.substring(1) : text;

    }

    public static Bitmap getBitmapFromByteArray(byte[] bytes) {

        return bytes == null ? null : BitmapFactory.decodeStream(new ByteArrayInputStream(bytes));

    }

    public static Bitmap getBitmap(Context mContext, String url) throws IOException {
        // Bitmap mBitmap;
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });

        return builder.build().with(mContext).load(url).get();
        // return mBitmap;
    }


    public static void hideSoftKeyboard(final Activity activity, View rootView) {


        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

                if (activity.getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                }


            }

        });


    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
//            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
    public static void hideSoftKeyboardFromDialog(Dialog dialog, Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (dialog.getCurrentFocus() != null) {
//            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            inputMethodManager.hideSoftInputFromWindow(dialog.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void setupUI(final Activity activity, View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(activity, innerView);
            }
        }
    }

    public static void requestChildFocus(View view) {
        view.requestFocus();
        view.getParent().requestChildFocus(view, view);
    }

    public static void setCircleImageBackgroundFromUrl(Context context, ImageView profileImgView, Uri profilePicUrl) {

        if (profilePicUrl != null && !TextUtils.isEmpty(profilePicUrl.toString())) {

            Picasso.with(context).load(profilePicUrl).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(profileImgView);

        } else {
            Log.i("Unable to set Bg Image.");
        }


    }




    public static String getCalculatedDate(String dateFormat, int days) {
        Calendar cal = Calendar.getInstance(new Locale("en"));
        SimpleDateFormat s = new SimpleDateFormat(dateFormat,new Locale("en"));
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }
    public static Calendar getCalculatedDate(int days) {
        Calendar cal = Calendar.getInstance(new Locale("en"));
        cal.add(Calendar.DAY_OF_YEAR, days);
        return  cal;
    }

    static String pad(long c) {
        if (c >= 10) {
            return String.valueOf(c);
        } else {
            return '0' + String.valueOf(c);
        }
    }


    public static void copy(String string, Context context) {
        if (string.trim().length() > 0) {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboardMgr = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardMgr.setText(string.trim());
            } else {
                // this api requires SDK version 11 and above, so suppress warning for now
                android.content.ClipboardManager clipboardMgr = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(getStringFromResources(R.string.copied), string.trim());
                clipboardMgr.setPrimaryClip(clip);
            }
            customToast(context, getStringFromResources(R.string.copied), TOAST_TIME);
        }
    }

    public static String base64Encode(String token) {
        String encodedBytes = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);

        return encodedBytes;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("web.tatx.com"); //You can replace it with your name

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }



    public static Bitmap getBitmapFromView(View view)
    {

        Log.i("? - view.getWidth() : " + view.getWidth());
        Log.i("? - view.getHeight() : " + view.getHeight());

        Log.i("? - view.getMeasuredWidth() : " + view.getMeasuredWidth());
        Log.i("? - view.getMeasuredHeight() : " + view.getMeasuredHeight());

        view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(b);

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.draw(c);

        return b;


    }


}

