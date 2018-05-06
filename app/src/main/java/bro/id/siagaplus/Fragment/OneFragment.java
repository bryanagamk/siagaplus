package bro.id.siagaplus.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;

import bro.id.siagaplus.Activity.CheckListActivity;
import bro.id.siagaplus.Adapter.AgendaAdapter;
import bro.id.siagaplus.Adapter.ChecklistAdapter;
import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;


public class OneFragment extends Fragment {

    ArrayList<Agenda> mlistAgenda;
    ArrayList<Checklist> mListChecklist;
    RecyclerView rvAgenda,rvChecklist;

    AgendaAdapter agendaAdapter;
    ChecklistAdapter checklistAdapter;

    Context mContext;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        mContext = this.getActivity().getApplicationContext();
        final Bundle myBundle = new Bundle();
        mlistAgenda = new ArrayList<>();
        mListChecklist = new ArrayList<>();

        rvAgenda = rootView.findViewById(R.id.rv_agenda_fragmentone);
        rvChecklist = rootView.findViewById(R.id.rv_checklist_fragmentone);

        LinearLayoutManager llmAgenda = new LinearLayoutManager(mContext);
        llmAgenda.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmChecklist = new LinearLayoutManager(mContext);
        llmChecklist.setOrientation(LinearLayoutManager.VERTICAL);

        rvAgenda.setLayoutManager(llmAgenda);
        rvChecklist.setLayoutManager(llmChecklist);

        agendaAdapter = new AgendaAdapter(mContext,mlistAgenda);
        checklistAdapter = new ChecklistAdapter(mContext,mListChecklist);
        rvAgenda.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        rvChecklist.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        rvAgenda.setAdapter(agendaAdapter);
        rvChecklist.setAdapter(checklistAdapter);


        getAgenda();
        getChecklist();



        rootView.findViewById(R.id.link_checklist_fragmentone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CheckListActivity.class);
                myBundle.putInt("id",0);
                intent.putExtras(myBundle);
                startActivity(intent);
            }
        });

        return rootView;
    }

        private void getAgenda(){

            Agenda agenda = new Agenda("6/08/2018","11.00","bimbingan pak ali");
            mlistAgenda.add(agenda);

            agenda = new Agenda("6/08/2018","13.00","bimbingan pak udin");
            mlistAgenda.add(agenda);

            agenda = new Agenda("7/08/2018","11.00","proposal PA");
            mlistAgenda.add(agenda);

            agendaAdapter.notifyDataSetChanged();
        }

        private void getChecklist(){
            Checklist checklist = new Checklist("jurnal PA",false);
            mListChecklist.add(checklist);

            checklist = new Checklist("Tugas",false);
            mListChecklist.add(checklist);

            checklistAdapter.notifyDataSetChanged();
        }
}
