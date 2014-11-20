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

public class EventDetailsFragment extends Fragment {

    public EventDetailsFragment(){
    }

    public static final EventDetailsFragment newInstance(EventSummary es){
        EventDetailsFragment el = new EventDetailsFragment();
        Bundle args = new Bundle();

        args.putParcelable("eventSummary",es);

        el.setArguments(args);
        return el;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventSummary es=getArguments().getParcelable("eventSummary");

        int layout=R.layout.template_event_details;

        View rootView = inflater.inflate(layout, container, false);
        ((ImageView) rootView.findViewById(R.id.header_image)).setImageResource(es.image_id);
        ViewGroup content = (LinearLayout) rootView.findViewById(R.id.content);
        View descriptionLayout = inflater.inflate(es.description_layout, content, false);

        content.addView(descriptionLayout);

        ((TextView)rootView.findViewById(R.id.venue)).setText(es.venue);
        ((TextView)rootView.findViewById(R.id.time)).setText(es.time);
        ((TextView)rootView.findViewById(R.id.date)).setText(es.date);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt("section_number"));
    }
}