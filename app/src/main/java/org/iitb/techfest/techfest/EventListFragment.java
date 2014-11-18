package org.iitb.techfest.techfest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EventListFragment extends Fragment {

    public EventListFragment(){
    }

    public static final EventListFragment newInstance(int section_number, int image_id, int description_layout, ArrayList<String> event_list){ // TODO change String arraylist to EventSummary arraylist
        EventListFragment el = new EventListFragment();
        Bundle args = new Bundle();

        args.putInt("section_number", section_number);
        args.putInt("image_id", image_id);
        args.putInt("description_layout", description_layout);
        args.putStringArrayList("event_list", event_list);

        el.setArguments(args);
        return el;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args=getArguments();

        int layout=R.layout.template_event_list;

        View rootView = inflater.inflate(layout, container, false);
        ((ImageView) rootView.findViewById(R.id.header_image)).setImageResource(args.getInt("image_id"));
        ViewGroup content = (LinearLayout) rootView.findViewById(R.id.content);
        View descriptionLayout = inflater.inflate(args.getInt("description_layout"), content, false);

        ArrayList<String> event_list = args.getStringArrayList("event_list");

        content.addView(descriptionLayout);

        for(String s: event_list){
            TextView tv = new TextView(container.getContext());
            tv.setText(s);
            content.addView(tv);
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt("section_number"));
    }
}
