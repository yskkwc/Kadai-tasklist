package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "getAllTasks",
            query = "SELECT m FROM Tasks AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getTasksCount",
            query = "SELECT COUNT(m) FROM Tasks AS m"
            )
})

@Table(name = "tasks")

public class Tasks {
    //テーブルの要素(id/created_date/updated_date/contents)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date", nullable = false)
    private Timestamp created_date;

    @Column(name = "updated_date", nullable = false)
    private Timestamp updated_date;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    //getset
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public Timestamp getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
    }

    public String getContents() {
        return content;
    }

    public void setContents(String content) {
        this.content = content;
    }


}
