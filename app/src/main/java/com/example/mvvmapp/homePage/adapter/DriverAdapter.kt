package com.example.mvvmapp.homePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.databinding.ItemRecBinding
import com.example.mvvmapp.homePage.model.Model
import kotlin.math.sign

class DriverAdapter(val item : List<Model>) : RecyclerView.Adapter<DriverAdapter.ItemHolder>() {

    class ItemHolder(val binding : ItemRecBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemRecBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       val items = item[position]
        holder.binding.name.text = items.name
    }

    override fun getItemCount(): Int {
        return item.size
    }
}