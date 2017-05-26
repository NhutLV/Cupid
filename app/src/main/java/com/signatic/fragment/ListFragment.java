package com.signatic.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.signatic.adapter.ListAdapter;
import com.signatic.cupid.R;
import com.signatic.model.Category;

import java.util.ArrayList;

/**
 * Created by DefaultAccount on 9/5/2016.
 */
public class ListFragment extends Fragment{
    private ArrayList<Category> mCategories;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cardview_fragment_layout,container,false);
        RecyclerView rc1= (RecyclerView) view.findViewById(R.id.recycler_view);
        mCategories=new ArrayList<>();
        createArrayCategories();
        ListAdapter mList_adapter=new ListAdapter(mCategories,container.getContext());
        RecyclerView.LayoutManager mlayoutManager=new GridLayoutManager(container.getContext(),2);
        rc1.setLayoutManager(mlayoutManager);
        rc1.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        rc1.setItemAnimator(new DefaultItemAnimator());
        rc1.setAdapter(mList_adapter);
        return view;
    }
    public void createArrayCategories(){
        mCategories.add(new Category(R.drawable.image,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
        mCategories.add(new Category(R.drawable.image_category,"Loi Nguyen",R.drawable.gender,"Programmer","Android & IOS", 2000,"Lê Thị Xuyến"));
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
                int topchan=0;
                int tople=0;
            if (includeEdge) {

                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position %2==0) { // top edge
                    outRect.top=spacing-20;
                    outRect.bottom = spacing+20;
                }
                if(position%2==1){
                    outRect.top = spacing+5;
                    outRect.bottom = spacing-5;
                }
                // item bottom
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
