package ddd.leave.domain.leave.event;

import com.alibaba.fastjson.JSON;
import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.infrastructure.common.event.DomainEvent;
import ddd.leave.infrastructure.util.IdGenerator;
import lombok.Data;

import java.util.Date;

/**
 * 请假事件实体
 */
@Data
public class LeaveEvent extends DomainEvent {

    LeaveEventType leaveEventType;

    public static LeaveEvent create(LeaveEventType eventType, Leave leave){
        LeaveEvent event = new LeaveEvent();
        event.setId(IdGenerator.nextId());
        event.setLeaveEventType(eventType);
        event.setTimestamp(new Date());
        event.setData(JSON.toJSONString(leave));
        return event;
    }
}
