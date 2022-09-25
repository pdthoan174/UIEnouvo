package com.example.uienouvo.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.example.uienouvo.activity.addApprovalMatrix
import com.example.uienouvo.model.ItemApprovalMatrix
import kotlinx.android.synthetic.main.confirm_delete_dialog.view.*
import kotlinx.android.synthetic.main.list_approval_matrix.view.*
import kotlinx.android.synthetic.main.sign_in_failed_dialog.view.*

class ApprovalAdapter(private val mListItemApprovalMatrix:MutableList<ItemApprovalMatrix>):
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

        holder.layoutExpandable.setOnLongClickListener {
            showDeleteDialog(holder.adapterPosition, approval.approvalMatrixName, position)
            true
        }

        holder.layoutExpandable.setOnClickListener {

//            mListItemApprovalMatrix.set(holder.adapterPosition,ItemApprovalMatrix(
//                "hihi",
//                "alo",
//                4,
//                5,
//                1,
//                "sadfasdf"
//            ))
            notifyItemChanged(position)
            showActivityAddApproval(mListItemApprovalMatrix, holder.adapterPosition, position)
        }



        holder.layoutFeature.setOnClickListener {
            val approval = mListItemApprovalMatrix[position]
            approval.expandable = !approval.expandable
            notifyItemChanged(position)
            Toast.makeText(mContext, approval.expandable.toString(), Toast.LENGTH_SHORT).show()
        }

        Log.i("hihi", "onBindViewHolder invoked: "+position)

    }

    override fun getItemCount(): Int {
        return mListItemApprovalMatrix.size
    }

    private fun showActivityAddApproval(approval: MutableList<ItemApprovalMatrix>, adapterPosition: Int, position: Int) {
        val intent = Intent(mContext, addApprovalMatrix::class.java)
        val bundle = Bundle()

        bundle.putString("matrixName", approval[position].approvalMatrixName)
        bundle.putString("featureName", approval[position].feature)
        bundle.putString("minimum", approval[position].rangMinimum.toString())
        bundle.putString("maximum", approval[position].rangMaximum.toString())
        bundle.putString("numOfApproval", approval[position].numberOfApproval.toString())
        bundle.putString("inputApprover", approval[position].approver)
//        bundle.putString("inputApprover", approval[position].approvalMatrixName)

        bundle.putInt("adapterPosition", adapterPosition)
        intent.putExtras(bundle)
        mContext.startActivity(intent)

        approval.removeAt(position)
    }

    private fun showDeleteDialog(adapterPosition: Int, matrixName: String, position: Int) {
        val view = View.inflate(mContext, R.layout.confirm_delete_dialog, null)
        val builder = AlertDialog.Builder(mContext)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        view.btn_confirm.setOnClickListener {
            mListItemApprovalMatrix.removeAt(adapterPosition)
            Toast.makeText(mContext, "Delete "+matrixName, Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            notifyItemChanged(position)

        }

        view.btn_cancel.setOnClickListener {
            Toast.makeText(mContext,"Back", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

}