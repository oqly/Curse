package com.example.curse

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException

class Parser(var id: String?, var activity: ListActivity) {
    var listItems = arrayOfNulls<String>(12)
    fun parse() {
        val url = "http://192.168.1.87:5000/faculty?id=$id"
        Log.d("TAG", url)
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            Log.d("TAG", "Data")
            val jsonArray = response.getJSONArray("data")
            //listItems = arrayOfNulls(12)
            Log.d("TAG", jsonArray.length().toString())

            val arrayItems = mutableListOf<Pair<String, Double>>()

            for (i in 0..12) {
                val faculty = jsonArray.getJSONObject(jsonArray.length()-i-1)
                val title = faculty.getString("faculty")
                if (title == "None") { continue }

                val similarity = faculty.getString("similarity")
                val percent = similarity.toDouble() * 100
                arrayItems.addAll(mutableListOf(title to percent))
            }
            arrayItems.sortByDescending { it.second }
            for (i in 0..11){
                val title = arrayItems[i].first
                val percent = arrayItems[i].second
                listItems[i] = "\n$title\nСхожесть: " + String.format("%.3f", percent) + "%\n"
                Log.d("TAG", "\n$title\nСхожесть: $percent\n")
            }
            Log.d("TAG", "Data End")

            val adapter = ArrayAdapter(
                activity,
                android.R.layout.simple_list_item_1, listItems
            )
            activity.listView.adapter = adapter
            activity.progressBar.visibility = ProgressBar.INVISIBLE
            activity.textView.text = "Ваш результат:"
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, { error ->
            Log.d("TAG","response: ${error.message}") }) //error -> error.printStackTrace()

        val MY_SOCKET_TIMEOUT_MS = 30000
        val RetryP = DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        request.setRetryPolicy(RetryP)
        Log.d("TAG",request.retryPolicy.currentTimeout.toString())
        Log.d("TAG",request.retryPolicy.currentRetryCount.toString())

        activity.requestQueue?.add(request)
    }
}