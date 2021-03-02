package ddd.leave.domain.leave.repository.po;

import ddd.leave.domain.leave.event.LeaveEventType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 领域事件的持久化对象
 * 
 * 领域事件和持久化对象的转换，在Factory内完成
 */
@Data
@Entity
public class LeaveEventPO {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    int id;
    @Enumerated(EnumType.STRING)
    LeaveEventType leaveEventType;
    Date timestamp;
    String source;
    String data;
}
