package com.inventure.myaquaregypt.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.inventure.myaquaregypt.Adapter.ExpandListAdapter;
import com.inventure.myaquaregypt.Fragments.FavouriteFragment;
import com.inventure.myaquaregypt.Fragments.HomeFragment;
import com.inventure.myaquaregypt.Fragments.profileFragment;
import com.inventure.myaquaregypt.InternalStorage.mySharedPreference;
import com.inventure.myaquaregypt.Model.Login.UserInfo;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.myUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private static final String TAG = "Home_Activity_home";
    private Fragment fragment;
    private FragmentTransaction transaction;
    private Button buttonnavegation;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    private ListView listView;

    private TextView nav_header_profile_name_TV, nav_header_profile_email_TV;

    public static String headerOfCategory;
    public static String idForCategoryOfNav;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] values;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myUtils.setLocale(this);

        getUserData();
        //id
        dl = findViewById(R.id.activity_main);
        buttonnavegation = findViewById(R.id.navegation_button_menue);
        expListView = findViewById(R.id.lvExp);
        listView = findViewById(R.id.list_item);


        firstFragmentRun();
        listViewOfNavDrawer();
        prepareListData();
        expandableListViewForNavDrawer();


        buttonnavegation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(GravityCompat.START);
            }
        });

        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader.add("Categories");

        List<String> top250 = new ArrayList<String>();
        top250.add("Residential");
        top250.add("Holiday Home");
        top250.add("Commercial");
        top250.add("Medical");
        top250.add("Lunch Soon");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data

    }


    @OnClick(R.id.searchBtn)
    public void onSearchClick() {
        startActivity(new Intent(MainActivity.this, FilterActivity.class));
    }


    private void listViewOfNavDrawer() {

        if (mySharedPreference.getUserOBJ() != "") {
            values = new String[]{
                    "News and Events",
                    "Our Services",
                    "Our Magazine",
                    "Search",
                    "About Us",
                    "Contact Us",
                    "Terms and Policies",
                    "Log out "
            };
        } else {
            values = new String[]{
                    "News and Events",
                    "Our Services",
                    "Our Magazine",
                    "Search",
                    "About Us",
                    "Contact Us",
                    "Terms and Policies",
                    "Log in "
            };
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //news and events
                if (position == 0) {
                    Intent go = new Intent(MainActivity.this, NewsAndEvents.class);
                    startActivity(go);

                }
                //our services
                else if (position == 1) {
                    startActivity(new Intent(MainActivity.this, OurServicesActivity.class));


                }
                //OurMagazine
                else if (position == 2) {
                    startActivity(new Intent(MainActivity.this, OurMagazine.class));


                }//Search
                else if (position == 3) {
                    startActivity(new Intent(MainActivity.this, FilterActivity.class));


                    //about us
                } else if (position == 4) {
                    startActivity(new Intent(MainActivity.this, AboutUsActivity.class));


                }
                //contact us
                else if (position == 5) {
                    startActivity(new Intent(MainActivity.this, ContactUsActivity.class));

                }
                //terms and policies
                else if (position == 6) {
                    startActivity(new Intent(MainActivity.this, TermsAndPoliciesActivity.class));


                }
                //logout
                else if (position == 7) {


                    if (mySharedPreference.getUserOBJ() != "") {


                        mySharedPreference.setUserOBJ("");
                        startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        finish();

                    } else {

                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }

                }

            }
        });

        closeExpandableListWhenScroll();


    }


    private void closeExpandableListWhenScroll() {

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState != 0) {
                    expListView.collapseGroup(0);

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }


    private void expandableListViewForNavDrawer() {


        listAdapter = new ExpandListAdapter(MainActivity.this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                //Residential
                if (childPosition == 0) {
                    startActivity(new Intent(MainActivity.this, CategoryActivity.class));

                    idForCategoryOfNav = String.valueOf(myUtils.Residential);
                    headerOfCategory = "Residential";

                }
                //Holiday Home
                else if (childPosition == 1) {
                    startActivity(new Intent(MainActivity.this, CategoryActivity.class));

                    idForCategoryOfNav = String.valueOf(myUtils.HolidayHome);
                    headerOfCategory = "Holiday Home";

                }
                //commercial
                else if (childPosition == 2) {
                    startActivity(new Intent(MainActivity.this, CategoryActivity.class));

                    idForCategoryOfNav = String.valueOf(myUtils.Commercial);
                    headerOfCategory = "Commercial";
                }
                //medical
                else if (childPosition == 3) {
                    startActivity(new Intent(MainActivity.this, CategoryActivity.class));

                    idForCategoryOfNav = String.valueOf(myUtils.Medical);
                    headerOfCategory = "Medical";
                }
                //LunchSoon
                else if (childPosition == 4) {
                    startActivity(new Intent(MainActivity.this, CategoryActivity.class));

                    idForCategoryOfNav = String.valueOf(myUtils.LunchSoon);
                    headerOfCategory = "Lunch Soon";


                }

                return false;
            }
        });

    }

    private void firstFragmentRun() {
        fragment = new HomeFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();


        TextView textView1 = findViewById(R.id.home_text_id);
//        textView1.setTextColor(Color.parseColor("#FF0000"));
        textView1.setTextColor(this.getResources().getColor(R.color.HomeBottomColorClicked));


        ImageView imageButton1 = findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.home_red);


    }


    public void favourite(View view) {
        fragment = new FavouriteFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "favouriteFragment");
        transaction.commitNow();


        TextView textView1 = findViewById(R.id.favert_text_id);
