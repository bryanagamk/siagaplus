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


public class FiveFragment extends Fragment {
    ArrayList<Agenda> mlistAgenda, rawdataAgenda;
    ArrayList<Checklist> mListChecklist, rawdataCheclist;
    ArrayList<Artikel> mListArtikel;
    RecyclerView rvAgenda,rvChecklist,rvArtikel;
    DatabaseHelper db;

    AgendaAdapter agendaAdapter;
    ChecklistAdapter checklistAdapter;
    ArtikelAdapter artikelAdapter;

    Context mContext;
    public FiveFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_five, container, false);

        mContext = this.getActivity().getApplicationContext();
        db = new DatabaseHelper(mContext);
        mlistAgenda = new ArrayList<>();
        mListChecklist = new ArrayList<>();
        mListArtikel = new ArrayList<>();
        rawdataCheclist = new ArrayList<>();
        rawdataAgenda = new ArrayList<>();

        rvAgenda = rootView.findViewById(R.id.rv_agenda_fragmentfive);
        rvChecklist = rootView.findViewById(R.id.rv_checklist_fragmentfive);
        rvArtikel = rootView.findViewById(R.id.rv_artikel_fragmentfive);

        LinearLayoutManager llmAgenda = new LinearLayoutManager(mContext);
        llmAgenda.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmChecklist = new LinearLayoutManager(mContext);
        llmChecklist.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmArtikel = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvAgenda.setLayoutManager(llmAgenda);
        rvChecklist.setLayoutManager(llmChecklist);
        rvArtikel.setLayoutManager(llmArtikel);
        final Bundle myBundle = new Bundle();
        myBundle.putInt("id",4);

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

        rootView.findViewById(R.id.link_checklist_fragmentfive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CheckListActivity.class);
                intent.putExtras(myBundle);
                startActivity(intent);
            }
        });

        rootView.findViewById(R.id.link_artikel_fragmentfive).setOnClickListener(new View.OnClickListener() {
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
        rawdataCheclist = db.getAllChecklist(4);
        for (int i = 0;i < 3;i++){
            mListChecklist.add(rawdataCheclist.get(i));
        }

        checklistAdapter.notifyDataSetChanged();
    }

    private String [] menu={"Baby Blues", "Lidah Bayi Berwarna Putih, Apakah Normal?", "Tumbuh Kembang Gigi", "Menjaga Kebersihan Mulut Bayi", "Waspada Karies pada Anak"};
    private String [] nama={"babyblues.html", "lidahputih.html", "gigi.html", "mulutbayi.html", "waspadakaries.html"};
    private String [] gambar={"babyblues.jpg", "lidahputih.jpg", "gigi.png","mulutbayi.jpg","waspadakaries.jpg"};
    private void getArtikel(){
        Artikel artikel = new Artikel("Baby Blues","Setelah Lahir", "babyblues.jpg", "babyblues.html");
        mListArtikel.add(artikel);

        artikel = new Artikel("Menjaga Kebersihan Mulut Bayi","Setelah Lahir", "mulutbayi.jpg", "mulutbayi.html");
        mListArtikel.add(artikel);

        artikel = new Artikel("Lidah Bayi Berwarna Putih, Apakah Normal?","Setelah Lahir", "lidahputih.jpg", "lidahputih.html");
        mListArtikel.add(artikel);

        artikelAdapter.notifyDataSetChanged();
    }

}
