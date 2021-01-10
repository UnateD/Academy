package com.unated.academy.presentation.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unated.academy.R
import com.unated.academy.data.Actor
import com.unated.academy.data.Configuration

class ActorsAdapter(var configuration: Configuration, var actors: ArrayList<Actor>) :
    RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_actors_list, parent, false)
        )
    }

    override fun getItemCount(): Int = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) =
        holder.bind(actors[position])

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivActorAvatar: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
        private val tvActorName: TextView = itemView.findViewById(R.id.tv_actor_name)

        fun bind(actor: Actor) {
            Glide.with(ivActorAvatar).load("${configuration.images.base_url}/${configuration.images.backdrop_sizes.last()}/${actor.picture}").into(ivActorAvatar)
            tvActorName.text = actor.name
        }
    }
}