package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

public class ContactList extends AppCompatActivity {


    Button negative, btnCall, btnLocation;
    Dialog epicDialog;
    TextView title, message;
    ImageView close;

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "International Psychology Centre", "Malaysia Mental Health Association", "GRC Consulting Services", "InPsych Psychological & Counselling Services", ""
    };

    int[] listviewImage = new int[]{
            R.drawable.international_psychology_center, R.drawable.malaysia_mental_health_association, R.drawable.grc_consulting_services, R.drawable.inpsych_counselling_services, 0
    };

    String[] listviewShortDescription = new String[]{
            "03-27277434", "03-77825499", "03-22422813", "017-3311216",""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        final ListView androidListView = (ListView) findViewById(R.id.list_view);
        CustomAdapter customAdapter=  new CustomAdapter();
        androidListView.setAdapter(customAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                epicDialog = new Dialog(ContactList.this);
                epicDialog.setContentView(R.layout.pop_up_window);

                close = (ImageView)epicDialog.findViewById(R.id.close);
                btnCall = (Button)epicDialog.findViewById(R.id.btnCall);
                btnLocation = (Button)epicDialog.findViewById(R.id.btnLocation);
                title = (TextView)epicDialog.findViewById(R.id.popup_title);
                message = (TextView)epicDialog.findViewById(R.id.popup_message);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        epicDialog.dismiss();
                    }
                });

                btnCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri call = Uri.parse("tel:" + listviewShortDescription[pos]);
                        Intent surf = new Intent(Intent.ACTION_DIAL, call);
                        startActivity(surf);
                    }
                });

                btnLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + listviewTitle[pos]);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        //mapIntent.setPackage("com.google.android.apps.maps");

                        PackageManager packageManager = getPackageManager();
                        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                        boolean isIntentSafe = activities.size() > 0;
                        if(isIntentSafe)
                            startActivity(mapIntent);
                    }
                });

                epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                epicDialog.show();
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listviewTitle.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.listview_layout, null);

            TextView text1 = view1.findViewById(R.id.listview_item_title);
            TextView text2 = view1.findViewById(R.id.listview_item_short_description);
            ImageView image1 =  view1.findViewById(R.id.listview_image);

            text1.setText(listviewTitle[i]);
            text2.setText(listviewShortDescription[i]);
            image1.setImageResource(listviewImage[i]);

            return view1;
        }

    }
}

