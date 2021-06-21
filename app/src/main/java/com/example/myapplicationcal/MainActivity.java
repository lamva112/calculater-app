package com.example.myapplicationcal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workingsTV;
    TextView resultsTV;

    String workings = "";
    String fomula = "";
    String tempFomula = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void setWorkings(String givenValue){
        workings += givenValue;
        workingsTV.setText(workings);
    }

    private void initTextViews() {
        workingsTV = (TextView) findViewById(R.id.workingTextView);
        resultsTV = (TextView) findViewById(R.id.resultTextView);
    }

    public void equalOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        checkForPowerOff();

        try{
            result = (double)engine.eval(fomula);
        }catch (ScriptException e){
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_LONG).show();
        }

        if(result != null) {
            resultsTV.setText(String.valueOf(result.doubleValue()));
        }




    }

    private void checkForPowerOff() {
        ArrayList<Integer>  indexOfPowers = new ArrayList<>();
        for(int i = 0; i < workings.length(); i++){
            if(workings.charAt(i) == '^'){
                indexOfPowers.add(i);
            }

            fomula = workings;
            tempFomula = workings;
            for(Integer index: indexOfPowers){
                changefomula(index);
            }
            fomula = tempFomula;
        }
    }

    private void changefomula(Integer index) {
        String numberleft = "";
        String numberright = "";

        for(int i = index + 1; i <workings.length(); i++){
            if(isNumberic(workings.charAt(i))){
                numberright += workings.charAt(i);
            }
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--){
            if(isNumberic(workings.charAt(i))){
                numberleft += workings.charAt(i);
            }
            else
                break;
        }

        String original = numberleft +"^"+numberright;
        String chaged = "Math.pow(" + numberleft + "," + numberright +")";
        tempFomula = tempFomula.replace(original,chaged);

    }

    private boolean isNumberic(char c){
        if(c <= '9' && c >= '0' || c == '.'){
            return true;
        }
        return false;
    }


    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view) {
        if(leftBracket){
            setWorkings("(");
            leftBracket = false;
        }
        else{
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnclick(View view) {
       setWorkings("^");
    }

    public void divisionOnclick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void TimesOnCLick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }

}