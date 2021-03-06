package com.Spring.SpringLetsCode.Repo;

import com.Spring.SpringLetsCode.Model.Message;
import com.Spring.SpringLetsCode.Model.User;
import com.Spring.SpringLetsCode.domain.DTO.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoMessages extends CrudRepository<Message, Long> {

    List<User> findAll(User user);

    @Query("select new com.Spring.SpringLetsCode.domain.DTO.MessageDTO(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "group by m")
    Page<MessageDTO> findAll(Pageable pageable, @Param("user") User user);

    @Query("select new com.Spring.SpringLetsCode.domain.DTO.MessageDTO(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.text = :text " +
            "group by m")
    Page<MessageDTO> findByTag(@Param("text") String text, Pageable pageable, @Param("user") User user);

    @Query("select new com.Spring.SpringLetsCode.domain.DTO.MessageDTO(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.author = :author " +
            "group by m")
    Page<MessageDTO> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);
}