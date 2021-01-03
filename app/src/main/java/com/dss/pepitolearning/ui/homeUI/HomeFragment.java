package com.dss.pepitolearning.ui.homeUI;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.dss.pepitolearning.NavigationDrawerActivity;
import com.dss.pepitolearning.PayActivity;
import com.dss.pepitolearning.R;
import com.dss.pepitolearning.models.Category;
import com.dss.pepitolearning.ui.adapters.OneCartItemAdapter;
import com.dss.pepitolearning.ui.adapters.ProductAdapter;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView categoryRecyclerView;
    ProductAdapter productAdapter;

    OneCartItemAdapter carritoAdapter;
    RecyclerView carritoRecyclerView;

    public static SlidingUpPanelLayout slidingUpPanel;


    private LottieAnimationView goToPay;

    public void loadView(View view){

    }

    public static void bajaPanel(){
        Log.d("[HomeFragment]", "Colapsando");
        slidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = root.findViewById(R.id.rv_catalog);
        this.carritoRecyclerView = root.findViewById(R.id.shopping_cart_recicler_view);

        slidingUpPanel = root.findViewById(R.id.slidingUpPanel);



        goToPay = root.findViewById(R.id.animation_go_to_pay);

        goToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToPayActivity = new Intent(getActivity(), PayActivity.class);
                /*goToPayActivity.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getActivity().startActivity(goToPayActivity);*/


                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(goToPay,"pay_transition");

                Activity actividad = (Activity) getActivity();
                ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(actividad, pairs);


                getActivity().startActivity(goToPayActivity,op.toBundle());
            }
        });

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


        List<Category> carrito = new ArrayList<>();
        carrito.add(c3);
        carrito.add(c1);

        getAllCart(getActivity(), carrito);




        /*rw.setLayoutManager(newLayout);
        OneCartItemAdapter ocia = new OneCartItemAdapter(getActivity(), carrito);
        rw.setAdapter(ocia);
        ocia.notifyDataSetChanged();*/




        /*ListView listView = root.findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                new String[] {"Producto 1", "Producto 2", "Producto 3", "Producto 4", "Producto 5", "Producto 6"}));*/



        return root;
    }

    private void getAllCart(Context c, List<Category> cart){
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);
        //RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);
        this.carritoRecyclerView.setLayoutManager(layoutManager);
        this.carritoAdapter = new OneCartItemAdapter(c, cart);
        carritoRecyclerView.setAdapter(carritoAdapter);
        carritoAdapter.notifyDataSetChanged();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(c, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(c, "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                cart.remove(position);
                carritoAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(carritoRecyclerView);

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