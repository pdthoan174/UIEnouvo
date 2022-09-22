package com.example.uienouvo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uienouvo.R
import com.example.uienouvo.adapter.ApprovalAdapter
import com.example.uienouvo.model.ItemApprovalMatrix
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity() : AppCompatActivity() {

    var mListApprovalMatrix = ArrayList<ItemApprovalMatrix>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add_new_matrix.setOnClickListener {
            showActivityAddApproval()
        }

        setRecyclerView()
        initData()
    }

    private fun initData() {
        mListApprovalMatrix.add(ItemApprovalMatrix(
            1,
            "Default hihi ",
            "Default",
            0,
            50000,
            1,
            "GROUPMG1"
        ))

        mListApprovalMatrix.add(ItemApprovalMatrix(
            1,
            "Default",
            "Default",
            0,
            50000,
            1,
            "GROUPMG1"
        ))

        mListApprovalMatrix.add(ItemApprovalMatrix(
            1,
            "Default",
            "Default",
            0,
            50000,
            1,
            "GROUPMG1"
        ))
    }

    private fun setRecyclerView() {
        val adapter = ApprovalAdapter(mListApprovalMatrix)
        recyclerviewApproval.adapter = adapter
        recyclerviewApproval.setHasFixedSize(true)
    }


    private fun showActivityAddApproval() {
        startActivity(Intent(this, addApprovalMatrix::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}