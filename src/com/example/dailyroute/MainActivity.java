package com.example.dailyroute;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void addRoute(View view){
    	sendJson("a@a.com", "a");
    	TextView display = (TextView) findViewById(R.id.dateTextView);
    	String title = "Failure";
    	display.setText(title);  
    }
    
    public void cancelEvent(View view){
//    	super.onBackPressed();
    }
    
    protected void sendJson(final String email, final String password) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare(); //For Preparing Message Pool for the child Thread
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    HttpPost post = new HttpPost("http://tranquil-falls-5399.herokuapp.com/users/submit_new_event");
                    json.put("title", "title");
                    json.put("start_time", 1234);
                    json.put("end_time", 5678);
                    StringEntity se = new StringEntity( json.toString());  
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    post.setEntity(se);
                    response = client.execute(post);
                } catch(Exception e) {
              //
                }
                Looper.loop(); //Loop in the message queue
            }
        };

        t.start();      
    }
     

    
//    public class Event {
//    	private int id;
//    	private String title;
//    	private String location;
//    	private int startTime;
//    	private int endTime;
////        private Map<String , Object> otherProperties = new HashMap<String , Object>();
//     
//        public int getId() {
//            return id;
//        }
//        public void setId(int id) {
//            this.id = id;
//        }    
//        public String getTitle() {
//            return title;
//        }
//        public void setTitle(String title) {
//            this.title = title;
//        }
//        public String getLocation() {
//            return location;
//        }
//        public void setLocation(String location) {
//            this.location = location;
//        }
//        public int getStartTime() {
//            return startTime;
//        }
//        public void setStartTime(int startTime) {
//            this.startTime = startTime;
//        }  
//        public int getEndTime() {
//            return endTime;
//        }
//        public void setEndTime(int endTime) {
//            this.id = endTime;
//        }  
//        
//    }
    
//    public class asyncCaller extends AsyncTask<Void, Void, Event> {
//        @Override
//        protected void onPreExecute() {
//        }
//    	
//        @Override
//        protected Event doInBackground(Void... params) {
//        	String jason = "{\"id\":\"1\",\"title\":\"testTitle\",\"location\":\"TheMoon\",\"startTime\":\"2\",\"endTime\":\"3\"}" ;
//            Gson gson = new Gson();
//            Event event = gson.fromJson(jason, Event.class); 
//            return event;
//        }
//
//        @Override
//        protected void onPostExecute(Event result) {
//        	TextView display = (TextView) findViewById(R.id.dateTextView);
//        	String title = result.getTitle();
//            display.setText(title);
//        }
//    }
    
}
