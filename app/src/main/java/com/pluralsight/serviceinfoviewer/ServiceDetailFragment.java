package com.pluralsight.serviceinfoviewer;


import android.app.ActivityManager;
import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link ServiceDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceDetailFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//
//    public ServiceDetailFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ServiceDetailFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ServiceDetailFragment newInstance(String param1, String param2) {
//        ServiceDetailFragment fragment = new ServiceDetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
        private static final String ARG_SRV_INFO =  "srv_info";

        private ActivityManager.RunningServiceInfo mSrvInfo;

        public static ServiceDetailFragment newInstance(ActivityManager.RunningServiceInfo info){
            ServiceDetailFragment fragment = new ServiceDetailFragment();
            Bundle args = new Bundle();
            args.putParcelable(ARG_SRV_INFO, info);
            return fragment();

        }

    private static ServiceDetailFragment fragment() {
        return null;
    }

    public ServiceDetailFragment() {
            // Required empty public constructor
        }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSrvInfo = getArguments().getParcelable(ARG_SRV_INFO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.frag_srv_detail, container, false);

        View cnRow;
        TextView title;
        TextView data;

        // Component Name

        cnRow = rootview.findViewById(R.id.component_name);
        title = (TextView)cnRow.findViewById(R.id.title);
        data = (TextView)cnRow.findViewById(R.id.data);
        title.setText(R.string.component_name_title);
        data.setText(mSrvInfo.service.flattenToShortString());

        // Process Name

        cnRow = rootview.findViewById(R.id.process_name);
        title = (TextView)cnRow.findViewById(R.id.title);
        data = (TextView)cnRow.findViewById(R.id.data);
        title.setText(R.string.process_name_title);
        data.setText(mSrvInfo.process);

        // PID

        cnRow = rootview.findViewById(R.id.pid);
        title = (TextView)cnRow.findViewById(R.id.title);
        data = (TextView)cnRow.findViewById(R.id.data);
        title.setText(R.string.pid_title);
        data.setText(Integer.toString(mSrvInfo.pid));

        // UID

        cnRow = rootview.findViewById(R.id.uid);
        title = (TextView)cnRow.findViewById(R.id.title);
        data = (TextView)cnRow.findViewById(R.id.data);
        title.setText(R.string.uid_title);
        data.setText(Integer.toString(mSrvInfo.uid));

        // Connected Clients

        cnRow = rootview.findViewById(R.id.connected_clients);
        title = (TextView)cnRow.findViewById(R.id.title);
        data = (TextView)cnRow.findViewById(R.id.data);
        title.setText(R.string.conn_client_title);
        data.setText(Integer.toString(mSrvInfo.clientCount));


        // Active Time

        cnRow = rootview.findViewById(R.id.active_time);
        title = (TextView)cnRow.findViewById(R.id.title);
        data = (TextView)cnRow.findViewById(R.id.data);
        title.setText(R.string.active_time_title);
        data.setText(Long.toString(SystemClock.elapsedRealtime(), (int) mSrvInfo.lastActivityTime));

        return rootview;
    }

}
