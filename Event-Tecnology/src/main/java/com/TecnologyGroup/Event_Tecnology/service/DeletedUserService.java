package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.model.entity.DeletedUser;
import com.TecnologyGroup.Event_Tecnology.repository.DeletedUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DeletedUserService {

    private final DeletedUserRepository deletedUserRepository;

    @Transactional(readOnly = true)
    public DeletedUser getDeletedUserByEmail(String email) {
        return deletedUserRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<DeletedUser> getDeletedUsersByDateRange(LocalDate startDate, LocalDate endDate) {
        return deletedUserRepository.findByDeletedAtBetween(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public boolean deletedUserExistsByEmail(String email) {
        return deletedUserRepository.existsByUserEmail(email);
    }

    @Transactional
    public DeletedUser saveDeletedUser(DeletedUser deletedUser) {
        return deletedUserRepository.save(deletedUser);
    }
}
