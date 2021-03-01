package ddd.leave.domain.leave.entity.valueobject;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.domain.person.repository.po.PersonPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 审批人
 * 值对象
 * 1. Approver 数据来源于 person 聚合，从 person 聚合获取审批人返回后，从 person 实体获取 personID、personName 和 level 等属性，重新组合为 approver 值对象，因此需要数据转换和重新赋值。
 * 2. Approver 值对象同时被聚合根 leave 和实体 approvalInfo 引用。这类值对象的数据来源于其它聚合，不可修改，可重复使用。
 *
 * 将这种对象设计为值对象而不是实体，可以提高系统性能，降低数据库实体关联的复杂度，所以一般建议优先设计为值对象。
 * 值对象只做整体替换、不可修改的特性，在值对象中基本不会有修改或新增的方法。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Approver {

    String personId;
    String personName;
    int level;

    public static Approver fromPerson(Person person){
        Approver approver = new Approver();
        approver.setPersonId(person.getPersonId());
        approver.setPersonName(person.getPersonName());
        approver.setLevel(person.getRoleLevel());
        return approver;
    }

}
