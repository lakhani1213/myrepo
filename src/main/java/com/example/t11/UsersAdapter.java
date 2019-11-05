package com.example.t11;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    JSONArray jsonArray;

    public UsersAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            JSONObject jsonObject = (JSONObject) jsonArray.get(position);

            String timestamp = jsonObject.getString("timestamp");
            String commodity = jsonObject.getString("commodity");
            String market = jsonObject.getString("market");
            String variety = jsonObject.getString("variety");
            String minPrice = jsonObject.getString("min_price");
            String modalPrice = jsonObject.getString("modal_price");
            String maxPrice = jsonObject.getString("max_price");
            String arrivalDate = jsonObject.getString("arrival_date");

            Log.i("user- - ->",commodity);
            holder.txtName.setText(variety +"-->"+ minPrice+"-"+maxPrice);
            holder.txtEmail.setText(arrivalDate+"-"+market);

                if (variety.equals("Arhar Dal(Tur)")) {
                holder.ImgView.setImageResource(R.drawable.strawberry);
            } else {
                holder.ImgView.setImageResource(R.drawable.m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtEmail;
        ImageView ImgView;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            ImgView = itemView.findViewById(R.id.ImgView);
        }
    }
}
