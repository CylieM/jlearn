package com.example.jlearnn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private FirebaseAuth.AuthStateListener authStateListener;
    TextView txtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.framelayout);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        String email = getIntent().getStringExtra("email");  // Retrieve the email
        loadFragment(HomeFragment.newInstance(email), true);
        if (user == null ) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();
                if(itemId == R.id.navHome){
                    loadFragment(new HomeFragment(), false);
                } else if (itemId == R.id.navLeaderboard){
                    loadFragment(new LeaderboardFragment(), false);
                } else if (itemId == R.id.navKanashoot) {
                    loadFragment((new KanaShootFragment()), false);
                } else if (itemId == R.id.navNihongorace){
                    loadFragment(new NihongoRaceFragment(), false);
                } else if (itemId == R.id.navProfile){ //nav Profile
                    View view = bottomNavigationView.findViewById(R.id.navProfile);
                    PopupMenu popupMenu = new PopupMenu(UserActivity.this, view);
                    popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int itemId = item.getItemId();
                            if (itemId == R.id.navProfile) {
                                loadFragment(new ProfileFragment(), false);
                                return true;
                            } else if (itemId == R.id.navLogout) {
                                // Handle logout
                                firebaseAuth.signOut();
                                // Redirect to login activity
                                Intent I = new Intent(UserActivity.this, LoginActivity.class);
                                startActivity(I);
                                return true;
                            } else if (itemId == R.id.navAdminPanel) {
                                // Open the UploadActivity when the AdminPanel item is clicked
                                Intent intent = new Intent(UserActivity.this, CRUD.class);
                                startActivity(intent);
                                return true;
                            }
                            return false;
                        }
                    });
                    return false;  // return false to not select the profile item
                }

                return true;
            }
        });

        loadFragment(new HomeFragment(), true);

    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.framelayout, fragment);
        }else{
            fragmentTransaction.replace(R.id.framelayout, fragment);
        }
        fragmentTransaction.commit();
    }
    }

