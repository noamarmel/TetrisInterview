package interview.drupe.interviewtasknm;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import interview.drupe.interviewtasknm.gamecomponents.GameManager;
import interview.drupe.interviewtasknm.gamecomponents.interaction.gameEventListener;

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
            public void onDrawResult(final boolean[][] currBoard, final int width, final int height) {
                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        drawTv.setText("");
                        String empyCell = "#";
//                        String allocatedCell = "<font color=#cc0029>#</font>";


//                        final StringBuilder builder = new StringBuilder();

                        for (int r = 0; r < height; r++) {
                            for (int c = 0; c < width; c++) {
                                Spannable allocatedCell = new SpannableString("#");
                                allocatedCell.setSpan(new ForegroundColorSpan(Color.RED), 0, allocatedCell.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                drawTv.append(currBoard[r][c] ? allocatedCell : empyCell);
//                                builder.append(currBoard[r][c] ? allocatedCell : empyCell);
                            }

                            if (r < height - 1) {
//                                builder.append("\n");
                                drawTv.append("\n");
                            }
                        }


//                        drawTv.setText(Html.fromHtml(builder.toString(), Html.FROM_HTML_OPTION_USE_CSS_COLORS));
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
                drawTv.setOnClickListener(null);
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
        findViewById(R.id.rotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.onRotate();
            }
        });

    }


}
