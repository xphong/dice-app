// Phong Huynh
// 810194340
// hnhp0025
// CPAN 313 Mobile Programming
// Instructor: Igor Pustylnick
// **********************************
// Phong's Dice Application
// This application is a game that allows players to throw an abitrary number of dice (1-6).
// Each die can show the numbers 1-6 if thrown. On each throw the sum of each dice thrown is calculated.
// The player can go for a maximum for 6 times before the total number resets.
// To win you must roll either 7, 11 or 21 or reach the sum of 120
// To lose you roll 13 or 26 or run out of turns (6 turns).

package diceapp.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DiceAppActivity extends Activity {
    /** Called when the activity is first created. */
	
	//declare variables
	ImageView dice1;
	TextView output, rolled, total;
	Button roll1, roll2, roll3, roll4, roll5, roll6;

	int rollednumber, totalnumber, iteration;
	String totalnumberstring;
	
	//the function used for each click listener
	//it inserts into the database and receives from the database
	//also contains if and else statement used to decide if user wins or losses
	public void clickFunction(OpenHelper dbHelper){
			//counter for total number rolled
			totalnumber += rollednumber;
			
			//get the readable database, set database statement, get writable database
			SQLiteDatabase db = dbHelper.getReadableDatabase();
		    SQLiteStatement stmt = db.compileStatement("INSERT INTO MyTable VALUES(?);");
	        db = dbHelper.getWritableDatabase();
	        
	        //execute the database statement
			stmt.bindLong(1, totalnumber);
			stmt.executeInsert();
			
			//receive data from the database
		    Cursor cursor = db.query("MyTable", new String[]{"rolled"},null,null,null,null,"");
	    	while(cursor.moveToNext()){
	        	totalnumberstring = cursor.getString(0);
	        }
	    	
	    //set the rolled number, and total number
	    rolled.setText(Integer.toString(rollednumber));
	    total.setText(totalnumberstring);
	    
	    //counter for how many turns the user takes
	    iteration += 1;
	    
	    //if user reaches maximum tries of 6, they lose
	    if (iteration == 6){
	    	output.setText("Maximum Tries Reached. YOU LOSE");
	    	totalnumber=0;
	    	iteration=0;
	    }
	    
	    //if the user rolls one of these numbers, they win
	    if (rollednumber==7 || rollednumber==11 || rollednumber==21){
	    	output.setText("You Rolled a Winning Number. YOU WIN!!");
	    	totalnumber=0;
	    	iteration=0;
	    }
	    //if user rolls one these numbers, they lose
	    else if(rollednumber==13 || rollednumber==26){
	    	output.setText("You rolled a Losing Number. YOU LOSE.");
	    	totalnumber=0;
	    	iteration=0;
	    }
	    
	    //if the user's total number is 120, they win
	    if (totalnumber == 120){
	    	output.setText("You WIN!!");
	    	totalnumber=0;
	    	iteration=0;
	    }
	}
	
	//function used to provide random numbers for the dices rolled
	public int random(int lo, int hi){
		int range = Math.abs(hi-lo) +1;
		int random = (int) ((Math.random() * range) + lo);
		return random;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Get textviews
        output = (TextView)findViewById(R.id.txtOutput);
        rolled = (TextView)findViewById(R.id.txtCounter);
        total = (TextView)findViewById(R.id.txtTotalCounter);
        
        //Get buttons
        roll1 = (Button)findViewById(R.id.butRoll1);
        roll2 = (Button)findViewById(R.id.butRoll2);
        roll3 = (Button)findViewById(R.id.butRoll3);
        roll4 = (Button)findViewById(R.id.butRoll4);
        roll5 = (Button)findViewById(R.id.butRoll5);
        roll6 = (Button)findViewById(R.id.butRoll6);
        dice1 = (ImageView)findViewById(R.id.viewDice1);
         
        //Set the animation resources for the die
        dice1.setImageResource(R.anim.dice_anim);
        final AnimationDrawable diceAnimation = (AnimationDrawable)dice1.getDrawable();
        
        //Set the mediaplayer to play the dice sound file
        final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.diceclip);
        
        //Open the helper for SQLite database
	    final OpenHelper dbHelper = new OpenHelper(this);
	    
	    //Handle click listeners
	    //Roll 1 Button Clicked
        roll1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//call dice sound
		        mPlayer.start();
		    	output.setText("");
		    	//set random number for 1 dice rolled
				rollednumber = random(1,6);
				//call dice animation
				diceAnimation.run();
				//insert and receive info from SQLite database
				clickFunction(dbHelper);
			}	
        });
        
	    //Roll 2 Button Clicked
        roll2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
		        mPlayer.start();
		    	output.setText("");
				rollednumber = random(2,12);
				diceAnimation.run();
				clickFunction(dbHelper);
			}	
        });
        
	    //Roll 3 Button Clicked
        roll3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
		        mPlayer.start();
		    	output.setText("");
				rollednumber = random(3,18);
				diceAnimation.run();
				clickFunction(dbHelper);
			}	
        });
        
	    //Roll 4 Button Clicked
        roll4.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
		        mPlayer.start();
		    	output.setText("");
				rollednumber = random(4,24);
				diceAnimation.run();
				clickFunction(dbHelper);
			}	
        });
        
	    //Roll 5 Button Clicked
        roll5.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
		        mPlayer.start();
		    	output.setText("");
				rollednumber = random(5,30);
				diceAnimation.run();
				clickFunction(dbHelper);
			}	
        });
        
	    //Roll 6 Button Clicked
        roll6.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
		        mPlayer.start();
		    	output.setText("");
				rollednumber = random(6,36);
				diceAnimation.run();
				clickFunction(dbHelper);
			}	
        });
    }
}