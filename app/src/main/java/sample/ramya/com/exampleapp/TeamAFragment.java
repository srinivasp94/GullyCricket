package sample.ramya.com.exampleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sample.ramya.com.exampleapp.database.Dbhandler;
import sample.ramya.com.exampleapp.database.ModelPlayer;

/**
 * Created by elancer on 7/3/2017.
 */

public class TeamAFragment extends Fragment {

    private static final int RESULT_OK = 1;
    private List<ModelPlayer> listPlayer = new ArrayList<>();
    private RecyclerView recyclerView;
    TextView norecords;
    private ModelPlayer modelPlayer;
    RecyclerView.LayoutManager layoutManager;
    private Adapter_teams adapterTeams;
    Dbhandler dbhandler;
    String strtext;
    FloatingActionButton fab;
    Bundle bundle1;

    static final int REQUEST_CODE = 2;

    public TeamAFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhandler = new Dbhandler(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.team_a_layout_fragment, null, false);

        norecords = (TextView) view.findViewById(R.id.norecordsfound);

        Bundle b = this.getArguments();
        final String myValue = b.getString("TAB1");




        fab = (FloatingActionButton) view.findViewById(R.id.fab_team_a);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayerDataActivity.class);
                intent.putExtra("TEAM_NAME", myValue);
                startActivity(intent);
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_team_a);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        listPlayer = dbhandler.getAllPlayers();

        int mListSize = listPlayer.size();
        if (mListSize == 0) {
            norecords.setVisibility(View.VISIBLE);


        } else {
            Log.d("###Size", String.valueOf(mListSize));

            for (int i = 0; i < mListSize; i++) {

                modelPlayer = new ModelPlayer();
                modelPlayer.setPlayer_name(listPlayer.get(i).getPlayer_name());
                modelPlayer.setPlayer_score(listPlayer.get(i).getPlayer_score());
                modelPlayer.setPlayer_team(listPlayer.get(i).getPlayer_team());
//                modelPlayer.setImagebytes(listPlayer.get(i).getImagebytes());

                Log.d("%%%List DATA", listPlayer.get(i).toString());

                // listPlayer.add(modelPlayer);
            }

            setList();
        }

        return view;
    }

    public void setList() {
        adapterTeams = new Adapter_teams(getActivity(), listPlayer);
        recyclerView.setAdapter(adapterTeams);


        adapterTeams.setOnItemClickListener(new Adapter_teams.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int pos = view.getId();
                switch (view.getId()) {

                    case R.id.checkbox_check_player:

                        ArrayList<ModelPlayer> players = new ArrayList<ModelPlayer>();
                        int count = 0;
                        for (int i = 0; i < listPlayer.size(); i++) {
                            if (listPlayer.get(i).getIsChecked() == 1) {
                                count++;
                                listPlayer.get(i).setImagebytes("");
                                players.add(listPlayer.get(i));
                            }
                        }

                        if (count == 2) {
                            if (listPlayer.get(position).getIsOut() == 0) {
                                Toast.makeText(getActivity(), "items clicked  " + position, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getActivity(), EnterScoresHere.class);
                                Bundle bundle = new Bundle();

                                /*                                bundle.putSerializable("SERIALS",(Serializable)players);
                                intent.putExtra("BUNDLEDATA",bundle);
                                intent.putExtra("PLAYERS",(Serializable) players);*/

                                bundle.putSerializable("data", players);
                                intent.putExtras(bundle);
                                intent.putExtra("team","1");
//                                intent.putExtra("NAME", listPlayer.get(position).getPlayer_name());
//                                intent.putExtra("TEAM", listPlayer.get(position).getPlayer_team());
//                                Log.v("@@@ POsition and Name", +modelPlayer.getId() + " " + modelPlayer.getPlayer_name());
                                startActivity(intent);
                            } else {
                                showOutAlert();
                            }
                        } else if (count > 2) {
                            showAlert();
                        } else {

                        }
                        break;

                    default:
                        Toast.makeText(getActivity(), "Default" + position, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void showOutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("PLAYER OUT");
        builder.setIcon(R.drawable.billybowden);
        builder.setMessage("Already this Player was out  ");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("ALERT");
        builder.setMessage(" Please Check Only '2' Players ");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            listPlayer = dbhandler.getAllPlayers();
            setList();

           // getFragmentManager().beginTransaction().detach(this).attach(new TeamAFragment()).commit();
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
       /* listPlayer = dbhandler.getAllPlayers();
        int mListSize = listPlayer.size();
        if (mListSize == 0) {
            norecords.setVisibility(View.VISIBLE);

        }*/
        listPlayer = dbhandler.getAllPlayers();
        if (listPlayer.size() > 1) {

            norecords.setVisibility(View.GONE);

        }

        setList();

    }
}
