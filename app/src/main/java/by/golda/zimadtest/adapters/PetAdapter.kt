package by.golda.zimadtest.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.golda.zimadtest.R
import by.golda.zimadtest.model.PetModel
import by.golda.zimadtest.model.Data
import com.squareup.picasso.Picasso

class PetAdapter(private val context: Context?) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {

    var list: MutableList<Data> = mutableListOf()
    private var listener: onPetSelectedListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    fun setObject(cats: PetModel) {
        list.clear()
        list.addAll(cats.data)
        notifyDataSetChanged()
    }

    fun setListener(listener: onPetSelectedListener) {
        this.listener =  listener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text1.text = list[position].title
        holder.text2.text = list[position].url
        Picasso.with(context)
            .load(list[position].url)
            .error(R.drawable.ic_android)
            .into(holder.icon)

        holder.itemView.setOnClickListener{ listener?.select(list[position].title, list[position].url) }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text1 = view.findViewById(R.id.tv_text1) as TextView
        var text2 = view.findViewById(R.id.tv_text2) as TextView
        var icon = view.findViewById(R.id.iv_icon) as ImageView
    }

    interface onPetSelectedListener {
        fun select(title: String, urlIcon: String)
    }

}

