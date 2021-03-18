package pollub.ism.lab04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

     private String znak = "O";
     private TextView tura;
     private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tura = (TextView) findViewById(R.id.tura);
        tura.setText("Tura: " + znak);

        buttons = getButtons();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        setContentView(R.layout.activity_main);
        super.onSaveInstanceState(outState);
        outState.putString("button00", (String) buttons[0][0].getText());
        outState.putString("button01", (String) buttons[0][1].getText());
        outState.putString("button02", (String) buttons[0][2].getText());
        outState.putString("button10", (String) buttons[1][0].getText());
        outState.putString("button11", (String) buttons[1][1].getText());
        outState.putString("button12", (String) buttons[1][2].getText());
        outState.putString("button20", (String) buttons[2][0].getText());
        outState.putString("button21", (String) buttons[2][1].getText());
        outState.putString("button22", (String) buttons[2][2].getText());


        outState.putString("znak", znak);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        buttons[0][0].setText(savedInstanceState.getString("button00"));
        buttons[0][1].setText(savedInstanceState.getString("button01"));
        buttons[0][2].setText(savedInstanceState.getString("button02"));
        buttons[1][0].setText(savedInstanceState.getString("button10"));
        buttons[1][1].setText(savedInstanceState.getString("button11"));
        buttons[1][2].setText(savedInstanceState.getString("button12"));
        buttons[2][0].setText(savedInstanceState.getString("button20"));
        buttons[2][1].setText(savedInstanceState.getString("button21"));
        buttons[2][2].setText(savedInstanceState.getString("button22"));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(buttons[i][j].getText() != ""){
                    buttons[i][j].setEnabled(false);
                }
            }
        }

        znak = savedInstanceState.getString("znak");
        tura.setText("Tura: " + znak);

    }

    public void kliknieciePrzycisku(View view){

        Button btn = (Button) view;
        btn.setText(znak);

        btn.setEnabled(false);

        if(wygrana(znak)){
            Toast.makeText(this,"Wygrały " + znak, Toast.LENGTH_LONG).show();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }

        }

        if(znak == "O"){
            znak = "X";
        }
        else if(znak == "X"){
            znak = "O";
        }

        tura.setText("Tura: " + znak);

    }


    public void restart(View view) {

        tura.setText("Tura: " + znak);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private Button[][] getButtons(){
        Button[][] buttons = new Button[3][3];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++){
                buttons[i-1][j-1] = (Button) findViewById(getResources().getIdentifier("button_" + i + "_" + j, "id",
                        this.getPackageName()));
            }
        }

        return buttons;
    }

    private boolean wygrana(String znak){

        //w poziomie
        if(buttons[0][0].getText() == znak && buttons[0][1].getText() == znak && buttons[0][2].getText() == znak){
            return true;
        }
        if(buttons[1][0].getText() == znak && buttons[1][1].getText() == znak && buttons[1][2].getText() == znak){
            return true;
        }
        if(buttons[2][0].getText() == znak && buttons[2][1].getText() == znak && buttons[2][2].getText() == znak){
            return true;
        }

        //w pionie
        if(buttons[0][0].getText() == znak && buttons[1][0].getText() == znak && buttons[2][0].getText() == znak){
            return true;
        }
        if(buttons[0][1].getText() == znak && buttons[1][1].getText() == znak && buttons[2][1].getText() == znak){
            return true;
        }
        if(buttons[0][2].getText() == znak && buttons[1][2].getText() == znak && buttons[2][2].getText() == znak){
            return true;
        }

        //przekatne
        if(buttons[0][0].getText() == znak && buttons[1][1].getText() == znak && buttons[2][2].getText() == znak){
            return true;
        }
        if(buttons[2][0].getText() == znak && buttons[1][1].getText() == znak && buttons[0][2].getText() == znak){
            return true;
        }

        return false;
    }
}