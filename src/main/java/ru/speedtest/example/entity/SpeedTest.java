package ru.speedtest.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "test")
public class SpeedTest {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "test_1")
    private String str1;

    @Column(name = "test_2")
    private String str2;

    @Column(name = "test_3")
    private String str3;

    @Column(name = "test_4")
    private String str4;

    @Column(name = "test_5")
    private String str5;

    @Column(name = "test_6")
    private String str6;

    public int getHashCodeAllObject() {
        return String.join("", id.toString(), str1, str2, str3, str4, str5, str6).hashCode();
    }
}
