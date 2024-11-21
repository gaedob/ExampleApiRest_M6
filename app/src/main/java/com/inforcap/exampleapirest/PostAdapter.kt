package com.inforcap.exampleapirest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.inforcap.exampleapirest.databinding.PostItemBinding

class PostAdapter(private var postList: List<PostEntity>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var binding: PostItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(postList[position])
    }

    inner class PostViewHolder(binding: PostItemBinding) : ViewHolder(binding.root) {
        fun onBind(post: PostEntity){
            binding.run {
                tvId.text = post.id.toString()
                tvTitle.text = post.title
                tvBody.text = post.body
            }
        }

    }





}