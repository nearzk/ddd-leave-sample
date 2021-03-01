package ddd.leave.domain.leave.repository.facade;

import ddd.leave.domain.leave.repository.po.LeaveEventPO;
import ddd.leave.domain.leave.repository.po.LeavePO;

import java.util.List;

/**
 * 请假仓储接口
 *
 * 面向领域服务提供接口，一个聚合一个仓储，实现聚合数据的持久化。
 * 领域服务通过仓储接口来访问基础资源，由仓储实现完成数据持久化和初始化。
 *
 * 仓储一般包含：仓储接口和仓储实现
 */
public interface LeaveRepositoryInterface {

    void save(LeavePO leavePO);

    void saveEvent(LeaveEventPO leaveEventPO);

    LeavePO findById(String id);

    List<LeavePO> queryByApplicantId(String applicantId);

    List<LeavePO> queryByApproverId(String approverId);

}
