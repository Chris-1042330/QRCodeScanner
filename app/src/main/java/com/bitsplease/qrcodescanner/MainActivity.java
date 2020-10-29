package com.bitsplease.qrcodescanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends ScanActivity {

    Button scanBtn;
    private Button exit;

    TextView result;
    public static int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;
    private Intent data;

    //private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseS,mDatabaseA,mDatabaseC;
    Calendar c = Calendar.getInstance();
    Date today = new Date();
    SimpleDateFormat df = null;
    String formattedDate = "null";
    SimpleDateFormat sdf = null;
    public boolean vin = true;
    String formattedDatee = "null";
    String currentDateTimeString = "null";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scanBtn = (Button) findViewById(R.id.button);
        exit = (Button) findViewById(R.id.main_exit);

        result = (TextView) findViewById(R.id.result);

        String actualdate = today.toString();

        df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c.getTime());

        mDatabaseS = FirebaseDatabase.getInstance().getReference().child("Dates").child(formattedDate).child("science");
        mDatabaseA = FirebaseDatabase.getInstance().getReference().child("Dates").child(formattedDate).child("arts");
        mDatabaseC = FirebaseDatabase.getInstance().getReference().child("Dates").child(formattedDate).child("commerce");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getBaseContext(), ScanActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        if(vin == true)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    scanBtn.performClick();
                }
            }, 2000);
        }


    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


            if (data != null) {
                final Barcode barcode = data.getParcelableExtra("barcode");
                result.post(new Runnable() {
                    @Override
                    public void run() {


                        String V = barcode.displayValue;
                        int l = V.length();
                        String[] branch = V.split("\\s+");


                        sdf = new SimpleDateFormat("hh:mm a");
                        currentDateTimeString = sdf.format(today);


                        if (branch[0].equals("Science") || branch[0].equals("science")) {

                            Calendar c1 = Calendar.getInstance();
                            SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                            formattedDatee = df1.format(c1.getTime());
                            V = V.substring(7, l);
                            final String display = V;
                            HashMap<String, String> hash = new HashMap<String, String>();

                            hash.put("name", V);
                            hash.put("time", currentDateTimeString);
                            mDatabaseS.child(formattedDatee).setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, display, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                        }

                        if (branch[0].equals("Arts") || branch[0].equals("arts")) {
                            Calendar c2 = Calendar.getInstance();
                            SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                            formattedDatee = df1.format(c2.getTime());
                            V = V.substring(4, l);
                            final String display = V;
                            HashMap<String, String> hash = new HashMap<String, String>();

                            hash.put("name", V);
                            hash.put("time", currentDateTimeString);
                            mDatabaseA.child(formattedDatee).setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, display, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });


                        }

                        if (branch[0].equals("Commerce") || branch[0].equals("commerce")) {
                            Calendar c3 = Calendar.getInstance();
                            SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                            formattedDatee = df1.format(c3.getTime());
                            V = V.substring(8, l);
                            final String display = V;
                            HashMap<String, String> hash = new HashMap<String, String>();

                            hash.put("name", V);
                            hash.put("time", currentDateTimeString);
                            mDatabaseC.child(formattedDatee).setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, display, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                        }

                        if (vin == true) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    scanBtn.performClick();
                                }
                            }, 2000);
                        }
                    }
                });
            }
        }
    }
}