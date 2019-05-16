package com.example.cowrkstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cowrkstest.adapters.recyclerAdapter;
import com.example.cowrkstest.data.dataEnter;
import com.example.cowrkstest.data.post;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    dataEnter dh;
    dataEnter de;
    recyclerAdapter recycleradapter;
    LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    //this is the page size
    final int PAGE_SIZE = 5;
    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progress_circular);

        //For this test application I have taken data from JSON asset file. In real it might be taken from rest API
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dh = new Gson().fromJson(json, dataEnter.class);

        //Here I am getting data of 5 posts from this parsed JSON, but in real app we get 1st page with 5 post from rest api
        de = new dataEnter();
        de.setPosts(new ArrayList<post>());
        for (int i = 0; i < PAGE_SIZE; i++) {
            de.getPosts().add(dh.getPosts().get(i));
        }

        //setting up recycler view
        recycleradapter = new recyclerAdapter(de);
        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycleradapter);

        //adding scroll listener to enable pagination
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = true;
                            //Showing a non-interrupting progressbar to notify user of items being loaded.
                            progressBar.setVisibility(View.VISIBLE);
                            if (dh.getPosts().size() > de.getPosts().size()) {
                                //checking if last page has items less than page size
                                int limit = PAGE_SIZE;
                                int size = de.getPosts().size();
                                if (dh.getPosts().size() - size < PAGE_SIZE) {
                                    limit = dh.getPosts().size() - size;
                                }

                                for (int i = 0; i < limit; i++) {
                                    de.getPosts().add(dh.getPosts().get(size + i));
                                }

                                recycleradapter.notifyDataSetChanged();
                                loading = false;
                                progressBar.setVisibility(View.GONE);
                            } else {
                                loading = false;
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }
        });
    }

}
