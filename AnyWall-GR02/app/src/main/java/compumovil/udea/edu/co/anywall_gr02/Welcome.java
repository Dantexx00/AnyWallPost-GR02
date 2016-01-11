package compumovil.udea.edu.co.anywall_gr02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


public class Welcome extends FragmentActivity {
    Button logout;
    private static final int MAX_POSY_SEARCH_RESULTS=20;
    private ParseQueryAdapter<AnywallPost> postsQueryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ParseQueryAdapter.QueryFactory<AnywallPost> factory= new ParseQueryAdapter.QueryFactory<AnywallPost>(){
            public ParseQuery<AnywallPost> create(){
                ParseQuery<AnywallPost> query=AnywallPost.getQuery();
                query.include("user");
                query.orderByDescending("createdAt");
                query.setLimit(MAX_POSY_SEARCH_RESULTS);
                return query;
            }
        };
        postsQueryAdapter=new ParseQueryAdapter<AnywallPost>(this,factory){
            @Override
            public View getItemView(AnywallPost post, View view, ViewGroup parent) {
                if(view==null){
                    view=View.inflate(getContext(),R.layout.anywall_post_item,null);
                }
                TextView contentView=(TextView)view.findViewById(R.id.content_view);
                TextView usernameView=(TextView)view.findViewById(R.id.username_view);
                contentView.setText(post.getText());
                usernameView.setText(post.getUser().getUsername());
                return view;
            }
        };
        postsQueryAdapter.setAutoload(false);
        postsQueryAdapter.setPaginationEnabled(false);
        ListView postsListView=(ListView)findViewById(R.id.posts_listView);
        postsListView.setAdapter(postsQueryAdapter);
        Button postButton=(Button)findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome.this,PostActivity.class);
                startActivity(intent);
            }
        });
        logout=(Button)findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent intent=new Intent(Welcome.this,LoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        doListQuery();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    private void doListQuery(){
        postsQueryAdapter.loadObjects();
    }
}
