package com.jakewharton.hanselandgretel.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentBreadCrumbs;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SampleNestedFragmentsDefaultDark extends FragmentActivity {
	
	Fragment mRootFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_default_nested_fragments);

        if (savedInstanceState == null) {
            // Add root fragment
            mRootFragment = new RootFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.disallowAddToBackStack();
            ft.replace(R.id.root_fragment, mRootFragment);
            ft.commit();
        } else {
        	mRootFragment = getSupportFragmentManager().findFragmentById(R.id.root_fragment);
        }
    }
    
    @Override
    public void onBackPressed() {
    	// support library does not seem to handle backstack properly for nested fragment
    	if (mRootFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
    		mRootFragment.getChildFragmentManager().popBackStack();
    	} else {
    		super.onBackPressed();
    	}
    }

    public static class RootFragment extends Fragment {
        int mStackLevel = 0;
    	
    	public RootFragment() {
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
    		return inflater.inflate(R.layout.sample_default, container, false);
    	}
    	
    	@Override
    	public void onViewCreated(View view, Bundle savedInstanceState) {
    		super.onViewCreated(view, savedInstanceState);
    		
    		if (savedInstanceState == null) {
    			// Do first time initialization -- add initial fragment.
                Fragment newFragment = CountingFragment.newInstance(mStackLevel);
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.add(R.id.simple_fragment, newFragment);
                ft.commit();
    		} else {
                mStackLevel = savedInstanceState.getInt("level");
            }

    		//Associate bread crumbs
            FragmentBreadCrumbs crumbs = (FragmentBreadCrumbs)view.findViewById(R.id.breadcrumbs);
            crumbs.setFragmentManager(getChildFragmentManager());
            crumbs.setTitle("Base", null);
            
            // Watch for button clicks.
            Button button = (Button)view.findViewById(R.id.new_fragment);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    addFragmentToStack();
                }
            });
    	}
    	
    	@Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("level", mStackLevel);
        }
    	
    	void addFragmentToStack() {
            mStackLevel++;

            // Instantiate a new fragment.
            Fragment newFragment = CountingFragment.newInstance(mStackLevel);

            // Add the fragment to the root fragment, pushing this transaction
            // on to the back stack.
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.setBreadCrumbTitle("Frag"+mStackLevel);
            ft.replace(R.id.simple_fragment, newFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public static class CountingFragment extends Fragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static CountingFragment newInstance(int num) {
            CountingFragment f = new CountingFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.hello_world, container, false);
            View tv = v.findViewById(android.R.id.text1);
            ((TextView)tv).setText(mNum > 0 ? "Fragment #" + mNum : "Base");
            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            return v;
        }
    }
}
