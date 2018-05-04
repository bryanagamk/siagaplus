package bro.id.siagaplus.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bro.id.siagaplus.Adapter.AgendaAdapter;
import bro.id.siagaplus.Adapter.ChecklistAdapter;
import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;

public class FourFragment extends Fragment {

    ArrayList<Agenda> mlistAgenda;
    ArrayList<Checklist> mListChecklist;
    RecyclerView rvAgenda,rvChecklist;

    AgendaAdapter agendaAdapter;
    ChecklistAdapter checklistAdapter;
    Context mContext;

    public FourFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_fout, container, false);

        mContext = this.getActivity().getApplicationContext();

        mlistAgenda = new ArrayList<>();
        mListChecklist = new ArrayList<>();

        rvAgenda = rootView.findViewById(R.id.rv_agenda_fragmentfour);
        rvChecklist = rootView.findViewById(R.id.rv_checklist_fragmentfour);

        LinearLayoutManager llmAgenda = new LinearLayoutManager(mContext);
        llmAgenda.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmChecklist = new LinearLayoutManager(mContext);
        llmChecklist.setOrientation(LinearLayoutManager.VERTICAL);

        rvAgenda.setLayoutManager(llmAgenda);
        rvChecklist.setLayoutManager(llmChecklist);

        agendaAdapter = new AgendaAdapter(mContext,mlistAgenda);
        checklistAdapter = new ChecklistAdapter(mContext,mListChecklist);

        rvAgenda.setAdapter(agendaAdapter);
        rvChecklist.setAdapter(checklistAdapter);


        getAgenda();
        getChecklist();

        return rootView;
    }
    private void getAgenda(){

        Agenda agenda = new Agenda("6/08/2018","11.00","bimbingan pak ali");
        mlistAgenda.add(agenda);



        agendaAdapter.notifyDataSetChanged();
    }

    private void getChecklist(){
        Checklist checklist = new Checklist("fikkril PA",false);
        mListChecklist.add(checklist);



        checklistAdapter.notifyDataSetChanged();
    }
}
