package com.example.kids_learning.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kids_learning.R
import com.example.kids_learning.data.Letter
import com.example.kids_learning.data.LetterStatus

class LetterAdapter(
    private val letters: List<Letter>,
    private val onLetterClick: (Letter) -> Unit
) : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    class LetterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardLetter)
        val imageLetter: ImageView = view.findViewById(R.id.imageLetter)
        val textLetter: TextView = view.findViewById(R.id.textLetter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_letter, parent, false)
        return LetterViewHolder(view)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val letter = letters[position]
        
        // Charger l'image PNG ou afficher le caractère
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(letter.imageResource, "drawable", context.packageName)
        if (resourceId != 0) {
            holder.imageLetter.setImageResource(resourceId)
            holder.imageLetter.visibility = View.VISIBLE
            holder.textLetter.visibility = View.GONE
        } else {
            // Si l'image n'existe pas, afficher le caractère
            holder.imageLetter.visibility = View.GONE
            holder.textLetter.visibility = View.VISIBLE
            holder.textLetter.text = letter.character
        }

        // Couleur selon le statut
        val backgroundColor = when (letter.status) {
            LetterStatus.COMPLETED -> ContextCompat.getColor(context, R.color.completed_letter)
            LetterStatus.FAILED -> ContextCompat.getColor(context, R.color.failed_letter)
            LetterStatus.LOCKED -> ContextCompat.getColor(context, R.color.locked_letter)
        }
        
        holder.cardView.setCardBackgroundColor(backgroundColor)
        holder.cardView.setOnClickListener { onLetterClick(letter) }
    }

    override fun getItemCount() = letters.size
}