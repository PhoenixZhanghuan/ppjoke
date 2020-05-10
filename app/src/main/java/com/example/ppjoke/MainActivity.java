package com.example.ppjoke;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.alibaba.fastjson.JSONObject;
import com.example.libnetwork.ApiResponse;
import com.example.libnetwork.GetRequest;
import com.example.libnetwork.JsonCallback;
import com.example.ppjoke.utils.NavGraphBuilder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

/**
 * @author zhanghuan
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(fragment);
        NavGraphBuilder.build(navController, this, fragment.getId());

        navView.setOnNavigationItemSelectedListener(this);

        GetRequest<JSONObject> request = new GetRequest<JSONObject>("http://www.baidu.com");
//        request.execute();

        request.execute(new JsonCallback() {
            @Override
            public void onSuccess(ApiResponse response) {
//                super.onSuccess(response);
                Log.e("zhang", response.message);
            }

            @Override
            public void onError(ApiResponse response) {
                super.onError(response);
                Log.e("zhang", response.message);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        navController.navigate(menuItem.getItemId());
        return !TextUtils.isEmpty(menuItem.getTitle());
    }
}
