package bro.id.siagaplus.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bro.id.siagaplus.Activity.AgendaActivity;
import bro.id.siagaplus.Activity.ArtikelActivity;
import bro.id.siagaplus.Activity.CheckListActivity;
import bro.id.siagaplus.Adapter.AgendaAdapter;
import bro.id.siagaplus.Adapter.ArtikelAdapter;
import bro.id.siagaplus.Adapter.ChecklistAdapter;
import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Artikel;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;


public class OneFragment extends Fragment {

    ArrayList<Agenda> mlistAgenda, rawdataAgenda;
    ArrayList<Checklist> mListChecklist, rawdataCheclist;
    ArrayList<Artikel> mListArtikel;
    RecyclerView rvAgenda,rvChecklist,rvArtikel;
    DatabaseHelper db;

    AgendaAdapter agendaAdapter;
    ChecklistAdapter checklistAdapter;
    ArtikelAdapter artikelAdapter;

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
        rawdataAgenda = new ArrayList<>();
        mListChecklist = new ArrayList<>();
        rawdataCheclist = new ArrayList<>();
        mListArtikel = new ArrayList<>();
        db = new DatabaseHelper(mContext);

        rvAgenda = rootView.findViewById(R.id.rv_agenda_fragmentone);
        rvChecklist = rootView.findViewById(R.id.rv_checklist_fragmentone);
        rvArtikel = rootView.findViewById(R.id.rv_artikel_fragmentone);

        LinearLayoutManager llmAgenda = new LinearLayoutManager(mContext);
        llmAgenda.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmChecklist = new LinearLayoutManager(mContext);
        llmChecklist.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmArtikel = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvAgenda.setLayoutManager(llmAgenda);
        rvChecklist.setLayoutManager(llmChecklist);
        rvArtikel.setLayoutManager(llmArtikel);

        agendaAdapter = new AgendaAdapter(mContext,mlistAgenda);
        checklistAdapter = new ChecklistAdapter(mContext,mListChecklist);
        artikelAdapter = new ArtikelAdapter(mContext, mListArtikel);

        rvAgenda.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        rvChecklist.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        rvAgenda.setAdapter(agendaAdapter);
        rvChecklist.setAdapter(checklistAdapter);
        rvArtikel.setAdapter(artikelAdapter);

        getAgenda();
        getChecklist();
        getArtikel();

        rootView.findViewById(R.id.link_agenda_fragmentone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AgendaActivity.class);
                myBundle.putInt("id",0);
                intent.putExtras(myBundle);
                startActivity(intent);
            }
        });

        rootView.findViewById(R.id.link_checklist_fragmentone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CheckListActivity.class);
                myBundle.putInt("id",0);
                intent.putExtras(myBundle);
                startActivity(intent);
            }
        });

        rootView.findViewById(R.id.link_artikel_fragmentone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ArtikelActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getAgenda(){
        rawdataAgenda = db.getAllAgenda();
     if (rawdataAgenda.isEmpty()){
         Agenda agenda = new Agenda();
         agenda.setDate("-");
         agenda.setTitle("no upcoming agenda");
         agenda.setId(1);
         mlistAgenda.add(agenda);
     } else if (rawdataAgenda.size() > 3){
         for (int i = 0;i < 3;i++){
             mlistAgenda.add(rawdataAgenda.get(i));
         }
     } else {
         for (int i = 0;i < rawdataAgenda.size();i++){
             mlistAgenda.add(rawdataAgenda.get(i));
         }
     }

        agendaAdapter.notifyDataSetChanged();
    }
        private void getChecklist(){

            rawdataCheclist = db.getAllChecklist(0);
            for (int i = 0;i < 3;i++){
                mListChecklist.add(rawdataCheclist.get(i));
            }

            checklistAdapter.notifyDataSetChanged();
        }

        private void getArtikel(){
            Artikel artikel = new Artikel("Pentingnya Menjaga Kesehatan dan Mulut Calon Ibu dan Ayah","Sebelum Hamil", "catin_html_a5152cd4.jpg", "catin.html");
            mListArtikel.add(artikel);

            artikel = new Artikel("Yang Harus Diketahui Tentang Karies","Sebelum Hamil", "karies.jpg", "karies.html");
            mListArtikel.add(artikel);

            artikel = new Artikel("Radang Gusi (Gingivitis)","Sebelum Hamil", "radang.jpg", "radang.html");
            mListArtikel.add(artikel);

            artikelAdapter.notifyDataSetChanged();
        }
}
