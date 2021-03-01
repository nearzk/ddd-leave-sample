package ddd.leave.domain.leave.entity;

import ddd.leave.domain.leave.entity.valueobject.ApprovalType;
import ddd.leave.domain.leave.entity.valueobject.Approver;
import lombok.Data;

/**
 * 审批意见
 *
 * 实体，用于记录审批意见，有自己的属性和值对象
 */
@Data
public class ApprovalInfo {

    String approvalInfoId;
    Approver approver;
    ApprovalType approvalType;
    String msg;
    long time;

}
