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
import com.dss.pepitolearning.api.APIProductGet;
import com.dss.pepitolearning.models.Course;
import com.dss.pepitolearning.ui.adapters.OneCartItemAdapter;
import com.dss.pepitolearning.ui.adapters.ProductAdapter;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements APIProductGet.OnTaskCompleted  {

    private HomeViewModel homeViewModel;
    RecyclerView categoryRecyclerView;
    ProductAdapter productAdapter;

    OneCartItemAdapter carritoAdapter;
    RecyclerView carritoRecyclerView;

    private List<Course> courseList;

    public static SlidingUpPanelLayout slidingUpPanel;

    private LottieAnimationView slidingAnimation;
    private LottieAnimationView goToPay;

    public void loadView(View view){

    }






    public void populateCourses(){


        /*courseList = new ArrayList<>();

        Course c1 = new Course();
        Course c2 = new Course();
        Course c3 = new Course();

        Course c4 = new Course();
        Course c5 = new Course();
        Course c6 = new Course();



        c1.setCategoryName("Neural Networks");
        c1.setImage("course_" + convertName(c1.getCategoryName()) + ".json");
        System.out.println("SACO");
        System.out.println(c1.getImage());
        c1.setPrice("11"+ "€");

        c2.setCategoryName("Rust");
        c2.setImage("course_" + convertName(c2.getCategoryName()) + ".json");
        c2.setPrice("12"+ "€");

        c3.setCategoryName("Webapps");
        c3.setImage("course_" + convertName(c3.getCategoryName()) + ".json");
        c3.setPrice("11"+ "€");

        c4.setCategoryName("Databases");
        c4.setImage("course_" + convertName(c4.getCategoryName()) + ".json");
        c4.setPrice("11"+ "€");

        c5.setCategoryName("C");
        c5.setImage("course_" + convertName(c5.getCategoryName()) + ".json");
        c5.setPrice("11"+ "€");

        c6.setCategoryName("Fundamentals");
        c6.setImage("course_" + convertName(c6.getCategoryName()) + ".json");
        c6.setPrice("11"+ "€");

        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        courseList.add(c5);
        courseList.add(c6);*/

        getAllCategory(getActivity(), courseList);
    }


    public void populateShoppingCart(){
        /*List<Course> carrito = new ArrayList<>();
        if(carrito.size()>1){
            carrito.add(courseList.get(0));
            carrito.add(courseList.get(1));
        }

        getAllCart(getActivity(), carrito);
        */


    }

    public static void bajaPanel(){
        Log.d("[HomeFragment]", "Colapsando");
        slidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        categoryRecyclerView = root.findViewById(R.id.rv_catalog);
        this.carritoRecyclerView = root.findViewById(R.id.shopping_cart_recicler_view);

        slidingAnimation = root.findViewById(R.id.animation_swipe_up);

        slidingUpPanel = root.findViewById(R.id.slidingUpPanel);

        slidingUpPanel.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if(newState == SlidingUpPanelLayout.PanelState.EXPANDED){
                    System.out.println("Ahora se ha expandido");
                    slidingAnimation.setRotation(180);
                    carritoAdapter.notifyDataSetChanged();

                }else if(newState == SlidingUpPanelLayout.PanelState.COLLAPSED){
                    System.out.println("Ahora está escondido");
                    slidingAnimation.setRotation(360);
                    carritoAdapter.notifyDataSetChanged();
                }


            }
        });

        goToPay = root.findViewById(R.id.animation_go_to_pay);

        goToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToPayActivity = new Intent(getActivity(), PayActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(goToPay,"pay_transition");

                Activity actividad = (Activity) getActivity();
                ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(actividad, pairs);


                getActivity().startActivity(goToPayActivity,op.toBundle());
            }
        });

        /*populateCourses();
        populateShoppingCart();*/

        APIProductGet apipg = new APIProductGet(this);
        apipg.setActivity(getActivity());
        apipg.execute();

        getAllCart(getActivity(), NavigationDrawerActivity.operateShoppingCart());
        return root;
    }

    private void getAllCart(Context c, List<Course> cart){
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);
        this.carritoRecyclerView.setLayoutManager(layoutManager);
        this.carritoAdapter = new OneCartItemAdapter(c, cart);
        carritoRecyclerView.setAdapter(carritoAdapter);
        carritoAdapter.notifyDataSetChanged();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //Toast.makeText(c, "on Move", Toast.LENGTH_SHORT).show();
                carritoAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(c, "Erased", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getAdapterPosition();
                //cart.remove(position);

                NavigationDrawerActivity.operateShoppingCart().remove(position);

                carritoAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(carritoRecyclerView);

    }

    private void getAllCategory(Context c, List<Course> courseList){
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        categoryRecyclerView.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(c, courseList);
        categoryRecyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTaskCompleted(List<Course> c) {
        System.out.println("Se completó la operacion y estoy llamando desde HomeFragment");

        System.out.println("Lo que he descargao");
        System.out.println(c);
        System.out.println("####################");
        this.courseList = new ArrayList<>();
        this.courseList.addAll(c);
        populateCourses();
        //populateShoppingCart();
    }
}