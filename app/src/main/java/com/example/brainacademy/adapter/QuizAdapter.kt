import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brainacademy.R
import com.example.brainacademy.questiondatabse

class QuizAdapter(
//    private val context: Context,
//    private val questionList: List<questiondatabse>,
//    private val selectedOptionColor: Int
//) : RecyclerView.Adapter<QuizAdapter.OptionViewHolder>() {
//
//    private var selectedOptionPosition = -1
//
//    private var onOptionSelectedListener: OnOptionSelectedListener? = null
//
//    fun setOnOptionSelectedListener(listener: OnOptionSelectedListener) {
//        onOptionSelectedListener = listener
//    }
//    private val selectedOptions = mutableMapOf<Int, String>()
//
//    interface OnOptionSelectedListener {
//        fun onOptionSelected(question: questiondatabse, selectedOption: String)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
//        val itemView = LayoutInflater.from(context)
//            .inflate(R.layout.option_item, parent, false)
//        return OptionViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
//        val question = questionList[position]
//        holder.optionView.text = question.getOptionAt(position)
//        holder.descriptionView.text = question.description
//
//        holder.itemView.setOnClickListener {
//            val selectedOption = question.getOptionAt(position)
//            onOptionSelectedListener!!.onOptionSelected(question, selectedOption)
//            setSelectedOption(position, selectedOption)
//        }
//
//        if (selectedOptions.containsKey(position)) {
//            val userAnswer = selectedOptions[position]
//            if (userAnswer == question.correctanswer) {
//                holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
//            } else {
//                holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
//            }
//        } else {
//            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return questionList.size
//    }
//
//    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val optionView = itemView.findViewById<TextView>(R.id.QuizOption)
//        val descriptionView = itemView.findViewById<TextView>(R.id.QuizQuestion)
//    }
//
//    fun setSelectedOption(questionIndex: Int, option: String) {
//        selectedOptions[questionIndex] = option
//        notifyDataSetChanged()
//    }
//
//    fun getSelectedOption(questionIndex: Int): String? {
//        return selectedOptions[questionIndex]
//    }
//}
//
//
)