package com.example.myhospital;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.splashscreen.SplashScreen;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView textView;
    private View mainContent;
    private FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.splashText);
        mainContent = findViewById(R.id.hospitalMessage);
        fab = findViewById(R.id.fab);
        ImageView menuIcon = findViewById(R.id.imageView);
        ImageView profileIcon = findViewById(R.id.imageView2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "onCreate: ");

        fab.setOnClickListener(view ->
                Snackbar.make(view, "It's a FAB", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        menuIcon.setOnClickListener(this::onMenuClick);
        profileIcon.setOnClickListener(this::onProfileClick);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (isNetworkAvailable()) {
                mainContent.setVisibility(View.VISIBLE);
                textView.setText(R.string.app_name);
                fab.show();
            } else {
                Snackbar.make(findViewById(android.R.id.content), R.string.no_internet_message, Snackbar.LENGTH_INDEFINITE).show();
                fab.hide();
            }
        }, 3000);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_search) {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setNavigationOnClickListener(v ->
                Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    @SuppressLint("SetTextI18n")
    public void onMenuClick(View view) {
        textView.setText("Menu clicked!");
        animateClick(view);
    }

    @SuppressLint("SetTextI18n")
    public void onProfileClick(View view) {
        textView.setText("Profile clicked!");
        animateClick(view);
    }

    private void animateClick(View view) {
        view.setAlpha(0.5f);
        view.postDelayed(() -> view.setAlpha(1f), 200); // Restore after 200ms
    }
}
