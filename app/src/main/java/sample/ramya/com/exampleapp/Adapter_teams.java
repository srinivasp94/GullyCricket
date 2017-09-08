package sample.ramya.com.exampleapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import sample.ramya.com.exampleapp.commonutills.DbBitmapUtility;
import sample.ramya.com.exampleapp.customviews.RoundedImageView;
import sample.ramya.com.exampleapp.database.Dbhandler;
import sample.ramya.com.exampleapp.database.ModelPlayer;

import static sample.ramya.com.exampleapp.R.drawable.cricket_image_background;

/**
 * Created by elancer on 7/3/2017.
 */

public class Adapter_teams extends RecyclerView.Adapter<Adapter_teams.ViewHolder> {

    Context context;
    View itemView;
    Dbhandler dbhandler;

    private List<ModelPlayer> listPlayers;

    public Adapter_teams(Context context, List<ModelPlayer> listPlayers) {
        this.context = context;
        this.listPlayers = listPlayers;
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cricket_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        dbhandler = new Dbhandler(context);
        final ModelPlayer modelPlayer = listPlayers.get(position);
        holder.name_of_player.setText(modelPlayer.getPlayer_name());
        holder.Score_player.setText(modelPlayer.getPlayer_score());
        if(modelPlayer.getIsOut()==0) {
            holder.outststus.setText("NOT OUT");
            holder.outststus.setTextColor(Color.BLUE);
        }
        else{
            holder.outststus.setText("OUT");
            holder.outststus.setTextColor(Color.RED);

        }
        // Log.d("getImagebytes()",modelPlayer.getImagebytes());
        if (modelPlayer.getImagebytes() != null && modelPlayer.getImagebytes() != "") {
            Bitmap bp = DbBitmapUtility.stringToBitMap(modelPlayer.getImagebytes());
            holder.profile.setImageBitmap(bp);
//            Picasso.with(context).load(String.valueOf(bp)).error(R.drawable.ic_menu_camera).into(holder.profile);
        } else {
            // holder.profile.setImageResource(R.drawable.ic_menu_camera);
        }

        //for display purpose
        if (modelPlayer.getIsChecked() == 0) {
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
        }

        //change while clicked
       /* holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (modelPlayer.getIsChecked() == 0) {
                    if (modelPlayer.getIsOut() == 0) {
                        modelPlayer.setIsChecked(1);
                        dbhandler.updateCheckstatusA(new ModelPlayer(0, "", "", "", 0, 1, ""));
                    }
                    else
                    {
                        holder.checkBox.setChecked(false);
                    }
                } else {
                    modelPlayer.setIsChecked(0);
                }
            }
        });

    }

    @Override
    public long getItemId(int i) {
        return listPlayers.size();
    }

    @Override
    public int getItemCount() {
        return listPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name_of_player, Score_player,outststus;
        RoundedImageView profile;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            name_of_player = (TextView) itemView.findViewById(R.id.tv_name_of_player);
            Score_player = (TextView) itemView.findViewById(R.id.tv_score_of_player);
            profile = (RoundedImageView) itemView.findViewById(R.id.iv_cric_prifile_pic);
            outststus = (TextView) itemView.findViewById(R.id.tv_outstatus);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_check_player);
            Score_player.setOnClickListener(this);
            checkBox.setOnClickListener(this);
//            checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
            /*checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
*/
        }

        @Override
        public void onClick(View iteView) {

            if (mListener != null) {
                mListener.onItemClick(iteView, getPosition());
            }


        }
    }
}
