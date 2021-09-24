package com.sudarsan.newsapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceFragment extends Fragment {


    String api="ab0d74b21eaa4faf958dd45a927efd0e";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewscience;
    private String category="science";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sciencefragment,null);


        recyclerViewscience = v.findViewById(R.id.recycelrviewsciencepage);
        modelClassArrayList=new ArrayList<>();
        recyclerViewscience.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewscience.setAdapter(adapter);

        findNes();

        return v;
    }

    private void findNes() {

        ApiUtilites.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                if (response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });

    }

}
