package com.dss.pepitolearning.ui.adapters;

import android.animation.Animator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.dss.pepitolearning.R;
import com.dss.pepitolearning.models.Category;
import com.dss.pepitolearning.models.PlayList;

import java.util.List;

public class OneCartItemAdapter  extends RecyclerView.Adapter<OneCartItemAdapter.OneCartItemHolder>{

    Context context;

    List<Category> productos;

    public OneCartItemAdapter(Context context, List<Category> productos) {
        System.out.println("[OneCartItem][Adapter] constructor");
        this.context = context;
        this.productos = productos;
    }


    @NonNull
    @Override
    public OneCartItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("[OneCartItem][Adapter] onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false);


        // now here we create a recyclerview row items.
        return new OneCartItemAdapter.OneCartItemHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull OneCartItemAdapter.OneCartItemHolder holder, int position) {
        System.out.println("[OneCartItem][Adapter] onBindViewHolder");
        holder.title.setText(productos.get(position).getCategoryName());

        // El id es el precio
        holder.price.setText("10€");









        LottieCompositionFactory.fromAsset(context.getApplicationContext(), "2942-delete-bubble.json").addListener(new LottieListener<LottieComposition>() {
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

    }




    @Override
    public int getItemCount() {
        return productos.size();
    }


    public static class OneCartItemHolder extends RecyclerView.ViewHolder{


        TextView title, price;
        LottieAnimationView animacion;

        public OneCartItemHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.shopping_cart_course_name);
            price = itemView.findViewById(R.id.shopping_cart_course_price);
            animacion = itemView.findViewById(R.id.shopping_cart_course_lottie);

        }
    }
}
