package compumovil.udea.edu.co.anywall_gr02;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.ParseACL;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PostActivity extends Activity {
    private EditText postEditText;
    private TextView characterCountTextView;
    private Button postButton;
    private int maxCharacterCount=140;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        postEditText=(EditText)findViewById(R.id.post_edit_text);
        postEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updatePostButtonState();
                updateCharacterCountTextViewText();
            }
        });
        characterCountTextView=(TextView)findViewById(R.id.character);
        postButton=(Button)findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
        updatePostButtonState();
        updateCharacterCountTextViewText();
    }
    private void post(){
        String text=postEditText.getText().toString().trim();
        final ProgressDialog dialog=new ProgressDialog(PostActivity.this);
        dialog.setMessage("Posting...");
        dialog.show();
        AnywallPost post=new AnywallPost();
        post.setText(text);
        post.setUser(ParseUser.getCurrentUser());
        ParseACL acl=new ParseACL();
        acl.setPublicReadAccess(true);
        post.setACL(acl);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                finish();
            }
        });
    }

    private String getPostEditTextText(){
        return postEditText.getText().toString().trim();
    }

    private void updatePostButtonState(){
        int length=getPostEditTextText().length();
        boolean enabled=length>0&&length<maxCharacterCount;
        postButton.setEnabled(enabled);
    }

    private void updateCharacterCountTextViewText(){
        String characterCountString = String.format("%d/%d",postEditText.length(),maxCharacterCount);
        characterCountTextView.setText(characterCountString);
    }

}
