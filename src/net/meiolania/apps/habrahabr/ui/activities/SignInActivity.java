package net.meiolania.apps.habrahabr.ui.activities;

import net.meiolania.apps.habrahabr.R;
import net.meiolania.apps.habrahabr.ui.actions.HomeAction;
import net.meiolania.apps.habrahabr.utils.UIUtils;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.markupartist.android.widget.ActionBar;

public class SignInActivity extends ApplicationFragmentActivity{
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        
        setActionBar();
    }
    
    private void setActionBar(){
        if(!UIUtils.isHoneycombOrHigher()){
            ActionBar actionBar = (ActionBar)findViewById(R.id.actionbar);
            actionBar.setTitle(R.string.sign_in);
            actionBar.setHomeAction(new HomeAction(this));
        }else{
            ActionBar actionBarView = (ActionBar) findViewById(R.id.actionbar);
            actionBarView.setVisibility(View.GONE);
            
            android.app.ActionBar actionBar = getActionBar();
            actionBar.setTitle(R.string.sign_in);
            
            if(UIUtils.isIceCreamOrHigher())
                actionBar.setHomeButtonEnabled(true);
        }
    }
    
    private class DoSignIn extends AsyncTask<String, Void, Void>{
        private ProgressDialog progressDialog;
        private String login;
        private String password;
        
        @Override
        protected Void doInBackground(String... params){
            login = params[0];
            password = params[1];
            return null;
        }
        
        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(SignInActivity.this);
            progressDialog.setMessage(getString(R.string.preferences_sign_in_1));
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
        
        @Override
        protected void onPostExecute(Void result){
            progressDialog.dismiss();
        }
        
    }
    
}