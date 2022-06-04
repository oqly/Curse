package com.example.testapi

import android.R
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class Service {
    fun jsonParse(): Array<String?> {
        var requestQueue: RequestQueue? = null
        requestQueue = Volley.newRequestQueue(this)
        val url = "https://www.arbeitnow.com/api/job-board-api"
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            val jsonArray = response.getJSONArray("data")
            val listItems = arrayOfNulls<String>(jsonArray.length())
            for (i in 0 until jsonArray.length()) {
                val work = jsonArray.getJSONObject(i)
                val title = work.getString("title")
                val location = work.getString("location")
                listItems[i] = "$title\n\nLocation: $location"
            }
            return listItems

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, { error -> error.printStackTrace() })
        requestQueue.add(request)
    }
}