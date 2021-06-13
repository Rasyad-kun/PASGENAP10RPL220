package com.example.pasgenap10rpl220;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AccessController;
import java.util.List;

import javax.security.auth.callback.Callback;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    private Callback callback;
    private List<File> fileList;
    private LayoutInflater inflater;
    private int posisi;
    private View view;

    interface Callback {
        void onClick(int position);
    }

    public FileAdapter(Context context, List<File> fileList, Callback callback) {
        this.fileList = fileList;
        this.inflater = LayoutInflater.from(context);
        this.callback = callback;
    }

    //membuat kolom baru
    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_data, parent, false);
        return new FileViewHolder(view);
    }

    //mereplace id item item_data dengan data arrayList
    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.textTitle.setText(fileList.get(position).getName());
        holder.textDeskripsi.setText(fileList.get(position).getTelp());
        holder.imgIcon.setImageResource(fileList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return (fileList != null) ? fileList.size() : 0;
    }

    //menghubungkan id item yang ada di recyclerview dan item_data dengan operetor setempat
    public class FileViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView textTitle, textDeskripsi;
        private ImageView imgIcon;
        CardView cardView;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            itemView.setOnCreateContextMenuListener(this);
            textTitle = itemView.findViewById(R.id.tv_title);
            textDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            imgIcon = itemView.findViewById(R.id.tv_icon);
            cardView = itemView.findViewById(R.id.tv_cardview);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });
        }


        //Jika bagian card view di tahan lama maka akan menjalankan baris program di bawah ini!
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
            posisi = getAdapterPosition();
            Edit.setOnMenuItemClickListener(onClickContextMenu);
            Delete.setOnMenuItemClickListener(onClickContextMenu);
        }
    }

    private final MenuItem.OnMenuItemClickListener onClickContextMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 1:
                    //Do stuff
                    Toast.makeText(view.getContext(), "Mencoba mengedit kontak "+fileList.get(posisi).getName(), Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    //Delete data, butuh konfirmasi dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Kau ingin menghapus kontak ini?")
                            .setCancelable(false)
                            .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(view.getContext(), "Selesai Menghapus kontak "+fileList.get(posisi).getName(), Toast.LENGTH_SHORT).show();
                                    fileList.remove(posisi);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            //Set your icon here
                            .setTitle("Menghapus Data")
                            .setIcon(android.R.drawable.ic_dialog_alert);
                    AlertDialog alert = builder.create();
                    alert.show();//showing the dialog
                    break;
            }
            return true;
        }
    };
}