//        textView1.setTextColor(Color.parseColor("#FF0000"));
        textView1.setTextColor(this.getResources().getColor(R.color.HomeBottomColorClicked));

        TextView textView2 = findViewById(R.id.home_text_id);
//        textView2.setTextColor(Color.parseColor("#cccccc"));
        textView2.setTextColor(this.getResources().getColor(R.color.HomeBottomColorUnClicked));

        TextView textView3 = findViewById(R.id.account_text_id);
//        textView3.setTextColor(Color.parseColor("#cccccc"));
        textView3.setTextColor(this.getResources().getColor(R.color.HomeBottomColorUnClicked));


        ImageView imageButton = findViewById(R.id.favert_icon_id);
        imageButton.setImageResource(R.drawable.ic_favorite_filled);

        ImageView imageButton1 = findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.ic_home_unfilled);

        ImageView imageButton2 = findViewById(R.id.account_icon_id);
        imageButton2.setImageResource(R.drawable.ic_person_unfilled);


    }

    public void home(View view) {
        fragment = new HomeFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();


        TextView textView1 = findViewById(R.id.home_text_id);
//        textView1.setTextColor(Color.parseColor("#FF0000"));
        textView1.setTextColor(this.getResources().getColor(R.color.HomeBottomColorClicked));


        TextView textView2 = findViewById(R.id.favert_text_id);
//        textView2.setTextColor(Color.parseColor("#cccccc"));
        textView2.setTextColor(this.getResources().getColor(R.color.HomeBottomColorUnClicked));

        TextView textView3 = findViewById(R.id.account_text_id);
//        textView3.setTextColor(Color.parseColor("#cccccc"));
        textView3.setTextColor(this.getResources().getColor(R.color.HomeBottomColorUnClicked));


        ImageView imageButton = findViewById(R.id.favert_icon_id);
        imageButton.setImageResource(R.drawable.ic_favorite_unfiiled);

        ImageView imageButton1 = findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.home_red);

        ImageView imageButton2 = findViewById(R.id.account_icon_id);
        imageButton2.setImageResource(R.drawable.ic_person_unfilled);


    }

    public void profile(View view) {
        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {
                fragment = new profileFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_home, fragment, "profile");
                transaction.commitNow();
            }

        } catch (Exception e) {
            startActivity(new Intent(this, LoginActivity.class));

        }


        TextView textView1 = findViewById(R.id.favert_text_id);
//        textView1.setTextColor(Color.parseColor("#cccccc"));
        textView1.setTextColor(this.getResources().getColor(R.color.HomeBottomColorUnClicked));

        TextView textView2 = findViewById(R.id.home_text_id);
//        textView2.setTextColor(Color.parseColor("#cccccc"));
        textView2.setTextColor(this.getResources().getColor(R.color.HomeBottomColorUnClicked));

        TextView textView3 = findViewById(R.id.account_text_id);
//        textView3.setTextColor(Color.parseColor("#FF0000"));
        textView3.setTextColor(this.getResources().getColor(R.color.HomeBottomColorClicked));


        ImageView imageButton = findViewById(R.id.favert_icon_id);
        imageButton.setImageResource(R.drawable.ic_favorite_unfiiled);

        ImageView imageButton1 = findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.ic_home_unfilled);

        ImageView imageButton2 = findViewById(R.id.account_icon_id);
        imageButton2.setImageResource(R.drawable.ic_person_filled);


    }


    public void getUserData() {
        NavigationView navigationView = findViewById(R.id.Navigation_drawer);
        View headerView = navigationView.getHeaderView(0);
        nav_header_profile_name_TV = headerView.findViewById(R.id.nav_header_profile_name_TV);
        nav_header_profile_email_TV = headerView.findViewById(R.id.nav_header_profile_email_TV);
        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {
//                Glide.with(this).load(userPOJO.getImage()).into(profile_photo);
                nav_header_profile_name_TV.setText(userPOJO.getUsername());
                nav_header_profile_email_TV.setText(userPOJO.getEmail());
            }


        } catch (Exception e) {
            e.printStackTrace();
            nav_header_profile_name_TV.setText("Please Sign In First");
            nav_header_profile_email_TV.setText("");
            nav_header_profile_name_TV.setTextColor(getResources().getColor(R.color.Red));

        }
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                finishAffinity();
            } else {
                Toast.makeText(this, "press again to exit ", Toast.LENGTH_SHORT).show();
            }

            backPressedTime = System.currentTimeMillis();
        }


    }
}