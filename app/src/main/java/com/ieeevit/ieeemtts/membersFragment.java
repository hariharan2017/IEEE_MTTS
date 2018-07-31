package com.ieeevit.ieeemtts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class membersFragment extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.members, null);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        recyclerView = view.findViewById(R.id.recycler_members);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Members");

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<members, membersFragment.ReViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<members, membersFragment.ReViewHolder>(
                members.class,
                R.layout.design_row_members,
                membersFragment.ReViewHolder.class,
                ref) {

            protected void populateViewHolder(membersFragment.ReViewHolder viewHolder, members model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setPos(model.getPos());
                viewHolder.setContact(model.getContact());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ReViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ReViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/vitieeemtts/"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent, "Choose a browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);
                }
            });
        }


        public void setName(String name) {
            TextView post_title = mView.findViewById(R.id.mem_name);
            post_title.setText(name);
        }

        public void setPos(String pos) {
            TextView post_pos = mView.findViewById(R.id.mem_pos);
            post_pos.setText(pos);
        }

        public void setContact(String contact) {
            TextView post_contact = mView.findViewById(R.id.mem_contact);
            post_contact.setText(contact);
        }
        
        public void setImage(Context ctx, String image) {
            ImageView post_image = mView.findViewById(R.id.mem_image);
            Picasso.get().load(image).into(post_image);
        }

    }
}

