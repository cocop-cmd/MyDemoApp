package com.example.mydemoapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mydemoapp.R
import com.example.mydemoapp.database.PostEntityItem


class ListAdapter(
        context: Context,
        private val objects: List<PostEntityItem>
) : ArrayAdapter<PostEntityItem>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItemView = convertView
                ?: LayoutInflater.from(context).inflate(
                    R.layout.list_item, parent, false)

        val post: PostEntityItem? = getItem(position)

        val titleView = listItemView!!.findViewById<TextView>(R.id.title)
        titleView.text = post?.link

        val descriptionView = listItemView.findViewById<TextView>(R.id.description)
        descriptionView.text = post?.description

        val posterView = listItemView.findViewById<TextView>(R.id.poster)
        posterView.text = post?.username

        return listItemView
    }

    override fun getItemId(position: Int): Long {
        return objects[position].id ?: 0
    }
}