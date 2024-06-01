package zazueta.marcos.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

var maxValue = 100;
var minValue = 0;
var num:Int = 0;
var won = false;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings);
        val up: Button = findViewById(R.id.up);
        val down: Button = findViewById(R.id.down);
        val generate: Button = findViewById(R.id.generate);
        val guessed: Button = findViewById(R.id.guessed);

        generate.setOnClickListener{
            num = Random.nextInt(minValue, maxValue);
            guessings.setText(num.toString());
            generate.visibility = View.INVISIBLE;
            guessed.visibility = View.VISIBLE;
        }

        up.setOnClickListener{
            minValue = num;
            if(checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("Oh no :( You Won")
            }
        }

        down.setOnClickListener{
            maxValue = num;
            if(checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("Oh no :( You Won")
            }
        }

        guessed.setOnClickListener {
            if(!won){
                guessings.setText("Guessed, Your number is "+num)
                guessed.setText(("Play again?"))
                won = true

            }else{
                generate.visibility = View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.visibility = View.GONE
                resetValues();
            }

        }
    }

    fun checkingLimits(): Boolean{
        return minValue!= maxValue;
    }

    fun resetValues(){
        minValue = 0;
        maxValue = 100;
        won = false;
        num = 0;
    }
}