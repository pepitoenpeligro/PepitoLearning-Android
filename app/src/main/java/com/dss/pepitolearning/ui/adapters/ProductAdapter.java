package com.dss.pepitolearning.ui.adapters;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;

import com.airbnb.lottie.LottieListener;
import com.dss.pepitolearning.OneProductActivity;
import com.dss.pepitolearning.R;
import com.dss.pepitolearning.models.Category;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CategoryViewHolder> {

    private Context context;
    List<Category> categoryList;

    public ProductAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_row_items, parent, false);


        // now here we create a recyclerview row items.
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.categoryName.setText(categoryList.get(position).getCategoryName());
        holder.totalCategory.setText(categoryList.get(position).getTotalCourses());



        //Glide.with(context).load(categoryList.get(position).getImage()).into(holder.categoryImage);


        LottieCompositionFactory.fromAsset(context.getApplicationContext(), categoryList.get(position).getImage()).addListener(new LottieListener<LottieComposition>() {
            @Override
            public void onResult(LottieComposition result) {



                holder.animacion.setComposition(result);
                holder.animacion.setVisibility(View.VISIBLE);
                holder.animacion.playAnimation();


                holder.animacion.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                        holder.animacion.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        holder.animacion.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });


            }
        });








        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToOneProductDescription = new Intent(context, OneProductActivity.class);
                System.out.println("Le paso a la siguiente actividad:");
                System.out.println(categoryList.get(position).getImage());
                goToOneProductDescription.putExtra("animation_file_name",  categoryList.get(position).getImage());
                goToOneProductDescription.putExtra("product_parcelable",  categoryList.get(position));

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(holder.animacion,"latransicion");

                Activity actividad = (Activity) context;
                ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(actividad, pairs);


                context.startActivity(goToOneProductDescription,op.toBundle());

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;
        TextView categoryName, totalCategory;
        LottieAnimationView animacion;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            //categoryImage = itemView.findViewById(R.id.course);
            categoryName = itemView.findViewById(R.id.course_name);
            totalCategory = itemView.findViewById(R.id.total_course);
            animacion = itemView.findViewById(R.id.course_lottie);

        }
    }


}
