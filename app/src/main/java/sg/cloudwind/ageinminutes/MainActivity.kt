package sg.cloudwind.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        var dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val theDate = sdf.parse(selectedDate) // in mini seconds
                val selectedDateInMinutes =theDate.time/60000 //convert to minutes
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time/60000
                val diffInMinutes = currentDateInMinutes - selectedDateInMinutes
                var tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.text = diffInMinutes.toString()

            }, year, month, day)
        // 1 day = 86400000 milliseconds
        dpd.datePicker.setMaxDate(Date().time + 2*86400000  );
        dpd.show()
        
        // brute force implementation
        // month starts from 0, Jan month = 0
//        DatePickerDialog(this,
//            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
//
//                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
//                Toast.makeText(this,"chosen year is $selectedYear, " +
//                        "the month is $selectedMonth, the day is $selectedDayOfMonth ",
//                    Toast.LENGTH_LONG).show()
//                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
//                //tvSelectedDate.setText(selectedDate)
//                var theDate = sdf.parse(selectedDate)
//                tvSelectedDate.text = selectedDate
//
//                var diffInYearToDays: Int
//                var diffInMonthToDays: Int
//                var diffInDays: Int
//                var sumOfDays: Int
//                var sumOfDaysInMinutes: Int
//
//
//
//                if(year == selectedYear && month == selectedMonth && selectedDayOfMonth> day){
//                    Toast.makeText(this,
//                        "please select a valid date", Toast.LENGTH_SHORT).show()
//                    tvSelectedDateInMinutes.text = "invalid date selected"
//                }else{
//                    if(year == selectedYear){
//                        if(month == selectedMonth){
//                            sumOfDaysInMinutes = dayToMinute(day - selectedDayOfMonth)
//                        }
//                        else{
//                            // if month is not the same but year same
//                            diffInMonthToDays = monthToDay(month - selectedMonth-1)
//                            diffInDays = 30 + day - selectedDayOfMonth
//                            sumOfDaysInMinutes = dayToMinute(diffInMonthToDays + diffInDays)
//                        }
//                    }else{
//                        diffInYearToDays = yearToDay(year - selectedYear -1)
//                        diffInMonthToDays = monthToDay(month - selectedMonth + 12)
//                        diffInDays = day - selectedDayOfMonth
//                        sumOfDays = diffInYearToDays + diffInMonthToDays + diffInDays
//                        sumOfDaysInMinutes = dayToMinute(sumOfDays)
//                    }
//                    tvSelectedDateInMinutes.text = sumOfDaysInMinutes.toString()
//                }
//
//            }
//                ,year, month, day).show()
    }
    fun yearToDay(year: Int): Int{
        return year*365
    }
    fun monthToDay(month: Int): Int{
        return month*30
    }
    fun dayToMinute(day: Int): Int{
        return day*24*60
    }
}