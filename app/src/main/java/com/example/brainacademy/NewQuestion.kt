package com.example.brainacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.example.brainacademy.databinding.ActivityNewQuestionBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewQuestion : AppCompatActivity() {

    private lateinit var binding: ActivityNewQuestionBinding
    private lateinit var database: DatabaseReference
    lateinit var outerSpinner: Spinner
    lateinit var innerSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)



        outerSpinner = findViewById(R.id.outer_spinner)
        innerSpinner = findViewById(R.id.inner_spinner)


        val outerSpinnerArray = arrayOf("Select Category","Science ", "Technology", "Engineering","Mathematics","Entrepreneurship")
        val innerSpinnerArray1 = arrayOf("Select Subcategory")
        val innerSpinnerArray2 = arrayOf("Physics", "Chemistry", "Biology","Earth Science","Physical Science")
        val innerSpinnerArray3 = arrayOf("System Administration", "Network Security", "Software Development")
        val innerSpinnerArray4 = arrayOf("Computer Science", "Information Technology", "Electronics","Mechanical")
        val innerSpinnerArray5 = arrayOf("Applied Mathematics", "Statistics", "Data Science","Cryptography","Probability")
        val innerSpinnerArray6 = arrayOf("General Awareness", "Finance", "Leadership","Books","Marketing","R&d")


        val outerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, outerSpinnerArray)
        outerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        outerSpinner.adapter = outerAdapter

        outerSpinner = findViewById(R.id.outer_spinner)
        innerSpinner = findViewById(R.id.inner_spinner)


        outerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        val innerAdapter1 = ArrayAdapter(
                            this@NewQuestion,
                            android.R.layout.simple_spinner_item,
                            innerSpinnerArray1
                        )
                        innerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter1
                        Toast.makeText(this@NewQuestion,"Please select any category",Toast.LENGTH_SHORT).show()

                    }
                    1 -> {
                        val innerAdapter2 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray2)
                        innerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter2
                        binding.submitQuestion.setOnClickListener {
                            val category = parent.getItemAtPosition(position).toString()
//                            val subcategory = parent.getItemAtPosition(position).toString()


                            val subcategory = innerSpinner.selectedItem.toString()


                            val question=binding.questionTxt.text.toString()
                            val option1=binding.option1Txt.text.toString()
                            val option2=binding.option2Txt.text.toString()
                            val option3=binding.option3Txt.text.toString()
                            val option4=binding.option4Txt.text.toString()
                            val CorrectOption=binding.CorrectOptionTxt.text.toString()




                            database=FirebaseDatabase.getInstance().getReference("Question Database")
                            val Questions=Questions(category,subcategory,question,option1, option2, option3, option4, CorrectOption)
                            database.child(question).setValue(Questions).addOnSuccessListener {
                                binding.outerSpinner.clearFocus()
                                binding.innerSpinner.clearFocus()

                                binding.questionTxt.text?.clear()
                                binding.option1Txt.text?.clear()
                                binding.option2Txt.text?.clear()
                                binding.option3Txt.text?.clear()
                                binding.option4Txt.text?.clear()
                                binding.CorrectOptionTxt.text?.clear()


                                Toast.makeText(this@NewQuestion, "Successfully Saved", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {
                                Toast.makeText(this@NewQuestion,"Failed",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    2 -> {
                        val innerAdapter3 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray3)
                        innerAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter3

                        val innerAdapter2 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray3)
                        innerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter2
                        binding.submitQuestion.setOnClickListener {
                            val category = parent.getItemAtPosition(position).toString()
//                            val subcategory = parent.getItemAtPosition(position).toString()


                            val subcategory = innerSpinner.selectedItem.toString()


                            val question=binding.questionTxt.text.toString()
                            val option1=binding.option1Txt.text.toString()
                            val option2=binding.option2Txt.text.toString()
                            val option3=binding.option3Txt.text.toString()
                            val option4=binding.option4Txt.text.toString()
                            val CorrectOption=binding.CorrectOptionTxt.text.toString()




                            database=FirebaseDatabase.getInstance().getReference("Question Database")
                            val Questions=Questions(category,subcategory,question,option1, option2, option3, option4, CorrectOption)
                            database.child(question).setValue(Questions).addOnSuccessListener {
                                binding.outerSpinner.clearFocus()
                                binding.innerSpinner.clearFocus()

                                binding.questionTxt.text?.clear()
                                binding.option1Txt.text?.clear()
                                binding.option2Txt.text?.clear()
                                binding.option3Txt.text?.clear()
                                binding.option4Txt.text?.clear()
                                binding.CorrectOptionTxt.text?.clear()


                                Toast.makeText(this@NewQuestion, "Successfully Saved", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {
                                Toast.makeText(this@NewQuestion,"Failed",Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                    3 -> {
                        val innerAdapter4 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray4)
                        innerAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter4

                        val innerAdapter2 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray4)
                        innerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter2
                        binding.submitQuestion.setOnClickListener {
                            val category = parent.getItemAtPosition(position).toString()
//                            val subcategory = parent.getItemAtPosition(position).toString()


                            val subcategory = innerSpinner.selectedItem.toString()


                            val question=binding.questionTxt.text.toString()
                            val option1=binding.option1Txt.text.toString()
                            val option2=binding.option2Txt.text.toString()
                            val option3=binding.option3Txt.text.toString()
                            val option4=binding.option4Txt.text.toString()
                            val CorrectOption=binding.CorrectOptionTxt.text.toString()




                            database=FirebaseDatabase.getInstance().getReference("Question Database")
                            val Questions=Questions(category,subcategory,question,option1, option2, option3, option4, CorrectOption)
                            database.child(question).setValue(Questions).addOnSuccessListener {
                                binding.outerSpinner.clearFocus()
                                binding.innerSpinner.clearFocus()

                                binding.questionTxt.text?.clear()
                                binding.option1Txt.text?.clear()
                                binding.option2Txt.text?.clear()
                                binding.option3Txt.text?.clear()
                                binding.option4Txt.text?.clear()
                                binding.CorrectOptionTxt.text?.clear()


                                Toast.makeText(this@NewQuestion, "Successfully Saved", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {
                                Toast.makeText(this@NewQuestion,"Failed",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    4 -> {
                        val innerAdapter5 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray5)
                        innerAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter5

                        val innerAdapter2 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray5)
                        innerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter2
                        binding.submitQuestion.setOnClickListener {
                            val category = parent.getItemAtPosition(position).toString()
//                            val subcategory = parent.getItemAtPosition(position).toString()


                            val subcategory = innerSpinner.selectedItem.toString()


                            val question=binding.questionTxt.text.toString()
                            val option1=binding.option1Txt.text.toString()
                            val option2=binding.option2Txt.text.toString()
                            val option3=binding.option3Txt.text.toString()
                            val option4=binding.option4Txt.text.toString()
                            val CorrectOption=binding.CorrectOptionTxt.text.toString()




                            database=FirebaseDatabase.getInstance().getReference("Question Database")
                            val Questions=Questions(category,subcategory,question,option1, option2, option3, option4, CorrectOption)
                                database.child(question).setValue(Questions).addOnSuccessListener {
                                binding.outerSpinner.clearFocus()
                                binding.innerSpinner.clearFocus()

                                binding.questionTxt.text?.clear()
                                binding.option1Txt.text?.clear()
                                binding.option2Txt.text?.clear()
                                binding.option3Txt.text?.clear()
                                binding.option4Txt.text?.clear()
                                binding.CorrectOptionTxt.text?.clear()


                                Toast.makeText(this@NewQuestion, "Successfully Saved", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {
                                Toast.makeText(this@NewQuestion,"Failed",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    5 -> {
                        val innerAdapter6 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray6)
                        innerAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter6

                        val innerAdapter2 = ArrayAdapter(this@NewQuestion, android.R.layout.simple_spinner_item, innerSpinnerArray6)
                        innerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        innerSpinner.adapter = innerAdapter2
                        binding.submitQuestion.setOnClickListener {
                            val category = parent.getItemAtPosition(position).toString()
//                            val subcategory = parent.getItemAtPosition(position).toString()


                            val subcategory = innerSpinner.selectedItem.toString()


                            val question=binding.questionTxt.text.toString()
                            val option1=binding.option1Txt.text.toString()
                            val option2=binding.option2Txt.text.toString()
                            val option3=binding.option3Txt.text.toString()
                            val option4=binding.option4Txt.text.toString()
                            val CorrectOption=binding.CorrectOptionTxt.text.toString()




                            database=FirebaseDatabase.getInstance().getReference("Question Database")
                            val Questions=Questions(category,subcategory,question,option1, option2, option3, option4, CorrectOption)
                            database.child(question).setValue(Questions).addOnSuccessListener {
                                binding.outerSpinner.clearFocus()
                                binding.innerSpinner.clearFocus()

                                binding.questionTxt.text?.clear()
                                binding.option1Txt.text?.clear()
                                binding.option2Txt.text?.clear()
                                binding.option3Txt.text?.clear()
                                binding.option4Txt.text?.clear()
                                binding.CorrectOptionTxt.text?.clear()


                                Toast.makeText(this@NewQuestion, "Successfully Saved", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {
                                Toast.makeText(this@NewQuestion,"Failed",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


//        binding.submitQuestion.setOnClickListener {
//            val question=binding.questionTxt.text.toString()
//            val option1=binding.option1Txt.text.toString()
//            val option2=binding.option2Txt.text.toString()
//            val option3=binding.option3Txt.text.toString()
//            val option4=binding.option4Txt.text.toString()
//            val CorrectOption=binding.CorrectOptionTxt.text.toString()
//
//            database=FirebaseDatabase.getInstance().getReference("Question Database")
//            val Questions=Questions(question,option1, option2, option3, option4, CorrectOption)
//            database.child(question).setValue(Questions).addOnSuccessListener {
//                binding.questionTxt.text?.clear()
//                binding.option1Txt.text?.clear()
//                binding.option2Txt.text?.clear()
//                binding.option3Txt.text?.clear()
//                binding.option4Txt.text?.clear()
//                binding.CorrectOptionTxt.text?.clear()
//
//                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
//
//            }.addOnFailureListener {
//                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
//            }

//        }

//        val category = resources.getStringArray(R.array.category)
//        val categoryAdapter = ArrayAdapter(this, R.layout.dropdown_item, category)
//        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
//        autocompleteTV.setAdapter(categoryAdapter)
//
//        val subcategory = resources.getStringArray(R.array.subcategory)
//        val subcategoryAdapter = ArrayAdapter(this, R.layout.dropdown_item, subcategory)
//        val autocompleteTV1 = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView1)
//        autocompleteTV1.setAdapter(subcategoryAdapter)
    }

}