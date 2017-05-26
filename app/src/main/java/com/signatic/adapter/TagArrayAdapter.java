package com.signatic.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.signatic.model.TagClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NhutDu on 07/11/2016.
 */
public class TagArrayAdapter extends ArrayAdapter<TagClass> {
    private final Context mContext;
    private final List<TagClass> mTagClasss;
    private final List<TagClass> mTagClasss_All;
    private final List<TagClass> mTagClasss_Suggestion;
    private final int mLayoutResourceId;

    public TagArrayAdapter(Context context, int resource, List<TagClass> TagClasss) {
        super(context, resource, TagClasss);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mTagClasss = new ArrayList<>(TagClasss);
        this.mTagClasss_All = new ArrayList<>(TagClasss);
        this.mTagClasss_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mTagClasss.size();
    }

    public TagClass getItem(int position) {
        return mTagClasss.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            TagClass TagClass = getItem(position);
            TextView name = (TextView) convertView.findViewById(android.R.id.text1);
            name.setText(TagClass.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            public String convertResultToString(Object resultValue) {
//                return ((TagClass) resultValue).getName();
//            }
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                if (constraint != null) {
//                    mTagClasss_Suggestion.clear();
//                    for (TagClass TagClass : mTagClasss_All) {
//                        if (TagClass.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
//                            mTagClasss_Suggestion.add(TagClass);
//                        }
//                    }
//                    FilterResults filterResults = new FilterResults();
//                    filterResults.values = mTagClasss_Suggestion;
//                    filterResults.count = mTagClasss_Suggestion.size();
//                    return filterResults;
//                } else {
//                    return new FilterResults();
//                }
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                mTagClasss.clear();
//                if (results != null && results.count > 0) {
//                    // avoids unchecked cast warning when using mTagClasss.addAll((ArrayList<TagClass>) results.values);
//                    List<?> result = (List<?>) results.values;
//                    for (Object object : result) {
//                        if (object instanceof TagClass) {
//                            mTagClasss.add((TagClass) object);
//                        }
//                    }
//                } else if (constraint == null) {
//                    // no filter, add entire original list back in
//                    mTagClasss.addAll(mTagClasss_All);
//                }
//                notifyDataSetChanged();
//            }
//        };

        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((TagClass)(resultValue)).getName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                mTagClasss_Suggestion.clear();
                for (TagClass customer : mTagClasss_All) {
                    if(customer.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        mTagClasss_Suggestion.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mTagClasss_Suggestion;
                filterResults.count = mTagClasss_Suggestion.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<TagClass> filteredList = (ArrayList<TagClass>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (TagClass c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };




//    private class CustomFilter extends Filter {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            mTagClasss_Suggestion.clear();
//
//            if (mTagClasss_All != null && constraint != null) { // Check if the Original List and Constraint aren't null.
//                for (int i = 0; i < mTagClasss_All.size(); i++) {
//                    if (mTagClasss_All.get(i).getName().toLowerCase().contains(constraint)) { // Compare item in original list if it contains constraints.
//                        mTagClasss_Suggestion.add(mTagClasss_All.get(i)); // If TRUE add item in Suggestions.
//                    }
//                }
//            }
//            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
//            results.values = mTagClasss_Suggestion;
//            results.count = mTagClasss_Suggestion.size();
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            if (results.count > 0) {
//                notifyDataSetChanged();
//            } else {
//                notifyDataSetInvalidated();
//            }
//        }
//    }
}
