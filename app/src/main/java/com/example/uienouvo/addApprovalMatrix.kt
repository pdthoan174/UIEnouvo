package com.example.uienouvo

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_add_approval_matrix.*
import kotlinx.android.synthetic.main.bottom_sheet_feature.*
import kotlinx.android.synthetic.main.confirm_add_dialog.view.*

class addApprovalMatrix : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_approval_matrix)

        val bottomSheet:EditText = findViewById(R.id.text_select_feature)
        bottomSheet.setOnClickListener {
            showBottomSheet()
        }

        btn_add.setOnClickListener {
            showConfirmDialog()
        }

    }

    private fun showConfirmDialog() {
        val view = View.inflate(this@addApprovalMatrix, R.layout.confirm_add_dialog, null)



        val builder = AlertDialog.Builder(this@addApprovalMatrix)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        view.btn_cancel.setOnClickListener {
            Toast.makeText(this@addApprovalMatrix,"Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        view.btn_confirm.setOnClickListener {
            Toast.makeText(this@addApprovalMatrix,"Confirm", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }
    }

    private fun showBottomSheet() {
        val bottomSheetDialog:Dialog = Dialog(this)
        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_feature)

        bottomSheetDialog.checkBox1.setOnClickListener {
            Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show()
        }

        bottomSheetDialog.cancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }


        bottomSheetDialog.show()
        bottomSheetDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        bottomSheetDialog.window?.setGravity(Gravity.BOTTOM)


    }


}