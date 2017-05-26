package com.signatic.cupid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.signatic.adapter.TagArrayAdapter;
import com.signatic.model.TagClass;
import com.signatic.model.User;
import com.signatic.service.services.TagServiceImpl;
import com.signatic.utils.Callback;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;
import com.signatic.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TagsProfileActivity extends AppCompatActivity {

    //region properties

    @BindView(R.id.tag_group_added)
    TagView tagGroupAdded;

    @BindView(R.id.et_tags)
    AutoCompleteTextView editText;

    TextView mDone;

    @BindView(R.id.fullname_tag)
    TextView mFullname;

    @BindView(R.id.toolbar_profile_tag)
    Toolbar mToolbar;

    private TagServiceImpl mTagService;

    TagArrayAdapter mAdapter;

    User user = UserLogin.getUserLogin();

    //endregion

    /**
     * sample country list
     */
    public ArrayList<TagClass> tagClassList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        ButterKnife.bind(this);

        mDone = (TextView) mToolbar.findViewById(R.id.btn_done);

        mFullname.setText(user.getDisplayName());

        //set color for status bar with version >=21
        ToolsActivity.setColorStatus(this, R.color.colorPrimaryDark);


        //add Tags for user to profile view
        Utils.addTagsToView(tagGroupAdded,user.getTags(),true);


        mTagService = new TagServiceImpl(this);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTags(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (tagGroupAdded.getTags().size() < 5) {
                    tagGroupAdded.addTag(addTagTo(tagClassList.get(i).getName(), true));
                    user.getTags().add(tagClassList.get(i));
                } else {
                    Toast.makeText(getApplicationContext(), "You only choose 5 tags", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if(tagClassList.size()==0) {
                        if (tagGroupAdded.getTags().size() < 5) {
                            mTagService.createTags(user.getID(), editText.getText().toString(),user.getApiKey(), new Callback<TagClass>() {
                                @Override
                                public void next(TagClass result) {
                                    user.getTags().add(result);
                                }
                            });
                            tagGroupAdded.addTag(addTagTo(editText.getText().toString(), true));
                        } else {
                            Toast.makeText(getApplicationContext(), "You only choose 5 tags", Toast.LENGTH_SHORT).show();
                        }
                        editText.setText("");
                    }
                }
                return false;
            }
        });


        tagGroupAdded.setOnTagDeleteListener(new TagView.OnTagDeleteListener() {

            @Override
            public void onTagDeleted(final TagView view, final Tag tag, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TagsProfileActivity.this);
                builder.setMessage("\"" + tag.text + "\" will be delete. Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        view.remove(position);
                        user.getTags().remove(position);
                        Toast.makeText(TagsProfileActivity.this, "\"" + tag.text + "\" deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();

            }

        });

        // done choose tags and back profile activity
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTagActivity();
            }
        });
    }

    // set Tags from auto complete to tagGroup
    private void setTags(CharSequence cs) {

        if (cs.toString().equals("")) {
            return;
        }
        final String text = cs.toString();

        mTagService.searchTag(text,user.getApiKey(), new Callback<ArrayList<TagClass>>() {
            @Override
            public void next(ArrayList<TagClass> result) {
                tagClassList = result;
                if (tagClassList != null) {
                    if (tagClassList.size() > 0) {
                        editText.setThreshold(1);
                        mAdapter = new TagArrayAdapter(TagsProfileActivity.this, android.R.layout.simple_dropdown_item_1line, tagClassList);
                        editText.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    editText.setText("");
                }
            }
        });


    }

    // define a tag
    private Tag addTagTo(String s, boolean isDelete) {
        Tag tag;
        tag = new Tag(s);
        tag.layoutColor = Color.parseColor("#17A1CA");
        tag.radius = 10f;
        tag.isDeletable = isDelete;

        return tag;
    }

    // exit activity
    private void cancelTagActivity() {

        startActivity(new Intent(TagsProfileActivity.this, ProfileSettingActivity.class));
        Toast.makeText(getApplicationContext(), "Your tags are saved", Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelTagActivity();
    }
}
