package com.footballcalendar.bryant;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends Activity {

    String[] teams = {"Arizona Cardinals","Atlanta Falcons","Baltimore Ravens","Buffalo Bills","Carolina Panthers",
            "Chicago Bears","Cincinnati Bengals","Cleveland Browns","Dallas Cowboys","Denver Broncos","Detroit Lions",
            "Green Bay Packers","Houston Texans","Indianapolis Colts","Jacksonville Jaguars","Kansas City Chiefs",
            "Miami Dolphins","Minnesota Vikings","New England Patriots","New Orleans Saints","New York Giants",
            "New York Jets","Oakland Raiders","Philadelphia Eagles","Pittsburgh Steelers","Saint Louis Rams",
            "San Diego Chargers","San Francisco 49ers","Seattle Seahawks","Tampa Bay Buccaneers","Tennessee Titans",
            "Washington Redskins"};

    String[] teama = {"ARI ","ATL ","BAL ","BUF ","CAR ","CHI ","CIN ","CLE ","DAL ","DEN ",
            "DET ","GB ","HOU ","IND ","JAC ","KAN ","MIA ","MIN ","NE ","NO ","NYG ","NYJ ","OAK ",
            "PHI ","PIT ","SD ","SEA ","SF ","STL ","TB ","TEN ","WAS"};

    static String[][] events = {{"WEEK 1","BAL ","@DEN ","9","5","8","30","NBC"},{"WEEK 1","NE ","@BUF ","9","8","1","0","CBS"},{"WEEK 1","CIN ","@CHI ","9","8","1","0","CBS"},{"WEEK 1","MIA ","@CLE ","9","8","1","0","CBS"},{"WEEK 1","OAK ","@IND ","9","8","1","0","CBS"},{"WEEK 1","KAN ","@JAC ","9","8","1","0","CBS"},{"WEEK 1","TB ","@NYJ ","9","8","1","0","FOX"},{"WEEK 1","TEN ","@PIT ","9","8","1","0","CBS"},{"WEEK 1","ATL ","@NO ","9","8","1","0","FOX"},{"WEEK 1","SEA ","@CAR ","9","8","1","0","FOX"},{"WEEK 1","MIN ","@DET ","9","8","1","0","FOX"},{"WEEK 1","GB ","@SF ","9","8","4","25","FOX"},{"WEEK 1","ARI ","@STL ","9","8","4","25","FOX"},{"WEEK 1","NYG ","@DAL ","9","8","8","30","NBC"},{"WEEK 1","PHI ","@WAS ","9","9","7","10","ESPN"},{"WEEK 1","HOU ","@SD ","9","9","1","20","ESPN"},{"WEEK 2","NYJ ","@NE ","9","12","8","25","NFL Network"},{"WEEK 2","DAL ","@KAN ","9","15","1","0","FOX"},{"WEEK 2","STL ","@ATL ","9","15","1","0","FOX"},{"WEEK 2","SD ","@PHI ","9","15","1","0","CBS"},{"WEEK 2","TEN ","@HOU ","9","15","1","0","CBS"},{"WEEK 2","MIA ","@IND ","9","15","1","0","CBS"},{"WEEK 2","CAR ","@BUF ","9","15","1","0","FOX"},{"WEEK 2","CLE ","@BAL ","9","15","1","0","CBS"},{"WEEK 2","WAS ","@GB ","9","15","1","0","FOX"},{"WEEK 2","MIN ","@CHI ","9","15","1","0","FOX"},{"WEEK 2","NO ","@TB ","9","15","4","5","FOX"},{"WEEK 2","DET ","@ARI ","9","15","4","5","FOX"},{"WEEK 2","JAC ","@OAK ","9","15","4","25","CBS"},{"WEEK 2","DEN ","@NYG ","9","15","4","25","CBS"},{"WEEK 2","SF ","@SEA ","9","15","8","30","NBC"},{"WEEK 2","PIT ","@CIN ","9","16","8","40","ESPN"},{"WEEK 3","KAN ","@PHI ","9","19","8","25","NFL Network"},{"WEEK 3","TB ","@NE ","9","22","1","0","FOX"},{"WEEK 3","ARI ","@NO ","9","22","1","0","FOX"},{"WEEK 3","SD ","@TEN ","9","22","1","0","CBS"},{"WEEK 3","GB ","@CIN ","9","22","1","0","FOX"},{"WEEK 3","HOU ","@BAL ","9","22","1","0","CBS"},{"WEEK 3","CLE ","@MIN ","9","22","1","0","CBS"},{"WEEK 3","NYG ","@CAR ","9","22","1","0","FOX"},{"WEEK 3","STL ","@DAL ","9","22","1","0","FOX"},{"WEEK 3","DET ","@WAS ","9","22","1","0","FOX"},{"WEEK 3","ATL ","@MIA ","9","22","4","5","FOX"},{"WEEK 3","JAC ","@SEA ","9","22","4","25","CBS"},{"WEEK 3","IND ","@SF ","9","22","4","25","CBS"},{"WEEK 3","BUF ","@NYJ ","9","22","4","25","CBS"},{"WEEK 3","CHI ","@PIT ","9","22","8","30","NBC"},{"WEEK 3","OAK ","@DEN ","9","23","8","40","ESPN"},{"WEEK 4","SF ","@STL ","9","26","8","25","NFL Network"},{"WEEK 4","CHI ","@DET ","9","29","1","0","FOX"},{"WEEK 4","IND ","@JAC ","9","29","1","0","CBS"},{"WEEK 4","SEA ","@HOU ","9","29","1","0","FOX"},{"WEEK 4","CIN ","@CLE ","9","29","1","0","CBS"},{"WEEK 4","BAL ","@BUF ","9","29","1","0","CBS"},{"WEEK 4","PIT ","@MIN ","9","29","1","0","CBS"},{"WEEK 4","ARI ","@TB ","9","29","1","0","FOX"},{"WEEK 4","NYG ","@KAN ","9","29","1","0","FOX"},{"WEEK 4","NYJ ","@TEN ","9","29","4","5","CBS"},{"WEEK 4","DAL ","@SD ","9","29","4","25","FOX"},{"WEEK 4","WAS ","@OAK ","9","29","4","25","FOX"},{"WEEK 4","PHI ","@DEN ","9","29","4","25","FOX"},{"WEEK 4","NE ","@ATL ","9","29","8","30","NBC"},{"WEEK 4","MIA ","@NO ","9","30","8","40","ESPN"},{"WEEK 5","BUF ","@CLE ","10","3","8","25","NFL Network"},{"WEEK 5","NE ","@CIN ","10","6","1","0","CBS"},{"WEEK 5","BAL ","@MIA ","10","6","1","0","CBS"},{"WEEK 5","SEA ","@IND ","10","6","1","0","FOX"},{"WEEK 5","KAN ","@TEN ","10","6","1","0","CBS"},{"WEEK 5","JAC ","@STL ","10","6","1","0","CBS"},{"WEEK 5","NO ","@CHI ","10","6","1","0","FOX"},{"WEEK 5","DET ","@GB ","10","6","1","0","FOX"},{"WEEK 5","PHI ","@NYG ","10","6","1","0","FOX"},{"WEEK 5","CAR ","@ARI ","10","6","4","5","FOX"},{"WEEK 5","SD ","@OAK ","10","6","4","25","CBS"},{"WEEK 5","DEN ","@DAL ","10","6","4","25","CBS"},{"WEEK 5","HOU ","@SF ","10","6","8","30","NBC"},{"WEEK 5","NYJ ","@ATL ","10","7","8","40","ESPN"},{"WEEK 6","NYG ","@CHI ","10","10","8","25","NFL Network"},{"WEEK 6","CAR ","@MIN ","10","13","1","0","FOX"},{"WEEK 6","PHI ","@TB ","10","13","1","0","FOX"},{"WEEK 6","PIT ","@NYJ ","10","13","1","0","CBS"},{"WEEK 6","OAK ","@KAN ","10","13","1","0","CBS"},{"WEEK 6","STL ","@HOU ","10","13","1","0","FOX"},{"WEEK 6","DET ","@CLE ","10","13","1","0","FOX"},{"WEEK 6","GB ","@BAL ","10","13","1","0","FOX"},{"WEEK 6","CIN ","@BUF ","10","13","1","0","CBS"},{"WEEK 6","JAC ","@DEN ","10","13","4","5","CBS"},{"WEEK 6","TEN ","@SEA ","10","13","4","5","CBS"},{"WEEK 6","NO ","@NE ","10","13","4","25","FOX"},{"WEEK 6","ARI ","@SF ","10","13","4","25","FOX"},{"WEEK 6","WAS ","@DAL ","10","13","8","30","NBC"},{"WEEK 6","IND ","@SD ","10","14","8","40","ESPN"},{"WEEK 7","SEA ","@ARI ","10","17","8","25","NFL Network"},{"WEEK 7","TB ","@ATL ","10","20","1","0","FOX"},{"WEEK 7","NE ","@NYJ ","10","20","1","0","CBS"},{"WEEK 7","SD ","@JAC ","10","20","1","0","CBS"},{"WEEK 7","HOU ","@KAN ","10","20","1","0","CBS"},{"WEEK 7","BUF ","@MIA ","10","20","1","0","CBS"},{"WEEK 7","CIN ","@DET ","10","20","1","0","CBS"},{"WEEK 7","DAL ","@PHI ","10","20","1","0","FOX"},{"WEEK 7","STL ","@CAR ","10","20","1","0","FOX"},{"WEEK 7","CHI ","@WAS ","10","20","1","0","FOX"},{"WEEK 7","SF ","@TEN ","10","20","4","5","FOX"},{"WEEK 7","BAL ","@PIT ","10","20","4","25","CBS"},{"WEEK 7","CLE ","@GB ","10","20","4","25","CBS"},{"WEEK 7","DEN ","@IND ","10","20","8","30","NBC"},{"WEEK 7","MIN ","@NYG ","10","21","8","40","ESPN"},{"WEEK 8","CAR ","@TB ","10","24","8","25","NFL Network"},{"WEEK 8","DAL ","@DET ","10","27","1","0","FOX"},{"WEEK 8","NYG ","@PHI ","10","27","1","0","FOX"},{"WEEK 8","CLE ","@KAN ","10","27","1","0","CBS"},{"WEEK 8","BUF ","@NO ","10","27","1","0","CBS"},{"WEEK 8","SF ","@JAC ","10","27","1","0","FOX"},{"WEEK 8","MIA ","@NE ","10","27","1","0","CBS"},{"WEEK 8","PIT ","@OAK ","10","27","4","5","CBS"},{"WEEK 8","NYJ ","@CIN ","10","27","4","5","CBS"},{"WEEK 8","WAS ","@DEN ","10","27","4","25","FOX"},{"WEEK 8","ATL ","@ARI ","10","27","4","25","FOX"},{"WEEK 8","GB ","@MIN ","10","27","8","30","NBC"},{"WEEK 8","SEA ","@STL ","10","28","8","40","ESPN"},{"WEEK 9","CIN ","@MIA ","10","31","8","25","NFL Network"},{"WEEK 9","KAN ","@BUF ","11","3","1","0","CBS"},{"WEEK 9","ATL ","@CAR ","11","3","1","0","FOX"},{"WEEK 9","SD ","@WAS ","11","3","1","0","FOX"},{"WEEK 9","NO ","@NYJ ","11","3","1","0","FOX"},{"WEEK 9","TEN ","@STL ","11","3","1","0","CBS"},{"WEEK 9","MIN ","@DAL ","11","3","1","0","FOX"},{"WEEK 9","TB ","@SEA ","11","3","4","5","FOX"},{"WEEK 9","PHI ","@OAK ","11","3","4","5","FOX"},{"WEEK 9","PIT ","@NE ","11","3","4","25","CBS"},{"WEEK 9","BAL ","@CLE ","11","3","4","25","CBS"},{"WEEK 9","IND ","@HOU ","11","3","8","30","NBC"},{"WEEK 9","CHI ","@GB ","11","4","8","40","ESPN"},{"WEEK 10","WAS ","@MIN ","11","7","8","25","NFL Network"},{"WEEK 10","PHI ","@GB ","11","10","1","0","FOX"},{"WEEK 10","DET ","@CHI ","11","10","1","0","FOX"},{"WEEK 10","STL ","@IND ","11","10","1","0","FOX"},{"WEEK 10","CIN ","@BAL ","11","10","1","0","CBS"},{"WEEK 10","BUF ","@PIT ","11","10","1","0","CBS"},{"WEEK 10","JAC ","@TEN ","11","10","1","0","CBS"},{"WEEK 10","OAK ","@NYG ","11","10","1","0","CBS"},{"WEEK 10","SEA ","@ATL ","11","10","1","0","FOX"},{"WEEK 10","CAR ","@SF ","11","10","4","5","FOX"},{"WEEK 10","HOU ","@ARI ","11","10","4","25","CBS"},{"WEEK 10","DEN ","@SD ","11","10","4","25","CBS"},{"WEEK 10","DAL ","@NO ","11","10","8","30","NBC"},{"WEEK 10","MIA ","@TB ","11","11","8","40","ESPN"},{"WEEK 11","IND ","@TEN ","11","14","8","25","NFL Network"},{"WEEK 11","OAK ","@HOU ","11","17","1","0","CBS"},{"WEEK 11","NYJ ","@BUF ","11","17","1","0","CBS"},{"WEEK 11","CLE ","@CIN ","11","17","1","0","CBS"},{"WEEK 11","BAL ","@CHI ","11","17","1","0","CBS"},{"WEEK 11","SD ","@MIA ","11","17","1","0","CBS"},{"WEEK 11","ARI ","@JAC ","11","17","1","0","FOX"},{"WEEK 11","ATL ","@TB ","11","17","1","0","FOX"},{"WEEK 11","DET ","@PIT ","11","17","1","0","FOX"},{"WEEK 11","WAS ","@PHI ","11","17","1","0","FOX"},{"WEEK 11","KAN ","@DEN ","11","17","4","5","CBS"},{"WEEK 11","MIN ","@SEA ","11","17","4","25","FOX"},{"WEEK 11","SF ","@NO ","11","17","4","25","FOX"},{"WEEK 11","GB ","@NYG ","11","17","8","30","NBC"},{"WEEK 11","NE ","@CAR ","11","18","8","40","ESPN"},{"WEEK 12","NO ","@ATL ","11","21","8","25","NFL Network"},{"WEEK 12","CAR ","@MIA ","11","24","1","0","FOX"},{"WEEK 12","SD ","@KAN ","11","24","1","0","CBS"},{"WEEK 12","PIT ","@CLE ","11","24","1","0","CBS"},{"WEEK 12","JAC ","@HOU ","11","24","1","0","CBS"},{"WEEK 12","NYJ ","@BAL ","11","24","1","0","CBS"},{"WEEK 12","MIN ","@GB ","11","24","1","0","FOX"},{"WEEK 12","TB ","@DET ","11","24","1","0","FOX"},{"WEEK 12","CHI ","@STL ","11","24","1","0","FOX"},{"WEEK 12","IND ","@ARI ","11","24","4","5","CBS"},{"WEEK 12","TEN ","@OAK ","11","24","4","5","CBS"},{"WEEK 12","DAL ","@NYG ","11","24","4","25","FOX"},{"WEEK 12","DEN ","@NE ","11","24","8","30","NBC"},{"WEEK 12","SF ","@WAS ","11","25","8","40","ESPN"},{"WEEK 13","GB ","@DET ","11","28","12","30","FOX"},{"WEEK 13","OAK ","@DAL ","11","28","4","30","CBS"},{"WEEK 13","PIT ","@BAL ","11","28","8","30","NBC"},{"WEEK 13","DEN ","@KAN ","12","1","1","0","CBS"},{"WEEK 13","JAC ","@CLE ","12","1","1","0","CBS"},{"WEEK 13","TEN ","@IND ","12","1","1","0","CBS"},{"WEEK 13","ARI ","@PHI ","12","1","1","0","FOX"},{"WEEK 13","MIA ","@NYJ ","12","1","1","0","CBS"},{"WEEK 13","CHI ","@MIN ","12","1","1","0","FOX"},{"WEEK 13","TB ","@CAR ","12","1","1","0","FOX"},{"WEEK 13","STL ","@SF ","12","1","4","5","FOX"},{"WEEK 13","ATL ","@BUF ","12","1","4","5","FOX"},{"WEEK 13","CIN ","@SD ","12","1","4","25","CBS"},{"WEEK 13","NE ","@HOU ","12","1","4","25","CBS"},{"WEEK 13","NYG ","@WAS ","12","1","8","30","NBC"},{"WEEK 13","NO ","@SEA ","12","2","8","40","ESPN"},{"WEEK 14","HOU ","@JAC ","12","5","8","25","NFL Network"},{"WEEK 14","CLE ","@NE ","12","8","1","0","CBS"},{"WEEK 14","IND ","@CIN ","12","8","1","0","CBS"},{"WEEK 14","BUF ","@TB ","12","8","1","0","CBS"},{"WEEK 14","MIN ","@BAL ","12","8","1","0","FOX"},{"WEEK 14","MIA ","@PIT ","12","8","1","0","CBS"},{"WEEK 14","OAK ","@NYJ ","12","8","1","0","CBS"},{"WEEK 14","KAN ","@WAS ","12","8","1","0","CBS"},{"WEEK 14","CAR ","@NO ","12","8","1","0","FOX"},{"WEEK 14","DET ","@PHI ","12","8","1","0","FOX"},{"WEEK 14","TEN ","@DEN ","12","8","4","5","CBS"},{"WEEK 14","STL ","@ARI ","12","8","4","25","FOX"},{"WEEK 14","NYG ","@SD ","12","8","4","25","FOX"},{"WEEK 14","SEA ","@SF ","12","8","4","25","FOX"},{"WEEK 14","ATL ","@GB ","12","8","8","30","NBC"},{"WEEK 14","DAL ","@CHI ","12","9","8","40","ESPN"},{"WEEK 15","SD ","@DEN ","12","12","8","25","NFL Network"},{"WEEK 15","CHI ","@CLE ","12","15","1","0","FOX"},{"WEEK 15","HOU ","@IND ","12","15","1","0","CBS"},{"WEEK 15","BUF ","@JAC ","12","15","1","0","CBS"},{"WEEK 15","WAS ","@ATL ","12","15","1","0","FOX"},{"WEEK 15","NE ","@MIA ","12","15","1","0","CBS"},{"WEEK 15","SF ","@TB ","12","15","1","0","FOX"},{"WEEK 15","SEA ","@NYG ","12","15","1","0","FOX"},{"WEEK 15","NO ","@STL ","12","15","1","0","FOX"},{"WEEK 15","PHI ","@MIN ","12","15","1","0","FOX"},{"WEEK 15","ARI ","@TEN ","12","15","1","0","FOX"},{"WEEK 15","NYJ ","@CAR ","12","15","4","5","CBS"},{"WEEK 15","KAN ","@OAK ","12","15","4","5","CBS"},{"WEEK 15","GB ","@DAL ","12","15","4","25","FOX"},{"WEEK 15","CIN ","@PIT ","12","15","8","30","NBC"},{"WEEK 15","BAL ","@DET ","12","16","8","40","ESPN"},{"WEEK 16","MIN ","@CIN ","12","22","1","0","FOX"},{"WEEK 16","MIA ","@BUF ","12","22","1","0","CBS"},{"WEEK 16","IND ","@KAN ","12","22","1","0","CBS"},{"WEEK 16","CLE ","@NYJ ","12","22","1","0","CBS"},{"WEEK 16","DEN ","@HOU ","12","22","1","0","CBS"},{"WEEK 16","TEN ","@JAC ","12","22","1","0","CBS"},{"WEEK 16","DAL ","@WAS ","12","22","1","0","FOX"},{"WEEK 16","CHI ","@PHI ","12","22","1","0","FOX"},{"WEEK 16","NO ","@CAR ","12","22","1","0","FOX"},{"WEEK 16","TB ","@STL ","12","22","1","0","FOX"},{"WEEK 16","NYG ","@DET ","12","22","4","5","FOX"},{"WEEK 16","ARI ","@SEA ","12","22","4","5","FOX"},{"WEEK 16","PIT ","@GB ","12","22","4","25","CBS"},{"WEEK 16","OAK ","@SD ","12","22","4","25","CBS"},{"WEEK 16","NE ","@BAL ","12","22","8","30","NBC"},{"WEEK 16","ATL ","@SF ","12","23","8","40","ESPN"},{"WEEK 17","CAR ","@ATL ","12","29","1","0","FOX"},{"WEEK 17","NYJ ","@MIA ","12","29","1","0","CBS"},{"WEEK 17","BAL ","@CIN ","12","29","1","0","CBS"},{"WEEK 17","BUF ","@NE ","12","29","1","0","CBS"},{"WEEK 17","CLE ","@PIT ","12","29","1","0","CBS"},{"WEEK 17","JAC ","@IND ","12","29","1","0","CBS"},{"WEEK 17","HOU ","@TEN ","12","29","1","0","CBS"},{"WEEK 17","DET ","@MIN ","12","29","1","0","FOX"},{"WEEK 17","PHI ","@DAL ","12","29","1","0","FOX"},{"WEEK 17","GB ","@CHI ","12","29","1","0","FOX"},{"WEEK 17","WAS ","@NYG ","12","29","1","0","FOX"},{"WEEK 17","TB ","@NO ","12","29","1","0","FOX"},{"WEEK 17","STL ","@SEA ","12","29","4","25","FOX"},{"WEEK 17","DEN ","@OAK ","12","29","4","25","CBS"},{"WEEK 17","KAN ","@SD ","12","29","4","25","CBS"},{"WEEK 17","SF ","@ARI ","12","29","4","25","FOX"}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.teamlistview);
        final ArrayAdapter<String> teamAdapter;
        teamAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, teams);
        lv.setAdapter(teamAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = Integer.toString(i);
                Log.d("test",s);

                final int index = Integer.valueOf(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Add all of the "+teams[i]+" games to your calendar?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        dialog.dismiss();
                       int count = addEventsFromTeam(teama[index]);
                       Log.d("count",Integer.toString(count));
                       Toast.makeText(getApplicationContext(),"Games added: "+count,Toast.LENGTH_LONG).show();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

 }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    int addEventsFromTeam(String team){
        int c = 0;
        //count of events
        for(int i = 0; i<events.length; i++){
            if(events[i][1].contains(team) || events[i][2].contains(team)){
                c+=1;
                Long month = Long.parseLong(events[i][3]);
                if (month == 9l) month=0l;
                if (month==10l) month=30l;
                if (month==11l) month=61l;
                if (month==12l) month=91l;
                month = month*(24l*60l*60l*1000l);

                Long day = Long.parseLong(events[i][4]);
                day = day-1l;
                day = day*(24l*60l*60l*1000l);

                Long timeHour = Long.parseLong(events[i][5]);
                if (timeHour < 12l) timeHour+=12l;
                timeHour = timeHour*60l*60l*1000l;
                timeHour = timeHour + 18000000l + TimeZone.getDefault().getRawOffset();
                if(Long.parseLong(events[i][3])>=11l && Long.parseLong(events[i][4])>=3) timeHour+=60l*60l*1000l;

                Long timeMinute = Long.parseLong(events[i][6]);
                timeMinute = timeMinute*60l*1000l;

//1378008000000 9/1/2013
                Long startTime = 1378008000000l + month + day +timeHour+timeMinute;
                Log.d("starttime",Long.toString(startTime));
//haha
                Cursor cursor = null ;
                String[] projection = new String[] {
                        CalendarContract.Calendars._ID,
                        CalendarContract.Calendars.ACCOUNT_NAME,};
                ContentResolver cr = getApplicationContext().getContentResolver();
                cursor = cr.query(Uri.parse("content://com.android.calendar/calendars"), projection, null, null, null);
                int[] calIds = null;
                if ( cursor.moveToFirst() ) {
                    final String[] calNames = new String[cursor.getCount()];
                    calIds = new int[cursor.getCount()];
                    for (int j = 0; j < calNames.length; j++) {
                        calIds[j] = cursor.getInt(0);
                        calNames[j] = cursor.getString(1);
                        cursor.moveToNext();
                    }
                }
                try {
                    ContentValues values = new ContentValues();
                    int apiLevel = android.os.Build.VERSION.SDK_INT;
                        if(apiLevel<14){
                          values.put("visibility", 0);

                        }
                    values.put(CalendarContract.Events.DTSTART, startTime);
                    values.put(CalendarContract.Events.DTEND, startTime+60l*60l*3l*1000l);
                    values.put(CalendarContract.Events.TITLE, events[i][1]+events[i][2]);
                    values.put(CalendarContract.Events.DESCRIPTION, "Regular Season");
                    values.put(CalendarContract.Events.CALENDAR_ID, calIds[0]);
                    // values.put(Events.EVENT_LOCATION,"place");
                    Log.d("tztz",TimeZone.getDefault().getID());
                    Log.d("tztztz", String.valueOf(TimeZone.getDefault().getRawOffset()));
                    values.put(CalendarContract.Events.EVENT_TIMEZONE,TimeZone.getDefault().getID());
                    Uri mInsert =  cr.insert(CalendarContract.Events.CONTENT_URI, values);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        return c;
    }
}
