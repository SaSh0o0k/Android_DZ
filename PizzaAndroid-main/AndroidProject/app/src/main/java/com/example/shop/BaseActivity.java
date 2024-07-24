package com.example.shop;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int select = item.getItemId();
        if(select==R.id.register) {
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if(select==R.id.login) {
            Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        if(select==R.id.categories) {
            Intent intent = new Intent(BaseActivity.this, CategoryActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void setupBottomNavigationView(int id) {

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(id);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;

                int id = item.getItemId();
                if (id == R.id.page_1) {
                    intent = new Intent(BaseActivity.this, MainActivity.class);
                } else if (id == R.id.page_2) {
                    intent = new Intent(BaseActivity.this, LoginActivity.class);
                } else if (id == R.id.page_3) {
                    intent = new Intent(BaseActivity.this, CategoryActivity.class);
                } else {
                    return false;
                }
                startActivity(intent);
                return true;
            }
        });
    }
}
