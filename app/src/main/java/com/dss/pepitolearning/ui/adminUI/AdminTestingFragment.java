package com.dss.pepitolearning.ui.adminUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dss.pepitolearning.R;
import com.dss.pepitolearning.dao.ProductDao;
import com.dss.pepitolearning.models.Product;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

public class AdminTestingFragment extends Fragment {

    private AdminTestingViewModel adminTestingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adminTestingViewModel =
                new ViewModelProvider(this).get(AdminTestingViewModel.class);


        View root = inflater.inflate(R.layout.fragment_admin_testing, container, false);

        final TextView textView = root.findViewById(R.id.text_admin_testing);
        final TextView listProducts = root.findViewById(R.id.txt_list_products);
        final Button btn_listProducts = root.findViewById(R.id.btn_list_products);
        final Button bnt_add_Products = root.findViewById(R.id.btn_add_product);


        btn_listProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDao pdao = new ProductDao(getContext());
                Gson g = new Gson();
                String json = g.toJson(pdao.findAll());
                System.out.println(json);
                listProducts.setText(json);
            }
        });

        bnt_add_Products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product( );
                ProductDao pdao = new ProductDao(getContext());
                pdao.save(p);
                Snackbar.make(root, "Added" + p.toJson(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        adminTestingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                textView.setText(s);


            }
        });
        return root;
    }
}