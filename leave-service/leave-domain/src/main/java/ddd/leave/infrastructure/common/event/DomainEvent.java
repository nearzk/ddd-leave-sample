package ddd.leave.infrastructure.common.event;

import lombok.Data;

import java.util.Date;

/**
 * 领域事件定义基类
 *
 * 领域事件的执行逻辑如下：
 * 1. 执行业务逻辑，产生领域事件
 * 2. 完成业务数据持久化
 * 3. 完成事件数据持久化
 * 4. 完成领域事件发布
 */
@Data
public class DomainEvent {

    /**
     * 事件ID
     */
    String id;
    /**
     * 时间戳
     */
    Date timestamp;
    /**
     * 时间源
     */
    String source;
    /**
     * 事件相关的业务数据，可以是XML或JSON格式
     */
    String data;
}
