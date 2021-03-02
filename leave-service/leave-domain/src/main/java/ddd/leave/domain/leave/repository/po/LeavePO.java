package ddd.leave.domain.leave.repository.po;

import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.leave.entity.valueobject.Status;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * 请假实体，其实是持久化对象（PO：Persistent Object）
 */
@Entity
@Table(name = "leave")
@Data
public class LeavePO {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    String id;
    /**
     * Applicant值对象，把ID、Name和Type属性嵌入PO
     */
    String applicantId;
    String applicantName;
    @Enumerated(EnumType.STRING)
    PersonType applicantType;
    /**
     * Approver值对象，把ID和Name属性嵌入PO
     */
    String approverId;
    String approverName;
    @Enumerated(EnumType.STRING)
    LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    Status status;
    Date startTime;
    Date endTime;
    long duration;
    @Transient
    List<ApprovalInfoPO> historyApprovalInfoPOList;

    public Leave toLeave() {
        return new Leave();
    }

}
