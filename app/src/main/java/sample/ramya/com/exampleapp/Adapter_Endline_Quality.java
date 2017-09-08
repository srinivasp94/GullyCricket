package sample.ramya.com.exampleapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import sample.ramya.com.exampleapp.customviews.ValueSelector;
import sample.ramya.com.exampleapp.pojo.ReclylerItemModel;


/**
 * Created by elancer on 6/13/2017.
 */

public class Adapter_Endline_Quality extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    Context context;
    View itemView;

    ArrayList<ReclylerItemModel> mData;


    public Adapter_Endline_Quality(Context context, ArrayList<ReclylerItemModel> mData) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            itemView = LayoutInflater.from(context).inflate(R.layout.header_recycleview, parent, false);
            VHheader holder = new VHheader(itemView);
            return holder;
        } else {
            itemView = LayoutInflater.from(context).inflate(R.layout.row_quality_check, parent, false);
            VHitem holder = new VHitem(itemView);
            return holder;
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public int getItemViewType(int position) {
        if ("Header".equalsIgnoreCase(mData.get(position).getType())) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_HEADER) {
            VHheader vHheader = (VHheader) holder;
            vHheader.txtTitle.setText(mData.get(position).getDefect_label());
        } else {
            VHitem vHitem = (VHitem) holder;
            vHitem.txtLableName.setText(mData.get(position).getDefect_label());
        }
    }


    class VHheader extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public VHheader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.header);
        }
    }

    class VHitem extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtLableName;
        final ValueSelector valueSelector;

        public VHitem(View itemView) {
            super(itemView);
            this.valueSelector = (ValueSelector)itemView.findViewById(R.id.vh_valueselector);
            this.txtLableName = (TextView) itemView.findViewById(R.id.tv_lable_name);
            valueSelector.setMinValue(0);
            valueSelector.setMaxValue(100);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
