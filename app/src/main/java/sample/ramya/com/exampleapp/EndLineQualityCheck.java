package sample.ramya.com.exampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sample.ramya.com.exampleapp.commonutills.Common;
import sample.ramya.com.exampleapp.constants.Constants;
import sample.ramya.com.exampleapp.constants.ServiceUrls;
import sample.ramya.com.exampleapp.network.RetrofitRequester;
import sample.ramya.com.exampleapp.network.RetrofitResponseListener;
import sample.ramya.com.exampleapp.pojo.Bottom;
import sample.ramya.com.exampleapp.pojo.BrokenSkipStitch;
import sample.ramya.com.exampleapp.pojo.CommonResponsePojo;
import sample.ramya.com.exampleapp.pojo.Model_Result;
import sample.ramya.com.exampleapp.pojo.OtherConstructionDefect;
import sample.ramya.com.exampleapp.pojo.OtherDefect;
import sample.ramya.com.exampleapp.pojo.ReclylerItemModel;
import sample.ramya.com.exampleapp.pojo.Top;

public class EndLineQualityCheck extends AppCompatActivity implements RetrofitResponseListener {

    RecyclerView endLine;
    ArrayList<ReclylerItemModel> mData;
    String Defect_lable_name;

    List<OtherDefect> mOtherDefects;
    List<OtherConstructionDefect> mOtherConstructionDefects;

    RecyclerView.LayoutManager layoutManager;
    private Adapter_Endline_Quality adapterEndlineQuality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_line_qualitycheck);

        endLine = (RecyclerView) findViewById(R.id.rv_quality_check);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        endLine.setLayoutManager(layoutManager);


        new RetrofitRequester(EndLineQualityCheck.this).sendStringRequest(ServiceUrls.RequestNames.GET_ENDLINEQUALITY_FORM_FIELDS, null, true);


    }


    @Override
    public void onResponseSuccess(CommonResponsePojo commonResponsePojo, HashMap<String, String> requestParams, int requestId) {
        if (!commonResponsePojo.code.equalsIgnoreCase(Constants.SUCCESS)) {

            // Common.customToast(this, commonResponsePojo.message);
            Log.d("Data from server", commonResponsePojo.result.toString());
            return;

        }

        Model_Result dataObject_result = Common.getSpecificDataObject(commonResponsePojo.result, Model_Result.class);

        mData = new ArrayList<>();
        //OtherDefect

        mOtherDefects = dataObject_result.getOtherDefects();
        int otherDefectSize = mOtherDefects.size();
        mData.add(new ReclylerItemModel("Header", "other_defects"));

        for (int i = 0; i < otherDefectSize; i++) {
            mData.add(new ReclylerItemModel("Item", mOtherDefects.get(i).getDefectLabel()));
        }

        //OtherConstructionDefects
        List<OtherConstructionDefect> mOtherConstructionDefects = dataObject_result.getOtherConstructionDefects();
        int OtherConstructionDefectsSize = mOtherConstructionDefects.size();

        mData.add(new ReclylerItemModel("Header", "other_construction_defects"));

        for (int i = 0; i < OtherConstructionDefectsSize; i++) {
            mData.add(new ReclylerItemModel("Item", mOtherConstructionDefects.get(i).getDefectLabel()));
        }

        BrokenSkipStitch mBrokenSkipStitch = dataObject_result.getBrokenSkipStitch();

        // Tops
        List<Top> mTops = mBrokenSkipStitch.getTops();
        int mTopsSize = mTops.size();

        mData.add(new ReclylerItemModel("Header", "broken_skip_stitch -- Tops"));

        for (int i = 0; i < mTopsSize; i++) {
            mData.add(new ReclylerItemModel("Item", mTops.get(i).getDefectLabel()));
        }


        //Bottoms
        List<Bottom> mBottoms = mBrokenSkipStitch.getBottoms();
        int mBottinsSize = mBottoms.size();


        mData.add(new ReclylerItemModel("Header", "broken_skip_stitch -- Bottoms"));

        for (int i = 0; i < mBottinsSize; i++) {
            mData.add(new ReclylerItemModel("Item", mBottoms.get(i).getDefectLabel()));
        }


        adapterEndlineQuality = new Adapter_Endline_Quality(EndLineQualityCheck.this, mData);
        endLine.setAdapter(adapterEndlineQuality);
    }


}




