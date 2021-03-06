package com.example.tony.gadgeothek.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.example.tony.gadgeothek.GadgeothekActivity;
import com.example.tony.gadgeothek.R;
import com.example.tony.gadgeothek.adapter.LoanListAdapter;
import com.example.tony.gadgeothek.domain.Loan;
import com.example.tony.gadgeothek.services.Callback;
import com.example.tony.gadgeothek.services.LibraryService;

public class LoanListFragment extends Fragment {

    private RecyclerView recyclerView;
    private LoanListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_loan_list, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<Loan> loans = new ArrayList<>();

        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                for (int i = 0; i < input.size(); i++) {
                    loans.add(input.get(i));
                }

                adapter = new LoanListAdapter(loans);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String message) {
                Snackbar.make(root.findViewById(R.id.fragment_loanlist), "Error", Snackbar.LENGTH_LONG)
                        .show();

            }
        });

        return root;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof View.OnClickListener)) {
            throw new AssertionError("Activity must implement View.OnClickListener!");
        }
    }



}
