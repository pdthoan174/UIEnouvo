package com.example.uienouvo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uienouvo.R
import com.example.uienouvo.model.ItemApprovalMatrix
import kotlinx.android.synthetic.main.list_approval_matrix.view.*

class ApprovalAdapter(private val mListItemApprovalMatrix:List<ItemApprovalMatrix>):
    RecyclerView.Adapter<ApprovalAdapter.ApprovalHolder>() {

    private lateinit var mContext: Context


    class ApprovalHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val matrixName:TextView = itemView.matrixName
        val featureName:TextView = itemView.textFeature
        val min:TextView = itemView.textRangMinimum
        val max:TextView = itemView.textRangMaximum
        val numberOfApproval:TextView = itemView.textNumAppropval
        val approver:TextView = itemView.textApprover

        val layoutFeature:LinearLayout = itemView.layoutFeatures
        val layoutExpandable:LinearLayout = itemView.expandable_list_approval

        val arrowFeatures:ImageView = itemView.arrowFeature

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApprovalHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_approval_matrix, parent, false)
        mContext = parent.context
        view.arrowFeature.setImageResource(R.drawable.ic_arrow_down)
        return ApprovalHolder(view)
    }

    override fun onBindViewHolder(holder: ApprovalHolder, position: Int) {
        val approval: ItemApprovalMatrix = mListItemApprovalMatrix[position]

        holder.matrixName.text = approval.approvalMatrixName
        holder.featureName.text = approval.feature
        holder.min.text = approval.rangMinimum.toString()
        holder.max.text = approval.rangMaximum.toString()
        holder.numberOfApproval.text = approval.numberOfApproval.toString()
        holder.approver.text = approval.approver

        val isExpandable:Boolean = mListItemApprovalMatrix[position].expandable
        holder.layoutExpandable.visibility = if (isExpandable) View.VISIBLE else View.GONE

        // thay doi huong arrow khi click
        if (isExpandable){
            holder.arrowFeatures.setImageResource(R.drawable.ic_arrow_up)
        }else{
            holder.arrowFeatures.setImageResource(R.drawable.ic_arrow_down)
        }

        holder.layoutFeature.setOnClickListener {
            val approval = mListItemApprovalMatrix[position]
            approval.expandable = !approval.expandable

            notifyItemChanged(position)

//            holder.layoutExpandable.visibility = View.VISIBLE
            Toast.makeText(mContext, approval.expandable.toString(), Toast.LENGTH_SHORT).show()
        }

        Log.i("hihi", "onBindViewHolder invoked: "+position)

    }

    override fun getItemCount(): Int {
        return mListItemApprovalMatrix.size
    }

}