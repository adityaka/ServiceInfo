package com.pluralsight.serviceinfoviewer;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/***
 * A placeholder fragment containing simple view
 */
public class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayAdapter<RunningServicesWrapper> mServices;
    private ListView mServiceList;

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mServices = new ArrayAdapter<RunningServicesWrapper>(getActivity(), android.R.layout.simple_list_item_1);
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);
        mServiceList = (ListView) rootview.findViewById(R.id.service_list);
        mServiceList.setAdapter(mServices);
        mServiceList.setOnItemClickListener(this);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        Context ctx = getActivity().getApplicationContext();
        ActivityManager mgr = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        java.util.List<ActivityManager.RunningServiceInfo> srvInfo = mgr.getRunningServices(Integer.MAX_VALUE);
        mServices.clear();

        for (ActivityManager.RunningServiceInfo cursrv : srvInfo) {
            RunningServicesWrapper wrap = new RunningServicesWrapper(cursrv);
            mServices.add(wrap);
        }
        mServices.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RunningServicesWrapper curItem = mServices.getItem(position);
        ServiceDetailFragment frag = ServiceDetailFragment.newInstance(curItem.getInfo());
        android.app.FragmentManager fm = getActivity().getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, frag);
        ft.addToBackStack(frag.getClass().getSimpleName());
        ft.commit();
    }

    private class RunningServicesWrapper {
        private ActivityManager.RunningServiceInfo minfo;

        public RunningServicesWrapper(ActivityManager.RunningServiceInfo info) {
            minfo = info;
        }

        public ActivityManager.RunningServiceInfo getInfo() {
            return minfo;
        }

        public String toString() {
            return minfo.service.flattenToShortString();
        }
    }

    private class List<T> {
    }
}