package saif.rehman.connect3game;

import
        androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0:yellow 1;red
    int activePlayer = 0;
    boolean gameActive = true;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPostions = {{0,1,2},{3,4,5},{6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedcounter =  Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedcounter] == 2 && gameActive) {
            gameState[tappedcounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPostions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    //Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                    Button playAgainButton = (Button)findViewById(R.id.playAgianButtton);
                    TextView winnerTextView =  (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        Button playAgainButton = (Button)findViewById(R.id.playAgianButtton);
        TextView winnerTextView =  (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout =  (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}