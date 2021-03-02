package ddd.leave.domain.leave.entity;

import ddd.leave.domain.leave.entity.valueobject.Applicant;
import ddd.leave.domain.leave.entity.valueobject.Approver;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.leave.entity.valueobject.Status;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请假单信息，其实是领域对象（DO：Domain Object）
 *
 * 聚合根 leave 中有属性、值对象、关联实体和自身的业务行为。
 * Leave 实体采用充血模型，有自己的业务行为，具体就是聚合根实体类的方法，如代码中的 getDuration 和 addHistoryApprovalInfo 等方法。
 * 聚合根引用实体和值对象，它可以组合聚合内的多个实体，在聚合根实体类方法中完成复杂的业务行为，这种复杂的业务行为也可以在聚合领域服务里实现。
 * 但为了职责和边界清晰，我建议聚合要根据自身的业务行为在实体类方法中实现，而涉及多个实体组合才能实现的业务能力由领域服务完成。
 */
@Data
public class Leave {

    String id;
    /**
     * 申请者，值对象
     */
    Applicant applicant;
    /**
     * 审批者，值对象
     */
    Approver approver;
    /**
     * 请假类型
     */
    LeaveType type;
    /**
     * 请假状态
     */
    Status status;
    Date startTime;
    Date endTime;
    long duration;
    /**
     * 审批领导的最大级别
     */
    int leaderMaxLevel;
    /**
     * 当前审批信息
     */
    ApprovalInfo currentApprovalInfo;
    /**
     * 历史审批信息
     */
    List<ApprovalInfo> historyApprovalInfos;

    public long getDuration() {
        return endTime.getTime() - startTime.getTime();
    }

    public Leave addHistoryApprovalInfo(ApprovalInfo approvalInfo) {
        if (null == historyApprovalInfos)
            historyApprovalInfos = new ArrayList<>();
        this.historyApprovalInfos.add(approvalInfo);
        return this;
    }

    public Leave create(){
        this.setStatus(Status.APPROVING);
        this.setStartTime(new Date());
        return this;
    }

    public Leave agree(Approver nextApprover){
        this.setStatus(Status.APPROVING);
        this.setApprover(nextApprover);
        return this;
    }

    public Leave reject(Approver approver){
        this.setApprover(approver);
        this.setStatus(Status.REJECTED);
        this.setApprover(null);
        return this;
    }

    public Leave finish(){
        this.setApprover(null);
        this.setStatus(Status.APPROVED);
        this.setEndTime(new Date());
        this.setDuration(this.getEndTime().getTime() - this.getStartTime().getTime());
        return this;
    }
}
