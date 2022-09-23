package com.example.uienouvo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uienouvo.R
import com.example.uienouvo.adapter.ApprovalAdapter
import com.example.uienouvo.model.ItemApprovalMatrix
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

private var mListApprovalMatrix = mutableListOf<ItemApprovalMatrix>()


class MainActivity() : AppCompatActivity() {


    private var matrixName = ""
    private var featureName = ""
    private var minimum = ""
    private var maximum = ""
    private var numOfApproval = ""
    private var inputApprover = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add_new_matrix.setOnClickListener {
            showActivityAddApproval()
        }

        val intent = intent
        val bundle = intent.extras
        if (bundle?.getBoolean("updateList", false) == true) {
            initData()
        }

        setRecyclerView()

    }


    private fun initData() {
        val intent = intent
        val bundle = intent.extras
        if (bundle!= null) {
            matrixName = bundle.getString("matrixName","")
            featureName = bundle.getString("featureName","")
            minimum = bundle.getString("minimum","")
            maximum = bundle.getString("maximum","")
            numOfApproval = bundle.getString("numOfApproval","")
            inputApprover = bundle.getString("inputApprover","")

            mListApprovalMatrix.add(ItemApprovalMatrix(
                matrixName,
                featureName,
                minimum.toInt(),
                maximum.toInt(),
                numOfApproval.toInt(),
                inputApprover
            ))

//            mListApprovalMatrix.set()
        }
    }

    private fun setRecyclerView() {
        val adapter = ApprovalAdapter(mListApprovalMatrix)
        recyclerviewApproval.adapter = adapter
        recyclerviewApproval.setHasFixedSize(true)
    }


    private fun showActivityAddApproval() {
        val intent = Intent(this, addApprovalMatrix::class.java)

        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}