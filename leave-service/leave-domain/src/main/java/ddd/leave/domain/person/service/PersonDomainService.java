package ddd.leave.domain.person.service;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.domain.person.entity.valueobject.PersonStatus;
import ddd.leave.domain.person.repository.facade.PersonRepository;
import ddd.leave.domain.person.repository.po.PersonPO;
import ddd.leave.domain.rule.service.ApprovalRuleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 人员领域服务
 *
 * 注意：尽量避免调用其他聚合的领域服务 或 引用其他聚合的实体或值对象，目的：解耦
 * 例子：findNextApprover(String currentApproverId...)
 */
@Service
@Slf4j
public class PersonDomainService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonFactory personFactory;


    public void create(Person person) {
        PersonPO personPO = personRepository.findById(person.getPersonId());
        if (null == personPO) {
            throw new RuntimeException("Person already exists");
        }
        person.create();
        personRepository.insert(personFactory.createPersonPO(person));
    }

    public void update(Person person) {
        person.setLastModifyTime(new Date());
        personRepository.update(personFactory.createPersonPO(person));
    }

    public void deleteById(String personId) {
        PersonPO personPO = personRepository.findById(personId);
        Person person = personFactory.getPerson(personPO);
        person.disable();
        personRepository.update(personFactory.createPersonPO(person));
    }

    public Person findById(String userId) {
        PersonPO personPO = personRepository.findById(userId);
        return personFactory.getPerson(personPO);
    }

    /**
     * find leader with applicant, if leader level bigger then leaderMaxLevel return null, else return Approver from leader;
     *
     * @param applicantId
     * @param leaderMaxLevel
     * @return
     */
    public Person findFirstApprover(String applicantId, int leaderMaxLevel) {
        PersonPO leaderPO = personRepository.findLeaderByPersonId(applicantId);
        if (leaderPO.getRoleLevel() > leaderMaxLevel) {
            return null;
        } else {
            return personFactory.createPerson(leaderPO);
        }
    }

    /**
     * find leader with current approver, if leader level bigger then leaderMaxLevel return null, else return Approver from leader;
     * 在应用服务组合不同聚合的领域服务时，我们可以通过 ID 或者参数来传数，如单一参数 currentApproverId。这样聚合之间就解耦了，它可以不依赖其它聚合的实体，独立完成业务逻辑
     *
     * @param currentApproverId
     * @param leaderMaxLevel
     * @return
     */
    public Person findNextApprover(String currentApproverId, int leaderMaxLevel) {
        PersonPO leaderPO = personRepository.findLeaderByPersonId(currentApproverId);
        if (leaderPO.getRoleLevel() > leaderMaxLevel) {
            return null;
        } else {
            return personFactory.createPerson(leaderPO);
        }
    }

}
