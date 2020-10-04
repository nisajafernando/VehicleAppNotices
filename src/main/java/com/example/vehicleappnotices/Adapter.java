
// IT19170176
// FERNANDO W.N.D
// CarMart Notices

package com.example.vehicleappnotices;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder> /*implements Filterable */{

    private Context context;
    private ArrayList<Model> arrayList;
    ArrayList<Model> arrayListAll;
    //dataBase object

    DatabaseHelper databaseHelper;

    public Adapter(ArrayList<Model>arrayList){
        this.arrayList=arrayList;
        this.arrayListAll = new ArrayList<>(arrayList);
    }

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListAll = new ArrayList<>(arrayList);
        //initialize here
        databaseHelper = new DatabaseHelper(context);
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false );
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        Model model = arrayList.get(position);
        //get  for view
        final String id = model.getId();
        final String image = model.getImage();
        final String heading = model.getHeading();
        final String name = model.getName();
        final String mobile = model.getMobile();
        final String email = model.getEmail();


        final String notice_info = model.getNotice_info();
        final String add_notice = model.getAdd_notice();
        final String update_notice = model.getUpdate_notice();

        holder.profileIv.setImageURI(Uri.parse(image));
        holder.heading.setText(heading);
        holder.name.setText(name);
        holder.mobile.setText(mobile);
        holder.email.setText(email);


        holder.notice_info.setText(notice_info);


        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+heading,
                        ""+name,
                        ""+mobile,
                        ""+email,
                        ""+image,
                        ""+notice_info,
                        ""+add_notice,
                        ""+update_notice

                );
            }


        });
        //when long press on item ,show an alert for delete an item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                deleteDialog(
                    ""+id
                );
                return false;
            }
        });
    }
    private void deleteDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_delete);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteInfo(id);
               ((ShowRecords)context).onResume();
                Toast.makeText(context,"Delete Successfully!",Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void editDialog(String position, final String id, final String heading, final String name, final String mobile, final String email, final String image, final String notice_info, final String add_notice, final String update_notice) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want update?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_edit);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, EditRecordActivity.class);
                intent.putExtra("ID", id);
                intent.putExtra("HEADING", heading);
                intent.putExtra("NAME", name);
                intent.putExtra("MOBILE", mobile);
                intent.putExtra("EMAIL", email);
                intent.putExtra("IMAGE", image);
                intent.putExtra("NOTICE_INFO", notice_info);
                intent.putExtra("ADD_NOTICE", add_notice);
                intent.putExtra("UPDATE_NOTICE", update_notice);
                intent.putExtra("editMode", true);


                context.startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView profileIv;
        TextView heading, name, mobile, email,province, notice_info;
        ImageButton editButton;

        public Holder(@NonNull View itemView) {
            super(itemView);

            profileIv = itemView.findViewById(R.id.profileIv);
            heading = itemView.findViewById(R.id.heading);
            name = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);
            email = itemView.findViewById(R.id.email);
            notice_info = itemView.findViewById(R.id.notice_info);
            editButton = itemView.findViewById(R.id.editBtn);

        }
    }
}
