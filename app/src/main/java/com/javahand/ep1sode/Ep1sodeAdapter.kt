package com.javahand.ep1sode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javahand.ep1sode.room.Ep1sodeEntity

class Ep1sodeAdapter :
    ListAdapter<Ep1sodeEntity, Ep1sodeAdapter.Ep1sodeViewHolder>(
        Ep1sodeDiffCallback
    )
{ // class Ep1sodeAdapter
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = Ep1sodeViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ep1sode, parent, false)
    ) // fun onCreateViewHolder( ViewGroup, Int )

    override fun onBindViewHolder(holder: Ep1sodeViewHolder, position: Int)
    {
        holder.bind( getItem( position ))
    } // fun onBindViewHolder( Ep1sodeViewHolder, Int )

    override fun getItemId(position: Int): Long
    {
        return getItem( position ).id
    } // fun getItemId( Int )

    class Ep1sodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val textChannelNumber =
            itemView.findViewById<TextView>(R.id.text_channel_number)
        private val textChannelName =
            itemView.findViewById<TextView>(R.id.text_channel_name)
        private val imageRating =
            itemView.findViewById<ImageView>(R.id.image_rating)
        private val textProgramTitle =
            itemView.findViewById<TextView>(R.id.text_program_title)
        private val textBroadcastPeriod =
            itemView.findViewById<TextView>(R.id.text_broadcast_period)
        private val textReplayPeriod =
            itemView.findViewById<TextView>(R.id.text_replay_periods)

        fun bind( ep1sodeEntity: Ep1sodeEntity )
        {
            textChannelNumber.text = ep1sodeEntity.channelNumber.toString()
            textChannelName.text = ep1sodeEntity.channelName

            imageRating.setImageLevel( ep1sodeEntity.rating )

            textProgramTitle.text = ep1sodeEntity.title
            textBroadcastPeriod.text = ep1sodeEntity.broadcastPeriod
            textReplayPeriod.text = ep1sodeEntity.replayPeriods
        } // fun bind( Ep1sodeEntity )
    } // class Ep1soeViewHolder( View )

    object Ep1sodeDiffCallback : DiffUtil.ItemCallback<Ep1sodeEntity>()
    {
        override fun areItemsTheSame(
            oldItem: Ep1sodeEntity,
            newItem: Ep1sodeEntity
        ) = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: Ep1sodeEntity,
            newItem: Ep1sodeEntity
        ) = oldItem.id == newItem.id
    } // object Ep1sodeDiffCallback
}