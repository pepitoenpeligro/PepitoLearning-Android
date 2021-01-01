package com.dss.pepitolearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dss.pepitolearning.models.Course;
import com.dss.pepitolearning.models.PlayList;
import com.dss.pepitolearning.ui.adapters.OneProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class CoursePage extends AppCompatActivity {

    RecyclerView courseRecyclerView;

    OneProductAdapter oneProductAdapter;

    TextView member, rating, name, price;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_course_page);

        member = findViewById(R.id.members);
        rating = findViewById(R.id.rating);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);

        //apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);

        courseRecyclerView = findViewById(R.id.course_recycler);

        ///Call<List<Course>> call = apiInterface.getCourseContent();

        /*call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {

                List<Course> courseList = response.body();

                getCourseContent(courseList.get(0).getPlayList());
                //lets run it


                member.setText(courseList.get(0).getMember());
                rating.setText(courseList.get(0).getRating());
                name.setText(courseList.get(0).getCourseName());
                price.setText("₹ "+courseList.get(0).getPrice());

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(CoursePage.this, "No response from server", Toast.LENGTH_SHORT).show();
            }
        });*/

        List<Course> courseList = new ArrayList<>();
        Course c1 = new Course();
        c1.setCourseName("AAAA");
        c1.setPrice("4.55");
        c1.setMember("Miembro");
        c1.setRating("1");
        Course c2 = new Course();
        c2.setCourseName("AAAA");
        c2.setPrice("4.55");
        c2.setMember("Miembro");
        c2.setRating("1");

        PlayList p1 = new PlayList();
        p1.setName("PlayList 1");
        p1.setTime("44:00");

        List<PlayList> playlist =  new ArrayList<>();
        playlist.add(p1);

        getCourseContent(playlist);

    }

    private void getCourseContent(List<PlayList> playLists){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        courseRecyclerView.setLayoutManager(layoutManager);
        oneProductAdapter = new OneProductAdapter(this, playLists);
        courseRecyclerView.setAdapter(oneProductAdapter);
        oneProductAdapter.notifyDataSetChanged();

    }
}
