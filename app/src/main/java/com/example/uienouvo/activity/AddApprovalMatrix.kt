package com.example.uienouvo.activity

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import com.example.uienouvo.activity.MainActivity
import com.example.uienouvo.R
import kotlinx.android.synthetic.main.activity_add_approval_matrix.*
import kotlinx.android.synthetic.main.bottom_sheet_approver.*
import kotlinx.android.synthetic.main.bottom_sheet_feature.*
import kotlinx.android.synthetic.main.bottom_sheet_feature.cancel
import kotlinx.android.synthetic.main.bottom_sheet_feature.checkBox1
import kotlinx.android.synthetic.main.bottom_sheet_feature.checkBox2
import kotlinx.android.synthetic.main.bottom_sheet_feature.checkBox3
import kotlinx.android.synthetic.main.bottom_sheet_feature.checkBox4
import kotlinx.android.synthetic.main.bottom_sheet_feature.view.*
import kotlinx.android.synthetic.main.confirm_add_dialog.view.*
import kotlinx.android.synthetic.main.list_approval_matrix.*

class addApprovalMatrix : AppCompatActivity() {

    private lateinit var bottomSheetFeature:EditText
    private lateinit var bottomSheetApprover:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_approval_matrix)

        bottomSheetFeature = findViewById(R.id.text_select_feature)
        bottomSheetApprover = findViewById(R.id.text_select_approver)

        bottomSheetFeature.setOnClickListener {
            showBottomSheetFeature()
        }
        bottomSheetApprover.setOnClickListener {
            showBottomSheetApprover()
        }

        btn_add.setOnClickListener {
            validateApproval()
            showConfirmDialog()
        }

        btn_back.setOnClickListener {
            backToMainActivity()
        }

    }

    private fun validateApproval() {
        val matrixName = inputMatrixName.text.toString()
        val featureName = text_select_feature.text.toString()
        val minimum = inputMinimum.text.toString()
        val maximum = inputMaximum.text.toString()
        val numOfApproval = inputNumOfAppropval.text.toString()
        val inputApprover = text_select_approver.text.toString()

        if (matrixName.isEmpty()){
            errorMessageName.text = "Enter matrix name"
        }else{
            errorMessageName.text = ""
        }
        if (featureName.equals("Select a feature") || featureName.equals("")){
            errorMessageFeature.text = "Select a feature"
        }else{
            errorMessageFeature.text = ""
        }
        if (minimum.isEmpty()){
            errorMessageMin.text = "Enter minimum value"
        }else if (minimum.toInt() >= maximum.toInt()){
            errorMessageMin.text = "Minimum value must be less than maximum value"
        }else{
            errorMessageMin.text = ""
        }
        if (maximum.isEmpty()){
            errorMessageMax.text = "Enter maximum value"
        }else if (maximum.toInt() <= minimum.toInt()){
            errorMessageMax.text = "Maximum value must be greater than minimum value"
        }else{
            errorMessageMax.text = ""
        }

        if (numOfApproval.isEmpty()){
            errorMessageNumApproval.text = "Enter number of approval"
        }else if (numOfApproval.toInt() <= 0){
            errorMessageApprover.text = "Number of approval must be greater than 0"
        }else{
            errorMessageApprover.text = ""
        }
        if (inputApprover == ""){
            errorMessageApprover.text = "Select approver"
        }else{
            errorMessageApprover.text = ""
        }


        Log.i("hihi", matrixName+featureName+minimum+maximum+numOfApproval+inputApprover)
    }

    private fun showBottomSheetApprover() {
        val approver:MutableList<String> = mutableListOf()
        val bottomSheetDialogApprover:Dialog = Dialog(this)
        bottomSheetDialogApprover.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialogApprover.setContentView(R.layout.bottom_sheet_approver)

        bottomSheetDialogApprover.checkBox1.setOnClickListener {
            if (bottomSheetDialogApprover.checkBox1.isChecked) {
                approver.add(bottomSheetDialogApprover.textGroupmg1.text.toString())
                updateApprover(approver)
            }else {
                approver.remove(bottomSheetDialogApprover.textGroupmg1.text.toString())
                updateApprover(approver)
            }

        }
        bottomSheetDialogApprover.checkBox2.setOnClickListener {
            if (bottomSheetDialogApprover.checkBox2.isChecked) {
                approver.add(bottomSheetDialogApprover.textGroupmg2.text.toString())
                updateApprover(approver)
            }else {
                approver.remove(bottomSheetDialogApprover.textGroupmg2.text.toString())
                updateApprover(approver)
            }
        }
        bottomSheetDialogApprover.checkBox3.setOnClickListener {
            if (bottomSheetDialogApprover.checkBox3.isChecked) {

                approver.add(bottomSheetDialogApprover.textGroupmg3.text.toString())
                updateApprover(approver)

            }else {
                approver.remove(bottomSheetDialogApprover.textGroupmg3.text.toString())
                updateApprover(approver)

            }
        }
        bottomSheetDialogApprover.checkBox4.setOnClickListener {
            if (bottomSheetDialogApprover.checkBox4.isChecked) {

                approver.add(bottomSheetDialogApprover.textGroupcross.text.toString())
                updateApprover(approver)

            }else {
                approver.remove(bottomSheetDialogApprover.textGroupcross.text.toString())
                updateApprover(approver)

            }
        }

        bottomSheetDialogApprover.cancel.setOnClickListener {
            bottomSheetDialogApprover.dismiss()
            bottomSheetApprover.setText("")
            for (item in approver){
                bottomSheetApprover.append(item+", ")
            }
            Log.i("hihi", approver.toString())
        }

        bottomSheetDialogApprover.show()
        bottomSheetDialogApprover.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bottomSheetDialogApprover.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialogApprover.window?.attributes?.windowAnimations = R.style.DialogAnimation

        bottomSheetDialogApprover.window?.setGravity(Gravity.BOTTOM)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backToMainActivity()
    }


    private fun backToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        finishAffinity()

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

    private fun showBottomSheetFeature() {
        var boolean:Boolean
        val textFeature = "Select a feature"
        val bottomSheetDialog:Dialog = Dialog(this)
        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_feature)

        bottomSheetDialog.checkBox1.setOnClickListener {
            if (bottomSheetDialog.checkBox1.isChecked){
                boolean = false
                updateFeature(bottomSheetDialog.textDefault.text.toString())
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }else{
                boolean = true
                updateFeature(textFeature)
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }
        }

        bottomSheetDialog.checkBox2.setOnClickListener {
            if (bottomSheetDialog.checkBox2.isChecked){
                boolean = false
                updateFeature(bottomSheetDialog.textTranferOnline.text.toString())
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }else{
                boolean = true
                updateFeature(textFeature)
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }
        }
        bottomSheetDialog.checkBox3.setOnClickListener {
            if (bottomSheetDialog.checkBox3.isChecked){
                boolean = false
                updateFeature(bottomSheetDialog.textTranferOnline.text.toString())
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }else{
                boolean = true
                updateFeature(textFeature)
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }
        }
        bottomSheetDialog.checkBox4.setOnClickListener {
            if (bottomSheetDialog.checkBox4.isChecked){
                boolean = false
                updateFeature(bottomSheetDialog.textFeatureD.text.toString())
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }else{
                boolean = true
                updateFeature(textFeature)
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }
        }
        bottomSheetDialog.checkBox5.setOnClickListener {
            if (bottomSheetDialog.checkBox5.isChecked){
                boolean = false
                updateFeature(bottomSheetDialog.textFeatureE.text.toString())
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }else{
                boolean = true
                updateFeature(textFeature)
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox.isEnabled = boolean
            }
        }
        bottomSheetDialog.checkBox.setOnClickListener {
            if (bottomSheetDialog.checkBox.isChecked){
                boolean = false
                updateFeature(bottomSheetDialog.textMoreFeature.text.toString())

                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
            }else{
                boolean = true
                updateFeature(textFeature)
                bottomSheetDialog.checkBox1.isEnabled = boolean
                bottomSheetDialog.checkBox2.isEnabled = boolean
                bottomSheetDialog.checkBox3.isEnabled = boolean
                bottomSheetDialog.checkBox4.isEnabled = boolean
                bottomSheetDialog.checkBox5.isEnabled = boolean
            }
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


    private fun updateFeature(feature:String){
        bottomSheetFeature.setText(feature)
    }

    private fun updateApprover(approver: MutableList<String>) {
        bottomSheetApprover.setText("")
        for (item in approver){
            bottomSheetApprover.append(item+", ")
        }
    }

}