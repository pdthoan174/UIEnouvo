package com.example.uienouvo.model

class ItemApprovalMatrix(
    val approvalMatrixName:String,
    val feature: String,
    val rangMinimum: Int,
    val rangMaximum: Int,
    val numberOfApproval: Int,
    val approver:String,

    var expandable: Boolean = false
) {
}