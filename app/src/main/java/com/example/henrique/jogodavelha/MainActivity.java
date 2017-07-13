package com.example.henrique.jogodavelha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final String QUADRADO = "quadrado";
    private final String BOLA = "O";
    private final String XIS = "X";

    private String lastPlay = "X";

    private View view;
    private Toast toast;

    int[][] estadoFinal = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},

            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},

            {1, 5, 9},
            {3, 5, 7}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.activity_main, null));
        setContentView(getView());
    }

    public void clickQuadrado(View v) {

        if (!isFim()) {

            if (((Button) v).getText().equals("")) {
                if (getLastPlay().equals(XIS)) {
                    ((Button) v).setText(BOLA);
                    setLastPlay(BOLA);
                } else {
                    ((Button) v).setText(XIS);
                    setLastPlay(XIS);
                }
            }
            isFim();
        }
    }

    public Button getQuadrado(int tagNum) {
        return (Button) getView().findViewWithTag(QUADRADO + tagNum);
    }

    public void newGame(View v) {

        if (toast != null){
            toast.cancel();
        }

        setColorBlack();
        setEnableQuadrado(true);

        for (int i = 1; i <= 9; ++i) {
            if (getQuadrado(i) != null) {
                getQuadrado(i).setText("");
            }
        }
    }

    public void setEnableQuadrado(boolean b) {
        for (int i = 1; i <= 9; ++i) {
            if (getQuadrado(i) != null) {
                getQuadrado(i).setEnabled(b);
            }
        }
    }

    public void setColorBlack() {
        for (int i = 0; i <= 9; ++i) {
            if (getQuadrado(i) != null) {
                setColorQuadrados(i, R.color.black);
            }
        }
    }

    public boolean isFim() {

        for (int x = 0; x <= 7; ++x) {
            String s1 = getQuadrado(estadoFinal[x][0]).getText().toString();
            String s2 = getQuadrado(estadoFinal[x][1]).getText().toString();
            String s3 = getQuadrado(estadoFinal[x][2]).getText().toString();

            if ((!s1.equals("")) &&
                    (!s2.equals("")) &&
                    (!s3.equals(""))) {

                if (s1.equals(s2) && (s2.equals(s3))) {
                    setEnableQuadrado(false);

                    setColorQuadrados(estadoFinal[x][0], R.color.lightBlue);
                    setColorQuadrados(estadoFinal[x][1], R.color.lightBlue);
                    setColorQuadrados(estadoFinal[x][2], R.color.lightBlue);

                    toast = Toast.makeText(getView().getContext(), "Fim de Jogo", Toast.LENGTH_SHORT);
                    toast.show();

                    return true;
                }
            }
        }
        return false;
    }

    public void setColorQuadrados(int botao, int colorX) {
        getQuadrado(botao).setTextColor(getResources().getColor(colorX));
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getLastPlay() {
        return lastPlay;
    }

    public void setLastPlay(String lastPlay) {
        this.lastPlay = lastPlay;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // quando o item do menu é selecionado este método é chamado;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_bolinha:
                setLastPlay(XIS);
                newGame(this.view);
                break;
            case R.id.menu_xis:
                setLastPlay(XIS);
                setLastPlay(BOLA);
                newGame(this.view);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

