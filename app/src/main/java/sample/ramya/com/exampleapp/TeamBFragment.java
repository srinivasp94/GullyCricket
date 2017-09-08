package sample.ramya.com.exampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sample.ramya.com.exampleapp.database.Dbhandler;
import sample.ramya.com.exampleapp.database.ModelPlayer;
import sample.ramya.com.exampleapp.database.TeamBModel;

import static android.app.Activity.RESULT_OK;

/**
 * Created by elancer on 7/3/2017.
 */

public class TeamBFragment extends Fragment {

    static final int REQUEST_CODE = 2;
    TextView norecords;
    FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;
    Dbhandler dbhandler;
    TeamBModel teamBModel;
    AdapterForTeamB adapterForTeamB;
    private RecyclerView recyclerViewTeamB;
    private List<TeamBModel> list = new ArrayList<>();
    int mListSize;

    public TeamBFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhandler=new Dbhandler(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_b, null, false);
        norecords = (TextView) view.findViewById(R.id.norecordsfound);
        Bundle b = this.getArguments();
        final String myValueb = b.getString("TAB2");

        recyclerViewTeamB = (RecyclerView) view.findViewById(R.id.rv_team_b);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewTeamB.setLayoutManager(layoutManager);
        list = dbhandler.getAllTeamBPlayers();

         mListSize = list.size();
        if (mListSize == 0) {

            norecords.setVisibility(View.VISIBLE);

        } else {
            for (int i = 0; i < mListSize; i++) {

                teamBModel = new TeamBModel();
                teamBModel.setPlayer_nameB(list.get(i).getPlayer_nameB());
                teamBModel.setPlayer_scoreB(list.get(i).getPlayer_scoreB());
                teamBModel.setPlayer_teamB(list.get(i).getPlayer_teamB());

                Log.d("%%%List DATA", list.get(i).toString());

//                 listPlayer.add(modelPlayer);
            }

            setList();
        }


        fab = (FloatingActionButton) view.findViewById(R.id.fab_team_b);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayerDataActivity.class);
                intent.putExtra("TEAM_NAME", myValueb);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return view;
    }

    public void setList() {
        adapterForTeamB = new AdapterForTeamB(getActivity(), list);
        recyclerViewTeamB.setAdapter(adapterForTeamB);

        adapterForTeamB.setOnItemClickListener(new AdapterForTeamB.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.checkbox_check_player:

                        ArrayList<TeamBModel> bModels=new ArrayList<TeamBModel>();
                        int count=0;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getIsChecked() == 1) {
                                count++;
                                list.get(i).setImagebytes("");
                                bModels.add(list.get(i));
                            }
                        }
                        if (count==2){
                            if (teamBModel.getIsOut()==0){

                                Intent intent = new Intent(getActivity(), EnterScore_B_Here.class);
                                Bundle bundle = new Bundle();

                                bundle.putSerializable("data", bModels);
                                intent.putExtras(bundle);
                                intent.putExtra("team","2");

                                startActivityForResult(intent, RESULT_OK);



                                /*Toast.makeText(getActivity(), "items clicked  " + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), EnterScoresHere.class);
                                intent.putExtra("POSITION",list.get(position).getIdB());
                                intent.putExtra("NAME",list.get(position).getPlayer_nameB());
                                intent.putExtra("TEAM",list.get(position).getPlayer_teamB());
                                Log.v("@@@ POsition and Name", + teamBModel.getIdB() + " " + teamBModel.getPlayer_nameB());
                                startActivityForResult(intent, 1);*/
                            }
                        }

                        break;
                    default:
                        Toast.makeText(getActivity(), "Default" + position, Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            list = dbhandler.getAllTeamBPlayers();
            setList();

            // getFragmentManager().beginTransaction().detach(this).attach(new TeamAFragment()).commit();
        }
    }*/
    @Override
    public void onResume() {
        super.onResume();
        /*list = dbhandler.getAllTeamBPlayers();
        int mListSize = list.size();
        if (mListSize == 0) {
            norecords.setVisibility(View.VISIBLE);
        }*/
        list = dbhandler.getAllTeamBPlayers();
        if (list.size()>1)
        {
            norecords.setVisibility(View.GONE);

        }
        setList();

    }

}
