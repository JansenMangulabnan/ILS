package com.example.ils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

  private Context context;
  Activity activity;
  private ArrayList order_id, order_name, order_address, order_number, order_flavor, order_size;
  int pos;
  CustomAdapter(Context context,
                ArrayList order_id,
                ArrayList order_name,
                ArrayList order_address,
                ArrayList order_number,
                ArrayList order_flavor,
                ArrayList order_size){
    this.context = context;
    this.order_id = order_id;
    this.order_name = order_name;
    this.order_address = order_address;
    this.order_number = order_number;
    this.order_flavor = order_flavor;
    this.order_size = order_size;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.my_row, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.order_id_txt.setText(String.valueOf(order_id.get(position)));
    holder.order_name_txt.setText(String.valueOf(order_name.get(position)));
    holder.order_address_txt.setText(String.valueOf(order_address.get(position)));
    holder.order_number_txt.setText(String.valueOf(order_number.get(position)));
    holder.order_flavor_txt.setText(String.valueOf(order_flavor.get(position)));
    holder.order_size_txt.setText(String.valueOf(order_size.get(position)));

  }

  @Override
  public int getItemCount() {
    return order_id.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView order_id_txt, order_name_txt, order_address_txt, order_number_txt, order_flavor_txt, order_size_txt;
    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      order_id_txt.findViewById(R.id.order_id_txt);
      order_name_txt.findViewById(R.id.order_name_txt);
      order_address_txt.findViewById(R.id.order_address_txt);
      order_number_txt.findViewById(R.id.order_number_txt);
      order_flavor_txt.findViewById(R.id.order_flavor_txt);
      order_size_txt.findViewById(R.id.order_size_txt);
    }
  }
}
