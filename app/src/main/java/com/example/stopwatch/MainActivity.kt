//------------------------------------------------------------------------------------------------------------------//
// Name: Dylan Shortt
// Group: 1
// Student Number: ST-10438409
//------------------------------------------------------------------------------------------------------------------//

//------------------------------------------------------------------------------------------------------------------//
// <summary>
// The namespace is used to group classes that are logically related.
// </summary>
package com.example.stopwatch
//------------------------------------------------------------------------------------------------------------------//
// <summary>
// These import statements are used to import the necessary libraries for the program to run.
// </summary>
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stopwatch.databinding.ActivityMainBinding

//------------------------------------------------------------------------------------------------------------------//

//------------------------------------------------------------------------------------------------------------------//
// <summary>
// The MainActivity class is the entry point for the application.
// </summary>
class MainActivity : AppCompatActivity() {

    //-------------------------------------------------//
    // Step 1: setup binding
    private lateinit var binding: ActivityMainBinding
    //-------------------------------------------------//

    //-------------------------------------------------//
    // Step 2: handler
    private var isRunning = false
    private var timerSeconds = 0

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            timerSeconds++
            val hours = timerSeconds / 3600
            val minutes = (timerSeconds % 3600) / 60
            val seconds = timerSeconds % 60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.timerText.text = time

            handler.postDelayed(this, 1000)
        }
    }
    //-------------------------------------------------//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //-------------------------------------------------//
        // Step 1: setup binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //-------------------------------------------------//

        //------------------------------------------------------------------------------------------------------------------//

        //-------------------------------------------------//
        // step 6: call each method in their appropriate button
        binding.startButton.setOnClickListener {
            startTimer()
        }

        binding.stopButton.setOnClickListener {
            stopTimer()
        }

        binding.resetButton.setOnClickListener {
            resetTimer()
        }
        //-------------------------------------------------//

    }
    //------------------------------------------------------------------------------------------------------------------//

    //------------------------------------------------------------------------------------------------------------------//
    // <summary>
    // Step 3: create start timer method
    // </summary>
    private fun startTimer(){
        if(!isRunning) {
            handler.postDelayed(runnable, 1000)
            isRunning = true

            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }
    }
    //------------------------------------------------------------------------------------------------------------------//

    //------------------------------------------------------------------------------------------------------------------//
    // <summary>
    // Step 4: Create stop timer method
    // </summary>
    private fun stopTimer() {
        if (isRunning){
            handler.removeCallbacks(runnable)
            isRunning = false

            // Enable the start button and change to "Resume"0000000000
            binding.startButton.isEnabled = true
            binding.startButton.text = "Resume"

            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
        }
    }
    //------------------------------------------------------------------------------------------------------------------//

    //------------------------------------------------------------------------------------------------------------------//
    // <summary>
    // Step 5: create resetTimer method
    // </summary>
    private fun resetTimer() {
        stopTimer()

        timerSeconds = 0
        binding.timerText.text = "00:00:00"

        binding.startButton.isEnabled = true
        binding.stopButton.isEnabled = false
        binding.resetButton.isEnabled = false
    }
    //------------------------------------------------------------------------------------------------------------------//
}
//-----------------------------------...ooo000 END OF FILE 000ooo...-----------------------------------//