package diceapp.activity;

public class PhongsRandom {
	
	public static int random(int lo, int hi){
		int range = Math.abs(hi-lo) +1;
		int random = (int) ((Math.random() * range) + lo);
		return random;
	}
}
