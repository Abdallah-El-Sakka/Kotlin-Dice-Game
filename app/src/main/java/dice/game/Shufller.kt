package dice.game

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Shuffler {

    private fun shuffle(youImg: ImageView,AiImg : ImageView) {

        x = (1..6).random()
        y = (1..6).random()

        when(x){
            1 -> youImg.setImageResource(R.drawable.first)
            2 -> youImg.setImageResource(R.drawable.second)
            3 -> youImg.setImageResource(R.drawable.third)
            4 -> youImg.setImageResource(R.drawable.forth)
            5 -> youImg.setImageResource(R.drawable.fifth)
            6 -> youImg.setImageResource(R.drawable.sixth)
        }

        when(y){
            1 -> AiImg.setImageResource(R.drawable.first)
            2 -> AiImg.setImageResource(R.drawable.second)
            3 -> AiImg.setImageResource(R.drawable.third)
            4 -> AiImg.setImageResource(R.drawable.forth)
            5 -> AiImg.setImageResource(R.drawable.fifth)
            6 -> AiImg.setImageResource(R.drawable.sixth)

        }
    }

    private fun updateScore(playerScoreText : TextView , aiScoreText : TextView){
        playerScoreText.text = playerScore.toString()
        aiScoreText.text = aiScore.toString()
    }

    private fun checkForWinner(context: Context , playerScoreText: TextView , aiScoreText: TextView){
        when{
            x > y -> {
                playerScore++
                Toast.makeText(context,"You won !", Toast.LENGTH_SHORT).show()
                updateScore(playerScoreText,aiScoreText)
            }
            x < y -> {
                aiScore++
                Toast.makeText(context,"AI won", Toast.LENGTH_SHORT).show()
                updateScore(playerScoreText,aiScoreText)
            }
            x == y -> {

                Toast.makeText(context,"Draw", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun shuffleEffect(context: Context , AiImg: ImageView , youImg: ImageView , shuffleBtn : Button , playerScoreText: TextView , aiScoreText: TextView){

        val mainHandler = Handler(Looper.getMainLooper())
        var timer = 30

        mainHandler.post(object : Runnable {
            override fun run() {

                timer--

                if (timer > 0) {
                    shuffle(youImg , AiImg)
                    shuffleBtn.isEnabled = false
                    mainHandler.postDelayed(this, 30)
                }else{
                    shuffleBtn.isEnabled = true
                    checkForWinner(context,playerScoreText,aiScoreText)
                }
            }
        })
    }

    companion object{
        var x : Int = 0
        var y : Int = 0

        var playerScore = 0
        var aiScore = 0
    }

}