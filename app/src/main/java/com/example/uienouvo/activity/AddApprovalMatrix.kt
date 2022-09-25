package com.example.uienouvo.activity

import android.annotation.SuppressLint
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
import com.example.uienouvo.model.ItemApprovalMatrix
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
import kotlinx.android.synthetic.main.sign_in_failed_dialog.view.*

class addApprovalMatrix : AppCompatActivity() {

    private lateinit var bottomSheetFeature:EditText
    private lateinit var bottomSheetApprover:EditText

    private var matrixName = ""
    private var featureName = ""
    private var minimum = ""
    private var maximum = ""
    private var numOfApproval = ""
    private var inputApprover = ""

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

            if (validateApproval()){
                showConfirmDialog()
            }else{
                showFailedDialog()
            }
        }

        btnReset.setOnClickListener {
            inputMatrixName.setText("")
            text_select_feature.setText("")
            text_select_approver.setText("")
            inputMinimum.setText("")
            inputMaximum.setText("")
            inputNumOfAppropval.setText("")
        }

        btn_back.setOnClickListener {
            backToMainActivity(false)
        }

        val intent = intent
        val bundle = intent.extras
        if (bundle!= null) {
            getDataToUpdate()
        }

    }

    private fun validateApproval(): Boolean {

        matrixName = inputMatrixName.text.toString()
        featureName = text_select_feature.text.toString()
        minimum = inputMinimum.text.toString()
        maximum = inputMaximum.text.toString()
        numOfApproval = inputNumOfAppropval.text.toString()
        inputApprover = text_select_approver.text.toString()

        var validateResult: Boolean

        if (matrixName.isEmpty()){
            errorMessageName.text = "Enter matrix name"
            validateResult = false
        }else{
            errorMessageName.text = ""
            validateResult = true
        }
        if (featureName.equals("Select a feature") || featureName.equals("")){
            errorMessageFeature.text = "Select a feature"
            validateResult = false
        }else{
            errorMessageFeature.text = ""
            validateResult = true

        }
        if (minimum.isEmpty()){
            errorMessageMin.text = "Enter minimum value"
            validateResult = false
        }else if (minimum.toInt() >= maximum.toInt()){
            errorMessageMin.text = "Minimum value must be less than maximum value"
            validateResult = false
        }else{
            errorMessageMin.text = ""
            validateResult = true

        }
        if (maximum.isEmpty()){
            errorMessageMax.text = "Enter maximum value"
            validateResult = false
        }else if (maximum.toInt() <= minimum.toInt()){
            errorMessageMax.text = "Maximum value must be greater than minimum value"
            validateResult = false
        }else{
            errorMessageMax.text = ""
            validateResult = true

        }

        if (numOfApproval.isEmpty()){
            errorMessageNumApproval.text = "Enter number of approval"
            validateResult = false
        }else if (numOfApproval.toInt() <= 0){
            errorMessageNumApproval.text = "Number of approval must be greater than 0"
            validateResult = false
        }else{
            errorMessageNumApproval.text = ""
            validateResult = true

        }
        if (inputApprover == ""){
            errorMessageApprover.text = "Select approver"
            validateResult = false
        }else{
            errorMessageApprover.text = ""
            validateResult = true

        }
        return validateResult

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
        backToMainActivity(false)
    }


    private fun backToMainActivity(updateList: Boolean) {
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putBoolean("updateList", updateList)
        bundle.putString("matrixName", matrixName)
        bundle.putString("featureName", featureName)
        bundle.putString("minimum", minimum)
        bundle.putString("maximum", maximum)
        bundle.putString("numOfApproval", numOfApproval)
        bundle.putString("inputApprover", inputApprover)
        intent.putExtras(bundle)

        startActivity(intent)
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
            dialog.dismiss()

            backToMainActivity(true)
        }
    }

    private fun showFailedDialog() {
        val view = View.inflate(this@addApprovalMatrix, R.layout.sign_in_failed_dialog, null)
        val builder = AlertDialog.Builder(this@addApprovalMatrix)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        view.backToEdit.setOnClickListener {
            Toast.makeText(this@addApprovalMatrix,"Back", Toast.LENGTH_SHORT).show()
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
                updateFeature(bottomSheetDialog.textFeatureC.text.toString())
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


    private fun getDataToUpdate(){
        val intent = intent
        val bundle = intent.extras
        if (bundle!= null) {
            textTitle.text = "Update Approval Matrix"
            btn_add.text = "Update Approval"

            inputMatrixName.setText(bundle.getString("matrixName",""))
            text_select_feature.setText(bundle.getString("featureName",""))
            inputMinimum.setText(bundle.getString("minimum",""))
            inputMaximum.setText(bundle.getString("maximum",""))
            inputNumOfAppropval.setText(bundle.getString("numOfApproval",""))
            text_select_approver.setText(bundle.getString("inputApprover",""))
        }
    }

}