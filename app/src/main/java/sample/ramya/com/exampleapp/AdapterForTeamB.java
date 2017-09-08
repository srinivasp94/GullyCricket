package sample.ramya.com.exampleapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import sample.ramya.com.exampleapp.commonutills.DbBitmapUtility;
import sample.ramya.com.exampleapp.customviews.RoundedImageView;
import sample.ramya.com.exampleapp.database.Dbhandler;
import sample.ramya.com.exampleapp.database.TeamBModel;

public class AdapterForTeamB extends RecyclerView.Adapter<AdapterForTeamB.ViewHolder> {

    Context context;
    View itemView;
    Dbhandler dbhandler;

    private List<TeamBModel> bModelList;

    public AdapterForTeamB(Context context, List<TeamBModel> bModelList) {
        this.context = context;
        this.bModelList = bModelList;
    }
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener =  onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cricket_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        dbhandler=new Dbhandler(context);
        final TeamBModel teamBModel = bModelList.get(position);
        holder.name_of_player.setText(teamBModel.getPlayer_nameB());
        holder.Score_player.setText(teamBModel.getPlayer_scoreB());
        if (teamBModel.getIsOut()==0){
            holder.out_StatusB.setText("Not Out");
            holder.out_StatusB.setTextColor(Color.BLUE);
        }
        else {
            holder.out_StatusB.setText("Out");
            holder.out_StatusB.setTextColor(Color.RED);
        }
        /*if (teamBModel.getIsChecked() == 0) {
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
        }*/
        if (teamBModel.getImagebytes()!=null && teamBModel.getImagebytes()!=""){
            Bitmap bitmap= DbBitmapUtility.stringToBitMap(teamBModel.getImagebytes());
            holder.profile.setImageBitmap(bitmap);
        }
        else {


        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (teamBModel.getIsChecked()==0) {
                        if (teamBModel.getIsOut() == 0) {

                            teamBModel.setIsChecked(1);
                            dbhandler.updateCheckstatusB(new TeamBModel(0, "", "", "", 0, 1, ""));
                        } else {
//                            teamBModel.setIsChecked(0);
//                    dbhandler.updateCheckstatusB(new TeamBModel(0,"","","",0,0,""));
                            holder.checkBox.setChecked(false);
                        }

                }
                else {
//                        holder.checkBox.setChecked(false);
                        teamBModel.setIsChecked(0);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return bModelList.size();
    }

    @Override
    public long getItemId(int i) {
        return bModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name_of_player, Score_player,out_StatusB;
        RoundedImageView profile;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            name_of_player = (TextView) itemView.findViewById(R.id.tv_name_of_player);
            Score_player = (TextView) itemView.findViewById(R.id.tv_score_of_player);
            out_StatusB = (TextView) itemView.findViewById(R.id.tv_outstatus);
            profile = (RoundedImageView) itemView.findViewById(R.id.iv_cric_prifile_pic);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkbox_check_player);
            checkBox.setOnClickListener(this);
            Score_player.setOnClickListener(this);


        }

        @Override
        public void onClick(View iteView) {
            if (mListener != null) {
                mListener.onItemClick(iteView,getPosition());
            }


        }
    }


}
