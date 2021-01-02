package com.dss.pepitolearning.ui.homeUI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dss.pepitolearning.R;
import com.dss.pepitolearning.models.Category;
import com.dss.pepitolearning.ui.adapters.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView categoryRecyclerView;
    ProductAdapter productAdapter;

    public void loadView(View view){

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = root.findViewById(R.id.rv_catalog);

        Category c1 = new Category();
        Category c2 = new Category();
        Category c3 = new Category();

        Category c4 = new Category();
        Category c5 = new Category();
        Category c6 = new Category();
        Category c7 = new Category();
        Category c8 = new Category();

        List<Category> categoryList = new ArrayList<>();

        c1.setCategoryName("Android");
        c2.setCategoryName("Swift");
        c3.setCategoryName("Rust");

        c4.setCategoryName("C4");
        c5.setCategoryName("C5");
        c6.setCategoryName("C6");
        c7.setCategoryName("C7");
        c8.setCategoryName("C8");



        /*c1.setImage("https://image.freepik.com/vector-gratis/ilustracion-concepto-estudiar_114360-1107.jpg");
        c2.setImage("https://image.freepik.com/vector-gratis/personas-enfocadas-que-estudian-escuela-linea_74855-5834.jpg");
        c3.setImage("https://image.freepik.com/vector-gratis/estudiante-femenino-escuchando-webinar-linea_74855-6461.jpg");

        c4.setImage("https://i.pinimg.com/originals/d5/44/ff/d544ffca4ecb461fc19da7e384cbc6d5.gif");
        c5.setImage("https://image.freepik.com/vector-gratis/estudiante-femenino-escuchando-webinar-linea_74855-6461.jpg");
        c6.setImage("https://image.freepik.com/vector-gratis/estudiante-femenino-escuchando-webinar-linea_74855-6461.jpg");
        c7.setImage("https://image.freepik.com/vector-gratis/estudiante-femenino-escuchando-webinar-linea_74855-6461.jpg");
        c8.setImage("https://image.freepik.com/vector-gratis/estudiante-femenino-escuchando-webinar-linea_74855-6461.jpg");*/

        c1.setImage("27731-study-methods.json");
        c2.setImage("41464-student-with-books.json");
        c3.setImage("41375-laptop-rocket.json");
        c4.setImage("41504-developer-is-programming-using-notebook.json");
        c5.setImage("41504-developer-is-programming-using-notebook.json");
        c6.setImage("41504-developer-is-programming-using-notebook.json");
        c7.setImage("41504-developer-is-programming-using-notebook.json");



        categoryList.add(c3);
        categoryList.add(c4);
        categoryList.add(c1);
        categoryList.add(c2);
        categoryList.add(c5);
        categoryList.add(c6);
        categoryList.add(c7);
        categoryList.add(c8);




        getAllCategory(getActivity(), categoryList);
        System.out.println("onCreateView se ha terminado");

        return root;
    }

    private void getAllCategory(Context c, List<Category> categoryList){
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        //RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);
        categoryRecyclerView.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(c, categoryList);
        categoryRecyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }
}