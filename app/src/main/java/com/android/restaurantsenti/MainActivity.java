package com.android.restaurantsenti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.restaurantsenti.ui.FragmentHome;
import com.android.restaurantsenti.ui.FragmentOverallReviews;
import com.android.restaurantsenti.ui.FragmentProfile;
import com.android.restaurantsenti.ui.FragmentSavedRestaurants;
import com.android.restaurantsenti.ui.FragmentSearch;
import com.android.restaurantsenti.ui.FragmentYelpDirect;
import com.google.android.material.navigation.NavigationView;

import static com.android.restaurantsenti.LoginActivity.USER_EXISTENCE_SHARED_PREF_NAME;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    NavController navController;
    FragmentManager fragmentManager;
    Button drawerButton;
    Button backButton;
    RelativeLayout relativeLayoutToolBar;

    FragmentOverallReviews fragmentOverallReviews;
    FragmentProfile fragmentProfile;
    FragmentSearch fragmentSearch;
    FragmentSavedRestaurants fragmentSavedRestaurants;
    FragmentYelpDirect fragmentYelpDirect;
    FragmentHome fragmentHome;
    Fragment fragment = new Fragment();
    String tag = "";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        backButton.setVisibility(View.GONE);
        fragmentManager = getSupportFragmentManager();
        clickListeners();
        initiateFragments();
        inflateInitialFragment();
    }

    private void inflateInitialFragment() {
        fragment = fragmentHome;
        pushFragment(fragment, Util.TAG_FRAGMENT_HOME);
    }

    private void setNavigationViewListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragment = new Fragment();
                drawerCaseChecking(item.getItemId());
                pushFragment(fragment, tag);
                //This is for closing the drawer after acting on it
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void drawerCaseChecking(int itemId) {
        switch (itemId) {

            case R.id.menu_home:
                fragment = fragmentHome;
                tag = Util.TAG_FRAGMENT_HOME;
                break;
            case R.id.menu_profile:
                fragment = fragmentProfile;
                tag = Util.TAG_FRAGMENT_PROFILE;
                break;
            case R.id.menu_search_restaurant:
                fragment = fragmentSearch;
                tag = Util.TAG_FRAGMENT_SEARCH;
                break;
            case R.id.menu_saved_restaurant:
                fragment = fragmentSavedRestaurants;
                tag = Util.TAG_FRAGMENT_SAVED_RESTAURANTS;
                break;
            case R.id.menu_yelp_direct:
                fragment = fragmentYelpDirect;
                tag = Util.TAG_FRAGMENT_YELP_DIRECT;
                break;
            case R.id.menu_overall_reviews:
                fragment = fragmentOverallReviews;
                tag = Util.TAG_FRAGMENT_OVERALL_REVIEWS;
                break;
            case R.id.menu_logout:
                tag = Util.TAG_FRAGMENT_PROFILE;
                logout();
                break;
            default:
        }
    }

    private void logout() {
        //to set value of user login boolean to false in Shared Preference so that we can check
        // on app resume whether the user has already logged in or not
        sharedPreferences = this.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("isUserLoggedIn", false).apply();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void pushFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //to check if the fragment has not already been instantiated
        if (!fragment.isAdded()) {
            // fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            fragmentTransaction.add(R.id.nav_host_fragment, fragment, tag);
            fragmentTransaction.addToBackStack(String.valueOf(true));
            fragmentTransaction.commit();
        } else {
            refreshFragment(tag);
        }
    }

    public void refreshFragment(String tag) {
        Fragment fragment;
        fragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (fragment != null) {
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commit();
        }
    }

    public void clickListeners() {
        drawerButton.setOnClickListener(v -> openDrawerMenu());
        setNavigationViewListener();

        backButton.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void openDrawerMenu() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }


    private void findView() {
        drawerButton = findViewById(R.id.drawer_button);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        relativeLayoutToolBar = findViewById(R.id.app_toolbar);
        backButton = findViewById(R.id.back_button);
    }

    private void initiateFragments() {
        fragmentOverallReviews = new FragmentOverallReviews();
        fragmentProfile = new FragmentProfile();
        fragmentSavedRestaurants = new FragmentSavedRestaurants();
        fragmentYelpDirect = new FragmentYelpDirect();
        fragmentSearch = new FragmentSearch();
        fragmentHome = new FragmentHome();
    }

}