package interview.drupe.interviewtasknm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import interview.drupe.interviewtasknm.gamecomponents.GameManager;
import interview.drupe.interviewtasknm.gamecomponents.gameEventListener;

public class MainActivity extends AppCompatActivity {
    TextView drawTv;
    GameManager game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawTv = findViewById(R.id.drawTV);

        game = new GameManager(new gameEventListener() {
            @Override
            public void onDrawResult(final String drawStr) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        drawTv.setText(drawStr);
                    }
                });

            }

            @Override
            public void onGameEnd() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "GameOver", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        drawTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.runGameLoop();
            }
        });
        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.onMoveRight();
            }
        });
        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.onMoveLeft();
            }
        });

    }


}
