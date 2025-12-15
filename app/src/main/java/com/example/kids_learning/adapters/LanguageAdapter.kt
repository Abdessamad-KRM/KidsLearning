package com.example.kids_learning.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kids_learning.R

data class LanguageItem(
    val langue: String,
    val image: Int,
    val color: Int
)

class LangueAdapter(val context: Context,val items: List<LanguageItem>): BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)

        val item = items[position]

        val langue = view.findViewById<TextView>(R.id.langue)
        val image = view.findViewById<ImageView>(R.id.image)
        val cardView = view.findViewById<androidx.cardview.widget.CardView>(R.id.cardView)

        langue.text = item.langue
        image.setImageResource(item.image)
        cardView.setCardBackgroundColor(context.getColor(item.color))

        return view
    }
}
